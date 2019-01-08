package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    void addCourse(Course course) throws DaoException;

    List<Course> findCourseForTeacher(int userId) throws DaoException;

    Map<Course, List<User>> findRequest(int userId) throws DaoException;

    void rejectSubscriber(int studentId) throws DaoException;

    void acceptSubscriber(int studentId) throws DaoException;

    Course takeCourse(int courseId) throws DaoException;

    List<User> takeStudent(int courseId) throws DaoException;

    void removeCourse(int courseId) throws DaoException, DaoRollbackException;
}
