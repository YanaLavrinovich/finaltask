package by.etc.finaltask.bean;

import java.io.Serializable;

public class Course implements Serializable {
    private static final long serialVersionUID = 4857546578432834325L;

    private int id;
    private String name;
    private String description;
    private String dateStart;
    private String dateFinish;
    private int userId;
    private String isDeleted;

    private String progress;

    public Course() {
    }

    public Course(int id, String name, String description, String dateStart, String dateFinish, int userId, String progress) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.userId = userId;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + id;
        result = prime * result + name.hashCode();
        result = prime * result + description.hashCode();
        result = prime * result + dateStart.hashCode();
        result = prime * result + dateFinish.hashCode();
        result = prime * result + userId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return course.getId() == id &&
                course.getName() != null && course.getName().equals(name) &&
                course.getDescription() != null && course.getDescription().equals(description) &&
                course.getDateStart() != null && course.getDateStart().equals(dateStart) &&
                course.getDateFinish() != null && course.getDateFinish().equals(dateFinish) &&
                course.getUserId() != 0 && course.getUserId() == userId;
    }

    @Override
    public String toString() {
        return String.format("Entity %s [id %d, name %s, description %s, start date %s, date finish %s, userId %d]",
                getClass().getSimpleName(), id, name, description, dateStart, dateFinish, userId);
    }
}
