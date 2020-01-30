package com.manuba.cardiobook;

import android.content.Context;

import com.manuba.cardiobook.CardiacRecord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

@Database(entities = {CardiacRecord.class}, version = 1, exportSchema = true)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardiacRecordDao cardiacRecordDao();

    private static volatile AppDatabase INSTANCE = null;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "cardiobook_database")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}