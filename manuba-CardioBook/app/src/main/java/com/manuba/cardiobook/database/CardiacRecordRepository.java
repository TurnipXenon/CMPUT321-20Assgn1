package com.manuba.cardiobook.database;

import android.app.Application;

import com.manuba.cardiobook.database.AppDatabase;
import com.manuba.cardiobook.database.CardiacRecord;
import com.manuba.cardiobook.database.CardiacRecordDao;

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

    public LiveData<List<CardiacRecord>> getAllCardiacRecords() {
        return allWords;
    }

    public LiveData<CardiacRecord> getCardiacRecord(long index) {
        return cardiacRecordDao.getCardiacRecord(index);
    }

    public void insert(final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.insert(cardiacRecord);
            }
        });
    }

    public void update(final CardiacRecord cardiacRecord) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                cardiacRecordDao.update(cardiacRecord);
            }
        });
    }
}