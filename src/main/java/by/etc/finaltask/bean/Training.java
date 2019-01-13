package by.etc.finaltask.bean;

import java.io.Serializable;

public class Training implements Serializable {
    private static final long serialVersionUID = 4857546578432834325L;

    private String nameCourse;
    private String courseStatus;
    private String mark;
    private String comment;
    private String dateStart;
    private String dateFinish;
    private int userId;
    private String userFirstName;
    private String userLastName;

    public Training() {
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
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
        return nameCourse != null && nameCourse.equals(training.getNameCourse()) &&
                courseStatus != null && courseStatus.equals(training.getCourseStatus()) &&
                mark != null && mark.equals(training.getMark()) &&
                comment != null && comment.equals(training.getComment()) &&
                dateStart != null && dateStart.equals(training.getDateStart()) &&
                dateFinish != null && dateFinish.equals(training.getDateFinish());
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
        return result;
    }

    @Override
    public String toString() {
        return  String.format("Entity %s [name %s, course status %s, mark %s, comment %s, date start %s, date finish %s]",
                getClass().getSimpleName(), nameCourse, courseStatus, mark, comment, dateStart, dateFinish);
    }
}
