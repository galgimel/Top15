package org.example;


import java.util.Date;

public class Racer implements Comparable<Racer> {
    String name;
    String car;
    long time;
    Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Racer(String name, String car, long time) {
        this.name = name;
        this.car = car;
        this.time = time;
        this.date = new Date();
        this.date.setTime(time);
    }

    @Override
    public int compareTo(Racer o) {
        int result;

        if (this.time > o.time) {
            result = 1;
        } else if (this.time < o.time) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }
}