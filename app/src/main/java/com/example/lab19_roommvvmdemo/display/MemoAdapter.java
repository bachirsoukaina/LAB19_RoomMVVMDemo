package com.example.lab19_roommvvmdemo.display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab19_roommvvmdemo.R;
import com.example.lab19_roommvvmdemo.storage.local.Memo;

import java.util.ArrayList;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.MemoCell> {

    private List<Memo> memoList = new ArrayList<>();
    private TapListener tapListener;
    private HoldListener holdListener;

    public interface TapListener { void onTap(Memo memo); }
    public interface HoldListener { void onHold(Memo memo); }

    public void refreshList(List<Memo> incoming) {
        this.memoList = incoming;
        notifyDataSetChanged();
    }

    public void setTapListener(TapListener l) { this.tapListener = l; }
    public void setHoldListener(HoldListener l) { this.holdListener = l; }

    @NonNull
    @Override
    public MemoCell onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memo, parent, false);
        return new MemoCell(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemoCell cell, int position) {
        Memo m = memoList.get(position);
        cell.tvHeading.setText(m.getHeading());
        cell.tvBody.setText(m.getBody());
    }

    @Override
    public int getItemCount() { return memoList.size(); }

    class MemoCell extends RecyclerView.ViewHolder {
        TextView tvHeading, tvBody;

        public MemoCell(@NonNull View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvBody = itemView.findViewById(R.id.tvBody);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (tapListener != null && pos != RecyclerView.NO_POSITION)
                    tapListener.onTap(memoList.get(pos));
            });

            itemView.setOnLongClickListener(v -> {
                int pos = getAdapterPosition();
                if (holdListener != null && pos != RecyclerView.NO_POSITION) {
                    holdListener.onHold(memoList.get(pos));
                    return true;
                }
                return false;
            });
        }
    }
}