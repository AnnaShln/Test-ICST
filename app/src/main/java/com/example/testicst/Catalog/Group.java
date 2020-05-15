package com.example.testicst.Catalog;

import java.util.List;

public class Group{

    public String title;
    public String description;
    public String professions;
    public String salary;
    public int id;

    public Group (String title,String description, String professions, String salary, int id)
    {
        this.title = title;
        this.description = description;
        this.professions = professions;
        this.salary = salary;
        this.id = id;
    }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() {return title;}
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setProfessions(String professions) {
        this.professions = professions;
    }
    public String getProfessions() {
        return professions;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getSalary() {
        return salary;
    }
}
