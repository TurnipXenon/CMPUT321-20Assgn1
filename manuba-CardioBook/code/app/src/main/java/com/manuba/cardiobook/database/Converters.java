package com.manuba.cardiobook.database;

import java.util.Date;

import androidx.room.TypeConverter;

/**
 * Converts non-primitive data types for AppDatabase
 */
class Converters {
    @TypeConverter
    static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    static Long dateToTimestamp(Date date) {
        return date == null ? 0 : date.getTime();
    }
}
