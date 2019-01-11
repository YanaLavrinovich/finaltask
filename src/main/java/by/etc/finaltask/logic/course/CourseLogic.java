package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import java.util.List;
import java.util.Map;

public interface CourseLogic {
    void addCourse(String name, String description, String dateStart, String dateFinish, String userId) throws InvalidInputException, CourseLogicException;

    List<Course> findCourseForTeacher(String userId) throws CourseLogicException, InvalidInputException;

    Map<Course, List<User>> findRequest(String userId) throws CourseLogicException, InvalidInputException;

    void rejectSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException;

    void acceptSubscriber(String courseId, String studentId) throws CourseLogicException, InvalidInputException;

    Course takeCourse(String courseId) throws CourseLogicException, InvalidInputException;

    List<User> takeStudent(String courseId) throws CourseLogicException, InvalidInputException;

    void removeCourse(String courseId) throws CourseLogicException, InvalidInputException;

    void excludeStudent(String courseId, String studentId) throws CourseLogicException, InvalidInputException;

    void setMark(String courseId, String studentId, String mark, String comment) throws CourseLogicException, InvalidInputException;

    void editCourse(String courseId, String nameCourse, String description, String dateStart, String dateFinish) throws CourseLogicException, InvalidInputException;

    List<Course> findActualCourse() throws CourseLogicException;

    void submitCourse(String userId, String courseId) throws InvalidInputException, CourseLogicException;

    List<Training> takeTraining(String userId) throws InvalidInputException, CourseLogicException;
}
