package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.util.List;

public interface CourseDao {
    void addCourse(Course course) throws DaoException;

    List<Course> findCourseForTeacher(int userId) throws DaoException;

    Course takeCourse(int courseId) throws DaoException;

    void removeCourse(int courseId) throws DaoException, DaoRollbackException;

    void editCourse(int courseId, String nameCourse, String description, String dateStart, String dateFinish) throws DaoException;

    List<Course> findActualCourse() throws DaoException;

    List<Course> findAllCourse() throws DaoException;

    void restoreCourse(int courseId) throws DaoException;
}
