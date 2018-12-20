package by.etc.finaltask.bean;

import java.io.Serializable;
import java.sql.Date;

public class Course implements Serializable {
    private static final long serialVersionUID = 4857546578432834325L;

    private int id;
    private String name;
    private String description;
    private Date dateStart;
    private Date dateFinish;
    private int userId;

    public Course() {
    }

    public Course(int id, String name, String description, Date dateStart, Date dateFinish, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.userId = userId;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
