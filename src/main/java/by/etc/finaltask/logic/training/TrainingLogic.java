package by.etc.finaltask.logic.training;

import by.etc.finaltask.domain.Course;
import by.etc.finaltask.domain.Training;
import by.etc.finaltask.domain.User;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import java.util.List;
import java.util.Map;

public interface TrainingLogic {

    Map<Course, List<User>> findRequest(String userId) throws CourseLogicException, InvalidInputException;

    void rejectSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException;

    void acceptSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException;

    List<User> takeStudent(String courseId) throws CourseLogicException, InvalidInputException;

    void excludeStudent(String courseId, String studentId) throws CourseLogicException, InvalidInputException;

    void setMark(String courseId, String studentId, String mark, String comment) throws CourseLogicException, InvalidInputException;

    void submitCourse(String userId, String courseId) throws InvalidInputException, CourseLogicException;

    String takeCourseStatus(String userId, String courseId) throws InvalidInputException, CourseLogicException;

    List<Training> takeTraining(String userId) throws InvalidInputException, CourseLogicException;

    void startTraining(String courseId) throws InvalidInputException, CourseLogicException;

    void stopTraining(String courseId) throws CourseLogicException, InvalidInputException;

    List<Training> takeStudentForCourse(String courseId) throws InvalidInputException, CourseLogicException;


}
