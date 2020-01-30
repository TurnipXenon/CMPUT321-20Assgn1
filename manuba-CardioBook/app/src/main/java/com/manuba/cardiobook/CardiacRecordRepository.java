package com.manuba.cardiobook;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CardiacRecordRepository {
    private CardiacRecordDao cardiacRecordDao;
    private LiveData<List<CardiacRecord>> allWords;

    CardiacRecordRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        cardiacRecordDao = db.cardiacRecordDao();
        allWords = cardiacRecordDao.getAll();
    }

    LiveData<List<CardiacRecord>> getAllCardiacRecords() {
        return allWords;
    }

    void insert(final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.insert(cardiacRecord);
            }
        });
    }
}
