package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import java.util.List;
import java.util.Map;

public interface CourseLogic {
    void addCourse(String name, String description, String dateStart, String dateFinish, int userId) throws InvalidInputException, CourseLogicException;

    List<Course> findCourseForTeacher(int userId) throws CourseLogicException;

    Map<Course, List<User>> findRequest(int userId) throws CourseLogicException;

    void rejectSubscriber(int courseId, int studentId) throws CourseLogicException;

    void acceptSubscriber(int courseId, int studentId) throws CourseLogicException;

    Course takeCourse(int courseId) throws CourseLogicException;

    List<User> takeStudent(int courseId) throws CourseLogicException;

    void removeCourse(int courseId) throws CourseLogicException;

    void excludeStudent(int courseId, int studentId) throws CourseLogicException;

    void setMark(int courseId, int studentId, int mark, String comment) throws CourseLogicException;

    void editCourse(int courseId, String nameCourse, String description, String dateStart, String dateFinish) throws CourseLogicException;

}
