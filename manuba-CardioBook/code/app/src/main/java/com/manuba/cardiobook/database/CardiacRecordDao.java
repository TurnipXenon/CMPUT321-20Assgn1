package com.manuba.cardiobook.database;

import com.manuba.cardiobook.database.CardiacRecord;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Provides database interactions related to CardiacRecords
 */
@Dao
public interface CardiacRecordDao {
    @Query("SELECT * FROM cardiac_record")
    LiveData<List<CardiacRecord>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CardiacRecord cardiacRecord);

    @Query("SELECT * FROM cardiac_record WHERE cr_id = :crid")
    LiveData<CardiacRecord> getCardiacRecord(long crid);

    @Update
    void update(CardiacRecord cardiacRecord);

    @Delete
    void delete(CardiacRecord cardiacRecord);
}