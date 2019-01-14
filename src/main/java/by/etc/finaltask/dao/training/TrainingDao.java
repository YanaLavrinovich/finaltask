package by.etc.finaltask.dao.training;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.dao.exception.DaoException;
import by.etc.finaltask.dao.exception.DaoRollbackException;

import java.util.List;
import java.util.Map;

public interface TrainingDao {
    Map<Course, List<User>> findRequest(int userId) throws DaoException;

    void rejectSubscriber(int courseId, int studentId) throws DaoException;

    void acceptSubscriber(int courseId, int studentId) throws DaoException;

    List<User> takeStudent(int courseId) throws DaoException;

    void excludeStudent(int courseId, int studentId) throws DaoException;

    void setMark(int courseId, int studentId, int mark, String comment) throws DaoException;

    void submitCourse(int userId, int courseId) throws DaoException;

    List<Training> takeTraining(int userId) throws DaoException;

    void startTraining(int courseId) throws DaoException, DaoRollbackException;

    void stopTraining(int courseId) throws DaoException, DaoRollbackException;

    List<Training> takeStudentForCourse(int courseId) throws DaoException;

    String takeCourseStatus(int userId, int courseId) throws DaoException;

}
