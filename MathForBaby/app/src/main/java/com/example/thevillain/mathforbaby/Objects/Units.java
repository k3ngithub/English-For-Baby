package com.example.thevillain.mathforbaby.Objects;

/**
 * Created by Huy on 05/11/2017.
 */

public class Units {
    private String id;
    private String unit_name;
    private String unit_img;
    private String lesson1_img;
    private String lesson1;
    private String lesson2_img;
    private String lesson2;
    private String lesson3_img;
    private String lesson3;

    public Units() {
    }

    public Units(String id, String unit_name, String unit_img, String lesson1_img, String lesson1, String lesson2_img, String lesson2, String lesson3_img, String lesson3) {
        this.id = id;
        this.unit_name = unit_name;
        this.unit_img = unit_img;
        this.lesson1_img = lesson1_img;
        this.lesson1 = lesson1;
        this.lesson2_img = lesson2_img;
        this.lesson2 = lesson2;
        this.lesson3_img = lesson3_img;
        this.lesson3 = lesson3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getUnit_img() {
        return unit_img;
    }

    public void setUnit_img(String unit_img) {
        this.unit_img = unit_img;
    }

    public String getLesson1_img() {
        return lesson1_img;
    }

    public void setLesson1_img(String lesson1_img) {
        this.lesson1_img = lesson1_img;
    }

    public String getLesson1() {
        return lesson1;
    }

    public void setLesson1(String lesson1) {
        this.lesson1 = lesson1;
    }

    public String getLesson2_img() {
        return lesson2_img;
    }

    public void setLesson2_img(String lesson2_img) {
        this.lesson2_img = lesson2_img;
    }

    public String getLesson2() {
        return lesson2;
    }

    public void setLesson2(String lesson2) {
        this.lesson2 = lesson2;
    }

    public String getLesson3_img() {
        return lesson3_img;
    }

    public void setLesson3_img(String lesson3_img) {
        this.lesson3_img = lesson3_img;
    }

    public String getLesson3() {
        return lesson3;
    }

    public void setLesson3(String lesson3) {
        this.lesson3 = lesson3;
    }
}
