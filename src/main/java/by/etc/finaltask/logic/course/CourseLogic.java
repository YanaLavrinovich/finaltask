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

   Course takeCourse(String courseId) throws CourseLogicException, InvalidInputException;

    void removeCourse(String courseId) throws CourseLogicException, InvalidInputException;

   void editCourse(String courseId, String nameCourse, String description, String dateStart, String dateFinish) throws CourseLogicException, InvalidInputException;

    List<Course> findActualCourse() throws CourseLogicException;

    List<Course> findAllCourse() throws CourseLogicException;

    void restoreCourse(String courseId) throws InvalidInputException, CourseLogicException;
}
