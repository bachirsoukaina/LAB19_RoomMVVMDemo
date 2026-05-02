package com.example.lab19_roommvvmdemo.logic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab19_roommvvmdemo.storage.MemoStore;
import com.example.lab19_roommvvmdemo.storage.local.Memo;

import java.util.List;

public class MemoViewModel extends AndroidViewModel {

    private final MemoStore store;
    private final LiveData<List<Memo>> memoList;

    public MemoViewModel(@NonNull Application application) {
        super(application);
        store = new MemoStore(application);
        memoList = store.getMemoList();
    }

    public void saveMemo(Memo memo) { store.saveMemo(memo); }
    public void eraseMemo(Memo memo) { store.eraseMemo(memo); }
    public void eraseAll() { store.eraseAll(); }
    public LiveData<List<Memo>> getMemoList() { return memoList; }
}