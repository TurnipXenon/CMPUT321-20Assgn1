package com.manuba.cardiobook.database;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Converts non-primitive data types for AppDatabase
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? 0 : date.getTime();
    }
}
