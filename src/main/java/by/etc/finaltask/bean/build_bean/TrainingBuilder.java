package by.etc.finaltask.bean.build_bean;

import by.etc.finaltask.bean.Training;
import by.etc.finaltask.bean.User;
import by.etc.finaltask.logic.validator.TrainingValidator;

import java.io.Serializable;

/**
 * Build training. Parameters are validated with {@link TrainingValidator}.
 */
public class TrainingBuilder implements Serializable {
    private static final long serialVersionUID = 2299843224903644174L;

    private String nameCourse;
    private Training.Status courseStatus;
    private String mark;
    private String comment;
    private String dateStart;
    private String dateFinish;
    private int userId;
    private String userFirstName;
    private String userLastName;

    public TrainingBuilder() {
        courseStatus = Training.Status.REQUESTED;
    }

    /**
     * Adds name course to {@link Training}.
     *
     * @param nameCourse training name course
     */
    public void addNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    /**
     * Adds course status to {@link Training}.
     *
     * @param courseStatus training course status
     */
    public void addCourseStatus(String courseStatus) {
        this.courseStatus = Training.Status.valueOf(courseStatus.toUpperCase());
    }

    /**
     * Adds mark to {@link Training}.
     *
     * @param mark training mark
     */
    public void addMark(String mark) {
        this.mark = mark;
    }

    /**
     * Adds comment to {@link Training}.
     *
     * @param comment training comment
     */
    public void addComment(String comment) {
        this.comment = comment;
    }

    /**
     * Adds date start to {@link Training}.
     *
     * @param dateStart training date start
     */
    public void addDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * Adds date finish to {@link Training}.
     *
     * @param dateFinish training date finish
     */
    public void addDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    /**
     * Adds userId to {@link Training}.
     *
     * @param userId training userId
     */
    public void addUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Adds user first name to {@link Training}.
     *
     * @param userFirstName training user first name
     */
    public void addUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * Adds user last name to {@link Training}.
     *
     * @param userLastName training user last name
     */
    public void addUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Builds {@link Training}.
     *
     * @return new training
     */
    public Training build() {
        Training training = new Training();
        training.setNameCourse(nameCourse);
        training.setCourseStatus(courseStatus);
        training.setMark(mark);
        training.setComment(comment);
        training.setDateStart(dateStart);
        training.setDateFinish(dateFinish);
        training.setUserId(userId);
        training.setUserFirstName(userFirstName);
        training.setUserLastName(userLastName);
        return training;
    }
}
