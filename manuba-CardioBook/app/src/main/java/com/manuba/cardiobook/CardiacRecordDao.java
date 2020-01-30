package com.manuba.cardiobook;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CardiacRecordDao {
    @Query("SELECT * FROM cardiac_record")
    LiveData<List<CardiacRecord>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CardiacRecord cardiacRecord);

    // todo: update
}