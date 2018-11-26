package com.example.siwar.fitwoman.model;

import java.sql.Time;
import java.util.Date;

public class MActivity {
    int id;
    String name;
    Date day;
    String duration;
    String Description;
    int burnedCalories;
    String idUser;

    public MActivity() {
    }

    public MActivity(int id, String name, Date day, String duration, String description, int burnedCalories, String idUser) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.duration = duration;
        Description = description;
        this.burnedCalories = burnedCalories;
        this.idUser = idUser;
    }

    public MActivity(String name, String duration, int burnedCalories) {
        this.name = name;
        this.duration = duration;
        this.burnedCalories = burnedCalories;
    }

    public MActivity(String name, String duration, String description) {
        this.name = name;
        this.duration = duration;
        Description = description;
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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
