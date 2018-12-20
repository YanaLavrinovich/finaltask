package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.builder.CourseBuilder;
import by.etc.finaltask.dao.connector.ConnectionPool;
import by.etc.finaltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCourseDao implements CourseDao {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String INSERT_COURSE = "INSERT INTO courses VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_COURSE_FOR_TEACHER = "SELECT name, description, dateStart, dateFinish FROM courses" +
            " WHERE users_id = ?";
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

            while(resultSet.next()) {
                String name = resultSet.getString(1);
                String description = resultSet.getString(2);
                String dateStart = resultSet.getString(3);
                String dateFinish = resultSet.getString(4);
                Course course = courseBuilder.build(name, description, dateStart, dateFinish, userId);
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