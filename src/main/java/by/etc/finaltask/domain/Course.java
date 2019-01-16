package by.etc.finaltask.domain;

import java.io.Serializable;

/**
 * Entity class for Courses database table.
 */
public class Course implements Serializable {

    /**
     * Serialization UID.
     */
    private static final long serialVersionUID = 4857546578432834325L;

    /**
     * The course's id.
     */
    private int id;

    /**
     * The course's name.
     */
    private String name;

    /**
     * The course's description.
     */
    private String description;

    /**
     * The course's start date.
     */
    private String dateStart;

    /**
     * The course's finish date.
     */
    private String dateFinish;

    /**
     * The id of user who made the course.
     */
    private int userId;

    /**
     * Is course deleted value.
     */
    private boolean isDeleted;

    /**
     * The {@link Progress}.
     */
    private Progress progress;

    /**
     * Defines possible course's progress.
     */
    public enum Progress {

        /**
         * The planned progress.
         */
        PLANNED,

        /**
         * Thr continues progress.
         */
        CONTINUES,

        /**
         * The finished progress.
         */
        FINISHED
    }

    public Course() {
    }

    public Course(int id, String name, String description, String dateStart, String dateFinish, int userId, Progress progress) {
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

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
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
        result = prime * result + progress.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Course course = (Course) obj;
        return course.getId() == id && course.getIsDeleted() == isDeleted &&
                course.getName() != null && course.getName().equals(name) &&
                course.getDescription() != null && course.getDescription().equals(description) &&
                course.getDateStart() != null && course.getDateStart().equals(dateStart) &&
                course.getDateFinish() != null && course.getDateFinish().equals(dateFinish) &&
                course.getUserId() != 0 && course.getUserId() == userId &&
                course.getProgress() != null && course.getProgress() == progress;
    }

    @Override
    public String toString() {
        return String.format("Entity %s [id %d, name %s, description %s, start date %s, date finish %s, userId %d, isDeleted %s, progress %s]",
                getClass().getSimpleName(), id, name, description, dateStart, dateFinish, userId, isDeleted, progress);
    }
}
