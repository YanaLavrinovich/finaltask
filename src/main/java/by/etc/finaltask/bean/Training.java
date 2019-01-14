package by.etc.finaltask.bean;

import java.io.Serializable;

/**
 * Entity class for Training database table.
 */
public class Training implements Serializable {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 4857546578432834325L;

    /**
     * The course's name
     */
    private String nameCourse;

    /**
     * The {@link Status}.
     */
    private Status courseStatus;

    /**
     * The user's mark.
     */
    private String mark;

    /**
     * The user's comment.
     */
    private String comment;

    /**
     * The course's start date.
     */
    private String dateStart;

    /**
     * The course's finish date.
     */
    private String dateFinish;

    /**
     * The user's id.
     */
    private int userId;

    /**
     * The user's first name.
     */
    private String userFirstName;

    /**
     * The user's last name.
     */
    private String userLastName;

    /**
     * Defines possible training's status.
     */
    public enum Status {
        /**
         * The approved status.
         */
        APPROVED("approved"),

        /**
         * The completed status.
         */
        COMPLETED("completed"),

        /**
         * The excluded status.
         */
        EXCLUDED("excluded"),

        /**
         * The in process status.
         */
        IN_PROCESS("in process"),

        /**
         * The leaved status.
         */
        LEAVED("leaved"),

        /**
         * The rejected status.
         */
        REJECTED("rejected"),

        /**
         * The requested status.
         */
        REQUESTED("requested");

        /**
         * Constant's String value.
         */
        private String stringValue;


        Status(String stringValue) {
            this.stringValue = stringValue;
        }

        public String getStringValue() {
            return stringValue;
        }
    }

    public Training() {
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public Status getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Status courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Training training = (Training) obj;
        return training.getNameCourse() != null && nameCourse.equals(training.getNameCourse()) &&
                training.getCourseStatus() != null && courseStatus.equals(training.getCourseStatus()) &&
                training.getMark() != null && mark.equals(training.getMark()) &&
                training.getComment() != null && comment.equals(training.getComment()) &&
                training.getDateStart() != null && dateStart.equals(training.getDateStart()) &&
                training.getDateFinish() != null && dateFinish.equals(training.getDateFinish()) &&
                training.getUserId() == userId &&
                training.getUserFirstName() != null && userFirstName.equals(training.getUserFirstName()) &&
                training.getUserLastName() != null && userLastName.equals(training.getUserLastName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + nameCourse.hashCode();
        result = prime * result + courseStatus.hashCode();
        result = prime * result + mark.hashCode();
        result = prime * result + comment.hashCode();
        result = prime * result + dateStart.hashCode();
        result = prime * result + dateFinish.hashCode();
        result = prime * result + userId;
        result = prime * result + userFirstName.hashCode();
        result = prime * result + userLastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Entity %s [name %s, course status %s, mark %s, comment %s, date start %s, date finish %s, " +
                        "userId %d, userFirstName %s, userLastName %s]",
                getClass().getSimpleName(), nameCourse, courseStatus, mark, comment, dateStart, dateFinish, userId, userFirstName, userLastName);
    }
}