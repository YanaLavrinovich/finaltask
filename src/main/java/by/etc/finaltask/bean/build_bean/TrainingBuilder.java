package by.etc.finaltask.bean.build_bean;

import by.etc.finaltask.bean.Training;

public class TrainingBuilder {

    public Training build(String nameCourse, String courseStatus, String mark, String comment, String dateStart, String dateFinish) {
        Training training = new Training();
        training.setNameCourse(nameCourse);
        training.setCourseStatus(courseStatus);
        training.setMark(mark);
        training.setComment(comment);
        training.setDateStart(dateStart);
        training.setDateFinish(dateFinish);
        return training;
    }

    public Training build(String userFirstName, String userLastName, String courseStatus, String mark, String comment) {
        Training training = new Training();
        training.setUserFirstName(userFirstName);
        training.setUserLastName(userLastName);
        training.setCourseStatus(courseStatus);
        training.setMark(mark);
        training.setComment(comment);
        return training;
    }
}
