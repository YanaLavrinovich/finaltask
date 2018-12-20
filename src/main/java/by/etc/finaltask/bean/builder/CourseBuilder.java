package by.etc.finaltask.bean.builder;

import by.etc.finaltask.bean.Course;

import java.sql.Date;

public class CourseBuilder {

    public Course build(String name, String description, String dateStart, String dateFinish, int userId) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        Date dateStartSQL = Date.valueOf(dateStart);
        Date dateFinishSQL = Date.valueOf(dateFinish);
        course.setDateStart(dateStartSQL);
        course.setDateFinish(dateFinishSQL);
        course.setUserId(userId);
        return course;
    }
}
