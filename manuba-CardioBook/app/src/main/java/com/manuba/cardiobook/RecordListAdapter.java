package com.manuba.cardiobook;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manuba.cardiobook.database.CardiacRecord;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.StandardViewHolder> {

    private List<CardiacRecord> dataset;
    private View.OnClickListener onClickListener;

    static class StandardViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textSystolic;
        TextView textDiastolic;
        TextView textHeartRate;
        TextView textComment;

        StandardViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.text_date);
            textTime = itemView.findViewById(R.id.text_time);
            textSystolic = itemView.findViewById(R.id.text_systolic);
            textDiastolic = itemView.findViewById(R.id.text_diastolic);
            textHeartRate = itemView.findViewById(R.id.text_heart_rate);
            textComment = itemView.findViewById(R.id.text_comment);
        }
    }

    public RecordListAdapter(View.OnClickListener listener) {
        this.dataset = new ArrayList<>();
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public StandardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_main_list, parent, false
        );
        view.setOnClickListener(onClickListener);
        return new StandardViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StandardViewHolder holder, int position) {
        CardiacRecord cardiacRecord = dataset.get(position);

        holder.textDate.setText(cardiacRecord.getDate());
        holder.textTime.setText(cardiacRecord.getTime());
        holder.textSystolic.setText(Integer.toString(cardiacRecord.getSystolicPressure()));
        holder.textDiastolic.setText(Integer.toString(cardiacRecord.getDiastolicPressure()));
        holder.textHeartRate.setText(Integer.toString(cardiacRecord.getHeartRate()));
        holder.textComment.setText(cardiacRecord.getComment());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    void setCardiacRecords(List<CardiacRecord> records) {
        dataset = records;
        notifyDataSetChanged();
    }

    public CardiacRecord getItem(int position) {
        // todo put validation
        return dataset.get(position);
    }
}
