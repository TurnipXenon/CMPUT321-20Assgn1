package com.manuba.cardiobook;

import android.app.Application;

import java.util.List;

import androidx.core.graphics.drawable.WrappedDrawable;
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

    void insert(CardiacRecord cardiacRecord) {
        cardiacRecordDao.insert(cardiacRecord);
    }
}
