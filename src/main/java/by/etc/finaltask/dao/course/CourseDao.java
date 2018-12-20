package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Role;
import by.etc.finaltask.dao.exception.DaoException;

import java.util.List;

public interface CourseDao {
    void addCourse(Course course) throws DaoException;
    List<Course> findCourseForTeacher(int userId) throws DaoException;
}
