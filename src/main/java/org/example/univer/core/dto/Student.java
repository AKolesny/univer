package org.example.univer.core.dto;

import java.util.Objects;

/**
 * 	1.1 Порядковый номер (int)
 * 	1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 	1.3 возраст (7-17)
 * 	1.4 оценка(0.0-10.0)
 * 	1.5 признак участия в олимпиадах (bool).
 */
public class Student{

    private long id;
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public void setOlympicGamer(boolean olympicGamer) {
        this.olympicGamer = olympicGamer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", olympicGamer=" + olympicGamer +
                '}';
    }
}
