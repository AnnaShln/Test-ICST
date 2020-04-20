package com.example.testicst.DB;

public class Question {

    public String question;
    public int id;
    public String Ans1;
    public String Ans2;
    public String Ans3;
    public String point1;
    public String point2;
    public String point3;

    public Question(String question, int id, String ans1, String ans2, String ans3, String point1, String point2, String point3) {
        this.question = question;
        this.id = id;
        Ans1 = ans1;
        Ans2 = ans2;
        Ans3 = ans3;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public String getAns1() {
        return Ans1;
    }

    public void setAns1(String ans1) {
        Ans1 = ans1;
    }

    public String getAns2() {
        return Ans2;
    }

    public void setAns2(String ans2) {
        Ans2 = ans2;
    }

    public String getAns3() {
        return Ans3;
    }

    public void setAns3(String ans3) {
        Ans3 = ans3;
    }

    public String getPoint1() {
        return point1;
    }

    public void setPoint1(String point1) {
        this.point1 = point1;
    }

    public String getPoint2() {
        return point2;
    }

    public void setPoint2(String point2) {
        this.point2 = point2;
    }

    public String getPoint3() {
        return point3;
    }

    public void setPoint3(String point3) {
        this.point3 = point3;
    }

    public Question(String question, int id) {
        this.question = question;
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}