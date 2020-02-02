package com.manuba.cardiobook.database;

import android.app.Application;

import com.manuba.cardiobook.database.AppDatabase;
import com.manuba.cardiobook.database.CardiacRecord;
import com.manuba.cardiobook.database.CardiacRecordDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * Gives the view models the appropriate data from the appropriate sources.
 * It's unneeded for this project, but the experience of knowing how to use this
 * may come useful in the future.
 */
public class CardiacRecordRepository {
    private CardiacRecordDao cardiacRecordDao;
    private LiveData<List<CardiacRecord>> allWords;

    CardiacRecordRepository(@NonNull Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        cardiacRecordDao = db.cardiacRecordDao();
        allWords = cardiacRecordDao.getAll();
    }

    public LiveData<List<CardiacRecord>> getAllCardiacRecords() {
        return allWords;
    }

    public LiveData<CardiacRecord> getCardiacRecord(long index) {
        return cardiacRecordDao.getCardiacRecord(index);
    }

    public void insert(@NonNull final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.insert(cardiacRecord);
            }
        });
    }

    public void update(@NonNull final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.update(cardiacRecord);
            }
        });
    }

    public void delete(@NonNull final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.delete(cardiacRecord);
            }
        });
    }
}
