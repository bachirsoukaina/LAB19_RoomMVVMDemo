package com.example.lab19_roommvvmdemo.storage;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lab19_roommvvmdemo.storage.local.Memo;
import com.example.lab19_roommvvmdemo.storage.local.MemoDao;
import com.example.lab19_roommvvmdemo.storage.local.MemoDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MemoStore {

    private final MemoDao memoDao;
    private final LiveData<List<Memo>> memoList;
    private final ExecutorService bgRunner;

    public MemoStore(Application app) {
        MemoDatabase db = MemoDatabase.getInstance(app);
        memoDao = db.memoDao();
        memoList = memoDao.fetchAll();
        bgRunner = Executors.newSingleThreadExecutor();
    }

    public void saveMemo(Memo memo) {
        bgRunner.execute(() -> memoDao.addMemo(memo));
    }

    public void eraseMemo(Memo memo) {
        bgRunner.execute(() -> memoDao.removeMemo(memo));
    }

    public void eraseAll() {
        bgRunner.execute(memoDao::clearAll);
    }

    public LiveData<List<Memo>> getMemoList() {
        return memoList;
    }
}