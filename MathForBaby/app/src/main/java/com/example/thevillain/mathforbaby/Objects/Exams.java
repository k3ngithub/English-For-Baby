package com.example.thevillain.mathforbaby.Objects;

/**
 * Created by Huy on 05/11/2017.
 */

public class Exams {
    private String id;
    private String image;
    private String questions;
    private String answer1;
    private String answer2;
    private String resultans;
    private String score;

    public Exams() {
    }

    public Exams(String id, String image, String questions, String answer1, String answer2, String resultans, String score) {
        this.id = id;
        this.image = image;
        this.questions = questions;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.resultans = resultans;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getResultans() {
        return resultans;
    }

    public void setResultans(String resultans) {
        this.resultans = resultans;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}