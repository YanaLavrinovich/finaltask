package by.etc.finaltask.logic.course;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.bean.Role;
import by.etc.finaltask.logic.exception.CourseLogicException;
import by.etc.finaltask.logic.exception.InvalidInputException;

import java.util.List;

public interface CourseLogic {
    void addCourse(String name, String description, String dateStart, String dateFinish, int userId) throws InvalidInputException, CourseLogicException;
    List<Course> findCourseForTeacher(int userId) throws CourseLogicException;
}
