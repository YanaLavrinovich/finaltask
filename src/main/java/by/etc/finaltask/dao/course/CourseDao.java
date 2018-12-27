package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    void addCourse(Course course) throws DaoException;

    List<Course> findCourseForTeacher(int userId) throws DaoException;

    Map<Course, List<User>> findRequest(int userId) throws DaoException;

}
