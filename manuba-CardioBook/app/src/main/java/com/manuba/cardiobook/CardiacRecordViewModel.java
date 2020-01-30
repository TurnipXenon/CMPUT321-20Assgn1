package com.manuba.cardiobook;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CardiacRecordViewModel extends AndroidViewModel {
    private  CardiacRecordRepository repository;

    private LiveData<List<CardiacRecord>> allRecords;

    public CardiacRecordViewModel(@NonNull Application application) {
        super(application);
        repository = new CardiacRecordRepository(application);
        allRecords = repository.getAllCardiacRecords();
    }

    LiveData<List<CardiacRecord>> getAllRecords() {
        return allRecords;
    }

    public void insert(CardiacRecord cardiacRecord) {
        repository.insert(cardiacRecord);
    }

    // todo: update
}
