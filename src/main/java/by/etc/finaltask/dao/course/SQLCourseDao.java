package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.build_bean.CourseBuilder;
import by.etc.finaltask.dao.connector.ConnectionException;
import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLCourseDao implements CourseDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_COURSE = "INSERT INTO courses VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_COURSE_FOR_TEACHER = "SELECT id, name, description, dateStart, dateFinish FROM courses" +
            " WHERE users_id = ? && isDeleted = 'false'";
    private static final String TAKE_COURSE_BY_ID = "SELECT id, name, description, dateStart, dateFinish, users_id, progress FROM courses" +
            " WHERE id = ?";
    private static final String REMOVE_COURSE = "UPDATE courses" +
            " SET isDeleted = 'true' WHERE id = ?";
    private static final String REJECT_STUDENTS_OF_COURSE = "UPDATE training SET" +
            " course_status_id = (SELECT id FROM course_status WHERE course_status.name = 'reject')" +
            " WHERE courses_id = ?";
    private static final String EDIT_COURSE = "UPDATE courses SET name = ?, description = ?, dateStart = ?, dateFinish = ?" +
            " WHERE id = ?";
    private static final String FIND_ACTUAL_COURSE = "SELECT id, name, description, dateStart, dateFinish, users_id, isDeleted FROM courses" +
            " WHERE isDeleted = 'false' && progress = 'planned'";
    private static final String FIND_ALL_COURSES = "SELECT id, name, description, dateStart, dateFinish, users_id, isDeleted FROM courses";
    private static final String RESTORE_COURSE = "UPDATE courses SET isDeleted = 'false' WHERE id = ?";

    @Override
    public void addCourse(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        try {
            connection = connectionPool.takeConnection();
            courseStatement = connection.prepareStatement(INSERT_COURSE);
            courseStatement.setInt(1, course.getId());
            courseStatement.setString(2, course.getName());
            courseStatement.setString(3, course.getDescription());
            Date dateStartSQL = Date.valueOf(course.getDateStart());
            Date dateFinishSQL = Date.valueOf(course.getDateFinish());
            courseStatement.setDate(4, dateStartSQL);
            courseStatement.setDate(5, dateFinishSQL);
            courseStatement.setInt(6, course.getUserId());
            courseStatement.setString(7, "false");
            courseStatement.setString(8, "planned");

            courseStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't insert new course.", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }

    public List<Course> findCourseForTeacher(int userId) throws DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        ResultSet resultSet = null;

        List<Course> courses = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            courseStatement = connection.prepareStatement(FIND_COURSE_FOR_TEACHER);
            courseStatement.setInt(1, userId);
            resultSet = courseStatement.executeQuery();
            CourseBuilder courseBuilder = new CourseBuilder();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String dateStart = resultSet.getString(4);
                String dateFinish = resultSet.getString(5);
                courseBuilder.addId(id);
                courseBuilder.addName(name);
                courseBuilder.addDescription(description);
                courseBuilder.addDateStart(dateStart);
                courseBuilder.addDateFinish(dateFinish);
                courseBuilder.addUserId(userId);
                Course course = courseBuilder.build();
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't get courses.", e);
        } finally {
            connectionPool.closeConnection(connection, courseStatement, resultSet);
        }
        return courses;
    }

    @Override
    public Course takeCourse(int courseId) throws DaoException {
        Course course = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            CourseBuilder courseBuilder = new CourseBuilder();
            connection = connectionPool.takeConnection();
            statement = connection.prepareStatement(TAKE_COURSE_BY_ID);
            statement.setInt(1, courseId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String dateStart = resultSet.getString(4);
                String dateFinish = resultSet.getString(5);
                int userId = resultSet.getInt(6);
                String progress = resultSet.getString(7);
                courseBuilder.addId(id);
                courseBuilder.addName(name);
                courseBuilder.addDescription(description);
                courseBuilder.addDateStart(dateStart);
                courseBuilder.addDateFinish(dateFinish);
                courseBuilder.addUserId(userId);
                courseBuilder.addProgress(progress);
                course = courseBuilder.build();
            }
        } catch (SQLException e) {
            throw new DaoException("Can't take course.", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
        return course;
    }

    @Override
    public void removeCourse(int courseId) throws DaoException, DaoRollbackException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        PreparedStatement studentStatement = null;
        try {
            connection = connectionPool.takeManualCommitConnection();
            courseStatement = connection.prepareStatement(REMOVE_COURSE);
            courseStatement.setInt(1, courseId);
            courseStatement.execute();
            studentStatement = connection.prepareStatement(REJECT_STUDENTS_OF_COURSE);
            studentStatement.setInt(1, courseId);
            studentStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoRollbackException("Exception with rollback.", e);
            }
            throw new DaoException("Can't remove course.", e);
        } catch (ConnectionException e) {
            throw new DaoException("Can't get connection.", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public void editCourse(int courseId, String nameCourse, String description, String dateStart, String dateFinish) throws DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        try {
            connection = connectionPool.takeConnection();
            courseStatement = connection.prepareStatement(EDIT_COURSE);
            courseStatement.setString(1, nameCourse);
            courseStatement.setString(2, description);
            Date dateStartSQL = Date.valueOf(dateStart);
            Date dateFinishSQL = Date.valueOf(dateFinish);
            courseStatement.setDate(3, dateStartSQL);
            courseStatement.setDate(4, dateFinishSQL);
            courseStatement.setInt(5, courseId);

            courseStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't edit course.", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }

    @Override
    public List<Course> findActualCourse() throws DaoException {
        return findCourse(FIND_ACTUAL_COURSE);
    }

    @Override
    public List<Course> findAllCourse() throws DaoException {
        return findCourse(FIND_ALL_COURSES);
    }

    @Override
    public void restoreCourse(int courseId) throws DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        try {
            connection = connectionPool.takeConnection();
            courseStatement = connection.prepareStatement(RESTORE_COURSE);
            courseStatement.setInt(1, courseId);
            courseStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Can't restore course.", e);
        } finally {
            connectionPool.closeStatement(courseStatement);
            connectionPool.closeConnection(connection);
        }
    }

    private List<Course> findCourse(String select) throws DaoException {
        Connection connection = null;
        PreparedStatement courseStatement = null;
        ResultSet resultSet = null;

        List<Course> courses = new ArrayList<>();
        try {
            connection = connectionPool.takeConnection();
            courseStatement = connection.prepareStatement(select);
            resultSet = courseStatement.executeQuery();
            CourseBuilder courseBuilder = new CourseBuilder();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                String dateStart = resultSet.getString(4);
                String dateFinish = resultSet.getString(5);
                int teacherId = resultSet.getInt(6);
                boolean isDeleted = resultSet.getBoolean(7);
                courseBuilder.addId(id);
                courseBuilder.addName(name);
                courseBuilder.addDescription(description);
                courseBuilder.addDateStart(dateStart);
                courseBuilder.addDateFinish(dateFinish);
                courseBuilder.addUserId(teacherId);
                courseBuilder.addDeleted(isDeleted);
                Course course = courseBuilder.build();
                courses.add(course);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't get courses.", e);
        } finally {
            connectionPool.closeConnection(connection, courseStatement, resultSet);
        }
        return courses;
    }

}