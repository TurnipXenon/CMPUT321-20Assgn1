package com.manuba.cardiobook.database;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

/**
 * Gives the view models the appropriate data from the appropriate sources.
 * It's unneeded for this project, but the experience of knowing how to use this
 * may come useful in the future.
 */
class CardiacRecordRepository {
    private CardiacRecordDao cardiacRecordDao;
    private LiveData<List<CardiacRecord>> allRecords;

    CardiacRecordRepository(@NonNull Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        cardiacRecordDao = db.cardiacRecordDao();
        allRecords = cardiacRecordDao.getAll();
    }

    LiveData<List<CardiacRecord>> getAllCardiacRecords() {
        return allRecords;
    }

    LiveData<CardiacRecord> getCardiacRecord(long index) {
        return cardiacRecordDao.getCardiacRecord(index);
    }

    void insert(@NonNull final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.insert(cardiacRecord);
            }
        });
    }

    void update(@NonNull final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.update(cardiacRecord);
            }
        });
    }

    void delete(@NonNull final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.delete(cardiacRecord);
            }
        });
    }
}
