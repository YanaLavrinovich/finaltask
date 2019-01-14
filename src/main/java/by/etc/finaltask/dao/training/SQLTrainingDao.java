package by.etc.finaltask.dao.training;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.bean.build_bean.TrainingBuilder;
import by.etc.finaltask.bean.build_bean.UserBuilder;
import by.etc.finaltask.dao.DaoFactory;
import by.etc.finaltask.dao.connector.ConnectionException;
import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.course.CourseDao;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLTrainingDao implements TrainingDao{
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_REQUEST_FOR_COURSE = "SELECT users.id, email, firstName, lastname, sex FROM training" +
            " JOIN users ON training.users_id = users.id" +
            " JOIN course_status ON training.course_status_id = course_status.id" +
            " WHERE courses_id = ? && course_status.name = 'requested'";
    private static final String REJECT_SUBSCRIBER = "UPDATE training " +
            "SET course_status_id = " +
            "(SELECT id FROM course_status WHERE course_status.name = 'rejected') " +
            "WHERE courses_id = ? AND users_id = ?";
    private static final String ACCEPT_SUBSCRIBER = "UPDATE training " +
            "SET course_status_id = " +
            "(SELECT id FROM course_status WHERE course_status.name = 'approved') " +
            "WHERE courses_id = ? AND users_id = ?";
    private static final String TAKE_STUDENTS_BY_COURSE_ID = "SELECT users.id, email, firstName, lastname FROM training" +
            " JOIN users ON training.users_id = users.id" +
            " JOIN course_status ON training.course_status_id = course_status.id" +
            " WHERE courses_id = ? && course_status.name IN ('approved', 'in process', 'completed', 'leaved', 'excluded')";
    private static final String EXCLUDE_STUDENT = "UPDATE training SET course_status_id =" +
            " (SELECT id FROM course_status WHERE course_status.name = 'excluded')" +
            " WHERE courses_id = ? AND users_id = ?";
    private static final String SET_MARK = "UPDATE training SET mark = ?, comment = ?" +
            " WHERE courses_id = ? AND users_id = ?";
    private static final String SUBMIT_COURSE = "INSERT INTO training SET" +
            " courses_id = ?, course_status_id = (SELECT id FROM course_status WHERE name = 'requested')," +
            " users_id = ?, users_roles_id = (SELECT id FROM roles WHERE name = 'student')";
    private static final String TAKE_TRAINING_BY_ID = "SELECT courses.name, course_status.name, mark, comment, courses.dateStart, courses.dateFinish" +
            " FROM training JOIN courses ON training.courses_id = courses.id" +
            " JOIN course_status ON training.course_status_id = course_status.id" +
            " WHERE training.users_id = ?";
    private static final String START_COURSE = "UPDATE courses SET progress = 'continues' WHERE id = ?";
    private static final String SET_STUDENT_IN_PROCESS = "UPDATE training SET course_status_id =" +
            " (SELECT id FROM course_status WHERE name = 'in process')" +
            " WHERE courses_id = ? AND course_status_id =" +
            " (SELECT id FROM course_status WHERE name = 'approved')";
    private static final String STOP_COURSE = "UPDATE courses SET progress = 'finished' WHERE id = ?";
    private static final String SET_STUDENT_COMPLETED = "UPDATE training SET course_status_id =" +
            " (SELECT id FROM course_status WHERE name = 'completed')" +
            " WHERE courses_id = ?";
    private static final String TAKE_TRAINING_FOR_COURSE = "SELECT users_id, users.firstName, users.lastname, course_status.name, mark, comment" +
            " FROM training JOIN users ON training.users_id = users.id" +
            " JOIN course_status ON training.course_status_id = course_status.id WHERE courses_id = ?";
    private static final String TAKE_COURSE_STATUS = "SELECT course_status.name FROM training" +
            " JOIN course_status ON course_status.id = training.course_status_id" +
            " WHERE users_id = ? AND courses_id = ?";

    @Override
    public Map<Course, List<User>> findRequest(int userId) throws DaoException {
        Map<Course, List<User>> requests = new HashMap<>();
        Connection connection = null;
        PreparedStatement userStatement = null;
        ResultSet resultSet = null;
        CourseDao courseDao = DaoFactory.getInstance().getCourseDao();
        UserBuilder userBuilder = new UserBuilder();

        List<Course> courses = courseDao.findCourseForTeacher(userId);
        try {
            connection = connectionPool.takeConnection();
            userStatement = connection.prepareStatement(FIND_REQUEST_FOR_COURSE);
            for (Course currentCourse : courses) {
                List<User> users = new ArrayList<>();
                userStatement.setInt(1, currentCourse.getId());
                resultSet = userStatement.executeQuery();

                while (resultSet.next()) {
                    userBuilder.addId(resultSet.getInt(1));
                    userBuilder.addEmail(resultSet.getString(2));
                    userBuilder.addFirstName(resultSet.getString(3));
                    userBuilder.addLastName(resultSet.getString(4));
                    userBuilder.addSex(resultSet.getString(5).toUpperCase());
                    User user = userBuilder.build();
                    users.add(user);
                }
                requests.put(currentCourse, users);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't get requests.", e);
        } finally {
            connectionPool.closeConnection(connection, userStatement, resultSet);
        }
        return requests;
    }

    @Override
    public void rejectSubscriber(int courseId, int studentId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(REJECT_SUBSCRIBER);
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't reject subscriber.", e);
        } finally {
            connectionPool.closeStatement(statement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void acceptSubscriber(int courseId, int studentId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(ACCEPT_SUBSCRIBER);
            statement.setInt(1, courseId);
            statement.setInt(2, studentId);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't accept subscriber.", e);
        } finally {
            connectionPool.closeStatement(statement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<User> takeStudent(int courseId) throws DaoException {
        List<User> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            UserBuilder userBuilder = new UserBuilder();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_STUDENTS_BY_COURSE_ID);
            statement.setInt(1, courseId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                userBuilder.addId(id);
                userBuilder.addEmail(email);
                userBuilder.addFirstName(firstName);
                userBuilder.addLastName(lastName);
                User user = userBuilder.build();
                students.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't take course.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return students;
    }

    @Override
    public void excludeStudent(int courseId, int studentId) throws DaoException {
        Connection connection = null;
        PreparedStatement studentStatement = null;
        try {
            connection = connectionPool.takeConnection();
            studentStatement = connection.prepareStatement(EXCLUDE_STUDENT);
            studentStatement.setInt(1, courseId);
            studentStatement.setInt(2, studentId);
            studentStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't exclude student. Something is wrong with database", e);
        } finally {
            connectionPool.closeStatement(studentStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void setMark(int courseId, int studentId, int mark, String comment) throws DaoException {
        Connection connection = null;
        PreparedStatement markStatement = null;
        try {
            connection = connectionPool.takeConnection();
            markStatement = connection.prepareStatement(SET_MARK);
            markStatement.setInt(1, mark);
            markStatement.setString(2, comment);
            markStatement.setInt(3, courseId);
            markStatement.setInt(4, studentId);
            markStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't set mark for student. Something is wrong with database", e);
        } finally {
            connectionPool.closeStatement(markStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void submitCourse(int userId, int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        try {
            connection = connectionPool.takeConnection();
            courseStatement = connection.prepareStatement(SUBMIT_COURSE);
            courseStatement.setInt(1, courseId);
            courseStatement.setInt(2, userId);
            courseStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't submit course. Something is wrong with database", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Training> takeTraining(int userId) throws DaoException {
        List<Training> trainingList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            TrainingBuilder trainingBuilder = new TrainingBuilder();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_TRAINING_BY_ID);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString(1);
                String courseStatus = resultSet.getString(2);
                String mark = resultSet.getString(3);
                String comment = resultSet.getString(4);
                String dateStart = resultSet.getString(5);
                String dateFinish = resultSet.getString(6);
                trainingBuilder.addNameCourse(courseName);
                trainingBuilder.addCourseStatus(courseStatus);
                trainingBuilder.addMark(mark);
                trainingBuilder.addComment(comment);
                trainingBuilder.addDateStart(dateStart);
                trainingBuilder.addDateFinish(dateFinish);
                trainingList.add(trainingBuilder.build());
            }
        } catch (SQLException e) {
            throw new DaoException("Can't take training list.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return trainingList;
    }

    @Override
    public void startTraining(int courseId) throws DaoException, DaoRollbackException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        PreparedStatement studentStatement = null;
        try {
            connection = connectionPool.takeManualCommitConnection();
            courseStatement = connection.prepareStatement(START_COURSE);
            courseStatement.setInt(1, courseId);
            courseStatement.execute();
            studentStatement = connection.prepareStatement(SET_STUDENT_IN_PROCESS);
            studentStatement.setInt(1, courseId);
            studentStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoRollbackException("Can't rollback connection.", e);
            }
            throw new DaoException("Can't start course.", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can't get connection.", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeStatement(studentStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void stopTraining(int courseId) throws DaoException, DaoRollbackException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        try {
            connection = connectionPool.takeManualCommitConnection();
            courseStatement = connection.prepareStatement(STOP_COURSE);
            courseStatement.setInt(1, courseId);
            courseStatement.execute();
            courseStatement = connection.prepareStatement(SET_STUDENT_COMPLETED);
            courseStatement.setInt(1, courseId);
            courseStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoRollbackException("Can't rollback connection.", e);
            }
            throw new DaoException("Can't stop course.", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can't get connection.", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Training> takeStudentForCourse(int courseId) throws DaoException {
        List<Training> studentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            TrainingBuilder trainingBuilder = new TrainingBuilder();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_TRAINING_FOR_COURSE);
            statement.setInt(1, courseId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt(1);
                String userFirstName = resultSet.getString(2);
                String userLastName = resultSet.getString(3);
                String courseStatus = resultSet.getString(4);
                String mark = resultSet.getString(5);
                String comment = resultSet.getString(6);

                trainingBuilder.addUserFirstName(userFirstName);
                trainingBuilder.addUserLastName(userLastName);
                trainingBuilder.addCourseStatus(courseStatus);
                trainingBuilder.addMark(mark);
                trainingBuilder.addComment(comment);
                trainingBuilder.addUserId(userId);

                Training training = trainingBuilder.build();
                studentList.add(training);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't take training list.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return studentList;
    }

    @Override
    public String takeCourseStatus(int userId, int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String courseStatus = null;
        try {
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_COURSE_STATUS);
            statement.setInt(1, userId);
            statement.setInt(2, courseId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                courseStatus = resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't take course status.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return courseStatus;
    }



}
