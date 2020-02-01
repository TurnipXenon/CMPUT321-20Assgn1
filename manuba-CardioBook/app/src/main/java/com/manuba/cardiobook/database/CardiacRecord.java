package com.manuba.cardiobook.database;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
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
    @ColumnInfo(name = "comment")
    private String comment;

    @Ignore
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    @Ignore
    public final static String TIME_FORMAT = "HH:mm";

    @Ignore
    public static final int COMMENT_LIMIT = 20;

    public CardiacRecord() {}

    @Ignore
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
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);
        try {
            Date newDate = dateFormat.parse(dateInput);
            Calendar inputCalendar = Calendar.getInstance();
            inputCalendar.setTime(newDate);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(dateTime);
            currentCalendar.set(Calendar.YEAR, inputCalendar.get(Calendar.YEAR));
            currentCalendar.set(Calendar.MONTH, inputCalendar.get(Calendar.MONTH));
            currentCalendar.set(Calendar.DAY_OF_MONTH, inputCalendar.get(Calendar.DAY_OF_MONTH));
        } catch (ParseException ignore) {
            Log.w("Custom", "setDate: Invalid date format");
        }
    }

    public String getDate() {
        if (dateTime != null) {
            DateFormat dateFormat = DateFormat.getDateInstance();
            return dateFormat.format(dateTime);
        } else {
            // room does something that calls this function
            return "";
        }
    }

    public void setTime(String timeInput) {
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT, Locale.US);
        try {
            Date newDate = timeFormat.parse(timeInput);
            Calendar inputCalendar = Calendar.getInstance();
            inputCalendar.setTime(newDate);
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(dateTime);
            currentCalendar.set(Calendar.HOUR_OF_DAY, inputCalendar.get(Calendar.HOUR_OF_DAY));
            currentCalendar.set(Calendar.MINUTE, inputCalendar.get(Calendar.MINUTE));
        } catch (ParseException ignore) {
            Log.w("Custom", "setTime: Invalid time format");
        }
    }

    public String getTime() {
        if (dateTime != null) {
            DateFormat timeFormat = DateFormat.getTimeInstance();
            return timeFormat.format(dateTime);
        } else {
            // room does something that calls this function
            return "";
        }
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        if (systolicPressure >= 0) {
            this.systolicPressure = systolicPressure;
        } else {
            Log.w("Custom", "setSystolicPressure: only non-negative systolic pressures");
        }
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        if (diastolicPressure >= 0) {
            this.diastolicPressure = diastolicPressure;
        } else {
            Log.w("Custom", "setDiastolicPressure: only non-negative diastolic pressures");
        }
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        if (heartRate >= 0) {
            this.heartRate = heartRate;
        } else {
            Log.w("Custom", "setHeartRate: only non-negative heart rates");
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCrid() { return crid; }

    // warning: don't use this; only needed for room
    public void setCrid(long crid) {
        this.crid = crid;
    }

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
