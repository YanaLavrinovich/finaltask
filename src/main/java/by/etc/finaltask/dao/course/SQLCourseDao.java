package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Sex;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.bean.build_bean.CourseBuilder;
import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLCourseDao implements CourseDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_COURSE = "INSERT INTO courses VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_COURSE_FOR_TEACHER = "SELECT id, name, description, dateStart, dateFinish FROM courses" +
            " WHERE users_id = ?";
    private static final String FIND_REQUEST_FOR_COURSE = "SELECT users.id, email, firstName, lastname, sex FROM training" +
            " JOIN users ON training.users_id = users.id" +
            " JOIN course_status ON training.course_status_id = course_status.id" +
            " WHERE courses_id = ? && course_status.name = 'submit'";

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
            courseStatement.setDate(4, course.getDateStart());
            courseStatement.setDate(5, course.getDateFinish());
            courseStatement.setInt(6, course.getUserId());

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
                Course course = courseBuilder.build(id, name, description, dateStart, dateFinish, userId);
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
    public Map<Course, List<User>> findRequest(int userId) throws DaoException {
        Map<Course, List<User>> requests = new HashMap<>();
        Connection connection = null;
        PreparedStatement userStatement = null;
        ResultSet resultSet = null;

        List<Course> courses = findCourseForTeacher(userId);
        try {
            connection = connectionPool.takeConnection();
            userStatement = connection.prepareStatement(FIND_REQUEST_FOR_COURSE);
            for (Course currentCourse : courses) {
                List<User> users = new ArrayList<>();
                userStatement.setInt(1, currentCourse.getId());
                resultSet = userStatement.executeQuery();

                while (resultSet.next()) {
                    User user = new User();
                    if (resultSet.next()) {
                        user.setId(resultSet.getInt(1));
                        user.setEmail(resultSet.getString(2));
                        user.setFirstName(resultSet.getString(3));
                        user.setLastName(resultSet.getString(4));
                        user.setSex(Sex.valueOf(resultSet.getString(5).toUpperCase()));
                    }
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

}