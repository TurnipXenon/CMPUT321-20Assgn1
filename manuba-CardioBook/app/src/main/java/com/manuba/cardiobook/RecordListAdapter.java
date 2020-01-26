package com.manuba.cardiobook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecordListAdapter extends RecyclerView.Adapter<RecordListAdapter.StandardViewHolder> {
    List<CardiacRecord> dataset;

    static class StandardViewHolder extends RecyclerView.ViewHolder {
        TextView textDate;
        TextView textTime;
        TextView textSystolic;
        TextView textDiastolic;
        TextView textHeartRate;
        TextView textComment;

        public StandardViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.item_main_list_text_date);
            textTime = itemView.findViewById(R.id.item_main_list_text_time);
            textSystolic = itemView.findViewById(R.id.item_main_list_text_systolic);
            textDiastolic = itemView.findViewById(R.id.item_main_list_text_diastolic);
            textHeartRate = itemView.findViewById(R.id.item_main_list_text_heart_rate);
            textComment = itemView.findViewById(R.id.item_main_list_text_comment);
        }
    }

    public RecordListAdapter(@NonNull List<CardiacRecord> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public StandardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_main_list, parent, false
        );
        return new StandardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandardViewHolder holder, int position) {
        CardiacRecord cardiacRecord = dataset.get(position);

        holder.textDate.setText("Date" + cardiacRecord.getDate());
        holder.textTime.setText("Time: " + cardiacRecord.getTime());
        holder.textSystolic.setText("Systolic: " + cardiacRecord.getSystolicPressure());
        holder.textDiastolic.setText("Diastolic: " + cardiacRecord.getDiastolicPressure());
        holder.textHeartRate.setText("Heart rate: " + cardiacRecord.getHeartRate());
        holder.textComment.setText("Comment: " + cardiacRecord.getComment());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
