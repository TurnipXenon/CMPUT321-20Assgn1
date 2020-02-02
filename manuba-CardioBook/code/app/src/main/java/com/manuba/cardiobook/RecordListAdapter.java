package com.manuba.cardiobook;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manuba.cardiobook.database.CardiacRecord;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Controls the interaction between the RecyclerView
 * and the list of cardiac record
 */
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

        View layoutSystolic;
        View layoutDiastolic;
        View layoutComment;
        TextView labelSystolic;
        TextView labelDiastolic;

        StandardViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.text_date);
            textTime = itemView.findViewById(R.id.text_time);
            textSystolic = itemView.findViewById(R.id.text_systolic);
            textDiastolic = itemView.findViewById(R.id.text_diastolic);
            textHeartRate = itemView.findViewById(R.id.text_heart_rate);
            textComment = itemView.findViewById(R.id.text_comment);

            layoutSystolic = itemView.findViewById(R.id.layout_systolic);
            layoutDiastolic = itemView.findViewById(R.id.layout_diastolic);
            layoutComment = itemView.findViewById(R.id.label_comment);

            labelSystolic = itemView.findViewById(R.id.label_systolic);
            labelDiastolic = itemView.findViewById(R.id.label_diastolic);
        }

        void setCommentFromCardiac(@NonNull CardiacRecord cardiacRecord) {
            String comment = cardiacRecord.getComment();
            if (comment != null && !comment.isEmpty()) {
                layoutComment.setVisibility(View.VISIBLE);
                textComment.setText(comment);
                textComment.setVisibility(View.VISIBLE);
            } else {
                layoutComment.setVisibility(View.GONE);
                textComment.setVisibility(View.GONE);
            }
        }

        @SuppressLint("SetTextI18n")
        void setPressure(@NonNull CardiacRecord cardiacRecord, CardiacRecord.PressureType pressureType){
            boolean isNormal;
            int value;
            View layout;
            TextView textView;
            TextView label;

            switch (pressureType) {
                case Systolic:
                    isNormal = cardiacRecord.isPressureNormal(CardiacRecord.PressureType.Systolic);
                    Log.d("Warning", "setPressure: " + isNormal);
                    value = cardiacRecord.getSystolicPressure();
                    layout = layoutSystolic;
                    textView = textSystolic;
                    label = labelSystolic;
                    break;
                case Diastolic:
                    isNormal = cardiacRecord.isPressureNormal(CardiacRecord.PressureType.Diastolic);
                    value = cardiacRecord.getDiastolicPressure();
                    layout = layoutDiastolic;
                    textView = textDiastolic;
                    label = labelDiastolic;
                    break;
                default:
                    Log.w("Custom", "setPressure: Unknown pressure: " + pressureType.toString());
                    return;
            }

            textView.setText(Integer.toString(value));

            Log.d("Custom", "setPressure: " + isNormal);
            if (isNormal) {
                layout.setBackgroundColor(Color.WHITE);
                label.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else {
                layout.setBackgroundColor(Color.RED);
                label.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_warning_black_24dp, 0, 0, 0);
            }
        }
    }

    RecordListAdapter(View.OnClickListener listener) {
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

        holder.setPressure(cardiacRecord, CardiacRecord.PressureType.Systolic);
        holder.setPressure(cardiacRecord, CardiacRecord.PressureType.Diastolic);

        holder.textHeartRate.setText(Integer.toString(cardiacRecord.getHeartRate()));

        holder.setCommentFromCardiac(cardiacRecord);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    void setCardiacRecords(List<CardiacRecord> records) {
        dataset = records;
        notifyDataSetChanged();
    }

    CardiacRecord getItem(int position) {
        if ((0 <= position) && (position < getItemCount())) {
            return dataset.get(position);
        } else {
            return null;
        }
    }
}
