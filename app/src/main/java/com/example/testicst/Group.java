package com.example.testicst;

import java.util.List;

public class Group {
    private String title;
    private String description;
    private String professions;
    private boolean expanded;


    public Group (String title, String description,String professions)
    {
        this.title = title;
        this.description = description;
        this.professions = professions;
        this.expanded = false;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getProfessions() { return professions; }
    public void setProfessions(String title) { this.professions = professions; }

    public boolean isExpanded() { return expanded; }
    public void setExpanded(boolean expanded) { this.expanded = expanded; }

}
