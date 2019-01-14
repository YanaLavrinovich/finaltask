package by.etc.finaltask.bean.build_bean;

import by.etc.finaltask.bean.Course;
import by.etc.finaltask.logic.validator.CourseValidator;

import java.io.Serializable;

/**
 * Build course. Parameters are validated with {@link CourseValidator}.
 */
public class CourseBuilder implements Serializable {
    private static final long serialVersionUID = 2296897445903644174L;

    private int id;
    private String name;
    private String description;
    private String dateStart;
    private String dateFinish;
    private int userId;
    private boolean isDeleted;
    private Course.Progress progress;

    public CourseBuilder() {
        progress = Course.Progress.PLANNED;
    }

    /**
     * Adds id to {@link Course}.
     *
     * @param id course id
     */
    public void addId(int id) {
        this.id = id;
    }

    /**
     * Adds name to {@link Course}.
     *
     * @param name course name
     */
    public void addName(String name) {
        this.name = name;
    }

    /**
     * Adds description to {@link Course}.
     *
     * @param description course description
     */
    public void addDescription(String description) {
        this.description = description;
    }

    /**
     * Adds date start to {@link Course}.
     *
     * @param dateStart course date start
     */
    public void addDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Adds dateFinish to {@link Course}.
     *
     * @param dateFinish course date finish
     */
    public void addDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    /**
     * Adds user id to {@link Course}.
     *
     * @param userId course user's id
     */
    public void addUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Adds id to {@link Course}.
     *
     * @param isDeleted course is deleted
     */
    public void addDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Adds progress to {@link Course}.
     *
     * @param progress course progress
     */
    public void addProgress(String progress) {
        this.progress = Course.Progress.valueOf(progress.toUpperCase());
    }

    /**
     * Builds {@link Course}.
     *
     * @return new course
     */
    public Course build() {
        Course course = new Course();
        course.setId(id);
        course.setName(name);
        course.setDescription(description);
        course.setDateStart(dateStart);
        course.setDateFinish(dateFinish);
        course.setUserId(userId);
        course.setIsDeleted(isDeleted);
        course.setProgress(progress);
        return course;
    }

}
