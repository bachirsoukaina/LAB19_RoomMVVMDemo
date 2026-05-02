package com.example.lab19_roommvvmdemo.storage.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemoDao {

    @Insert
    void addMemo(Memo memo);

    @Delete
    void removeMemo(Memo memo);

    @Query("DELETE FROM memo_table")
    void clearAll();

    @Query("SELECT * FROM memo_table ORDER BY memoId DESC")
    LiveData<List<Memo>> fetchAll();
}