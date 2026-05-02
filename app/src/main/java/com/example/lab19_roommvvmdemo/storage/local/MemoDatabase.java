package com.example.lab19_roommvvmdemo.storage.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Memo.class}, version = 1, exportSchema = false)
public abstract class MemoDatabase extends RoomDatabase {

    public abstract MemoDao memoDao();

    private static volatile MemoDatabase dbInstance;

    public static MemoDatabase getInstance(Context ctx) {
        if (dbInstance == null) {
            synchronized (MemoDatabase.class) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(
                            ctx.getApplicationContext(),
                            MemoDatabase.class,
                            "memo_db"
                    ).fallbackToDestructiveMigration().build();
                }
            }
        }
        return dbInstance;
    }
}