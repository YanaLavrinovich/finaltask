package by.etc.finaltask.dao.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface CourseDao {
    void addCourse(Course course) throws DaoException;

    List<Course> findCourseForTeacher(int userId) throws DaoException;

    Map<Course, List<User>> findRequest(int userId) throws DaoException;

    void rejectSubscriber(int courseId, int studentId) throws DaoException;

    void acceptSubscriber(int courseId, int studentId) throws DaoException;

    Course takeCourse(int courseId) throws DaoException;

    List<User> takeStudent(int courseId) throws DaoException;

    void removeCourse(int courseId) throws DaoException, DaoRollbackException;

    void excludeStudent(int courseId, int studentId) throws DaoException;

    void setMark(int courseId, int studentId, int mark, String comment) throws DaoException;

    void editCourse(int courseId, String nameCourse, String description, String dateStart, String dateFinish) throws DaoException;

    List<Course> findActualCourse() throws DaoException;

    void submitCourse(int userId, int courseId) throws DaoException;

    List<Training> takeTraining(int userId) throws DaoException;

    void startTraining(int courseId) throws DaoException, DaoRollbackException;

    void stopTraining(int courseId) throws DaoException, DaoRollbackException;

    List<Training> takeStudentForCourse(int courseId) throws DaoException;

    String takeCourseStatus(int userId, int courseId) throws DaoException;

    List<Course> findAllCourse() throws DaoException;

    void restoreCourse(int courseId) throws DaoException;
}
