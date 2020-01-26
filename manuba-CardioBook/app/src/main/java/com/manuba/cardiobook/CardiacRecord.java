package com.manuba.cardiobook;

import java.util.Date;

import androidx.annotation.NonNull;

public class CardiacRecord {
    private Date dateTime;
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private String comment;

    public CardiacRecord(@NonNull String date,
                         @NonNull String time,
                         int systolicPressure,
                         int diastolicPressure,
                         int heartRate,
                         String comment) {
        setDate(date);
        setTime(time);
        setSystolicPressure(systolicPressure);
        setDiastolicPressure(diastolicPressure);
        setHeartRate(heartRate);
        setComment(comment);
    }

    public void setDate(String dateInput) {
        // todo: implement
    }

    public String getDate() {
        // todo: implement
        return "";
    }

    public void setTime(String timeInput) {
        // todo: implement
    }

    public String getTime() {
        // todo: implement
        return "";
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
