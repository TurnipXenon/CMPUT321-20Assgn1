package com.manuba.cardiobook;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cardiac_record")
public class CardiacRecord {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cr_id")
    private long crid;

    @ColumnInfo(name = "date_time")
    private Date dateTime;

    @ColumnInfo(name = "systolic")
    private int systolicPressure;

    @ColumnInfo(name = "diastolic")
    private int diastolicPressure;

    @ColumnInfo(name = "heart_rate")
    private int heartRate;

    @Nullable
    @ColumnInfo(name = "commment")
    private String comment;

    public CardiacRecord() {}

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

    public long getCrid() { return crid; }

    public void setCrid(long crid) { this.crid = crid; }

    public Date getDateTime() { return dateTime; }

    public void setDateTime(Date date) { this.dateTime = date; }
}
