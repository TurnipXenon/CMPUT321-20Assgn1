package com.manuba.cardiobook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cardiac_record")
public class CardiacRecord implements Parcelable {
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

    public CardiacRecord(@NonNull Date dateTime,
                         int systolicPressure,
                         int diastolicPressure,
                         int heartRate,
                         String comment) {
        this.dateTime = dateTime;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(crid);
        dest.writeLong(dateTime.getTime());
        dest.writeInt(systolicPressure);
        dest.writeInt(diastolicPressure);
        dest.writeInt(heartRate);
        dest.writeString(comment);
    }

    public static final Parcelable.Creator<CardiacRecord> CREATOR
            = new Parcelable.Creator<CardiacRecord>() {

        @Override
        public CardiacRecord createFromParcel(Parcel source) {
            return  new CardiacRecord(source);
        }

        @Override
        public CardiacRecord[] newArray(int size) {
            return new CardiacRecord[size];
        }
    };

    private CardiacRecord(Parcel in) {
        crid = in.readLong();
        dateTime = new Date(in.readLong());
        systolicPressure = in.readInt();
        diastolicPressure = in.readInt();
        heartRate = in.readInt();
        comment = in.readString();
    }
}
