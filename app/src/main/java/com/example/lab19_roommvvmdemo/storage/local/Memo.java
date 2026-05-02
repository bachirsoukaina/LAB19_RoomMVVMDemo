package com.example.lab19_roommvvmdemo.storage.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "memo_table")
public class Memo {

    @PrimaryKey(autoGenerate = true)
    private int memoId;

    private String heading;
    private String body;

    public Memo(String heading, String body) {
        this.heading = heading;
        this.body = body;
    }

    public int getMemoId() { return memoId; }
    public void setMemoId(int memoId) { this.memoId = memoId; }
    public String getHeading() { return heading; }
    public String getBody() { return body; }
}