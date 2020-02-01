package com.manuba.cardiobook.database;

import android.app.Application;
import android.util.Log;

import com.manuba.cardiobook.database.CardiacRecord;
import com.manuba.cardiobook.database.CardiacRecordRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

public class CardiacRecordViewModel extends AndroidViewModel {
    private CardiacRecordRepository repository;

    private LiveData<List<CardiacRecord>> allRecords;

    public CardiacRecordViewModel(@NonNull Application application) {
        super(application);
        repository = new CardiacRecordRepository(application);
        allRecords = repository.getAllCardiacRecords();
    }

    public static CardiacRecordViewModel create(Application application) {
        return new ViewModelProvider.AndroidViewModelFactory(
                application).create(CardiacRecordViewModel.class);
    }

    public LiveData<List<CardiacRecord>> getAllRecords() {
        return allRecords;
    }

    public LiveData<CardiacRecord> getRecord(long index) {
        return repository.getCardiacRecord(index);
    }

    public void insert(CardiacRecord cardiacRecord) {
        repository.insert(cardiacRecord);
    }

    public void update(CardiacRecord cardiacRecord) {
        repository.update(cardiacRecord);
    }
}
