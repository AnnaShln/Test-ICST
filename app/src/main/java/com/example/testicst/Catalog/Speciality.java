package com.example.testicst.Catalog;

public class Speciality extends Group{
    String name;
    String exams;
    String points;
    String places;
    private boolean isfirst;
    private boolean expanded;

    public Speciality (String title,String description, String professions, String salary,
                       String name, String exams, String points, String places, int idDirection)
    {
        super(title, description, professions, salary, idDirection);
        this.name = name;
        this.exams = exams;
        this.places = places;
        this.points = points;
        this.expanded = false;
        this.isfirst = true;
    }
    public Speciality (String title,  String name, String exams, String points, String places, int idDirection)
    {
        super(title, "false","false","false", idDirection);
        this.name = name;
        this.exams = exams;
        this.places = places;
        this.points = points;
        this.expanded = false;
        this.isfirst = true;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name;}
    public String getExams() {
        return exams;
    }
    public void setExams(String exams) {this.exams = exams;}
    public String getPoints() { return points; }
    public void setPoints(String points) { this.points = points; }
    public String getPlaces() { return places; }
    public void setPlaces(String places) { this.places = places; }
    public boolean isExpanded() { return expanded; }
    public void setExpanded(boolean expanded) { this.expanded = expanded; }

}

