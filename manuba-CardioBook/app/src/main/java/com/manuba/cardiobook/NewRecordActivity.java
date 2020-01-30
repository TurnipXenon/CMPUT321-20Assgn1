package com.manuba.cardiobook;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewRecordActivity extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private final static String DATE_PATTERN = "";
    private final static int INVALID_VALUE = -1;

    TextView textDate;
    TextView textTime;
    EditText textSystolic;
    EditText textDiastolic;
    EditText textHeartRate;
    EditText textComment;

    TextInputLayout inputLayoutSystolic;
    TextInputLayout inputLayoutDiastolic;
    TextInputLayout inputLayoutHeartRate;

    private CardiacRecordViewModel viewModel;

    private Calendar calendar;
    private int systolic;
    private int diastolic;
    private int heartRate;
    private String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textDate = findViewById(R.id.new_record_text_date);
        textTime = findViewById(R.id.new_record_text_time);
        textSystolic = findViewById(R.id.new_record_editText_systolic);
        textDiastolic = findViewById(R.id.new_record_editText_diastolic);
        textHeartRate = findViewById(R.id.new_record_editText_heart_rate);
        textComment = findViewById(R.id.new_record_editText_comment);

        inputLayoutSystolic = findViewById(R.id.new_record_text_systolic);
        inputLayoutDiastolic = findViewById(R.id.new_record_text_diastolic);
        inputLayoutHeartRate = findViewById(R.id.new_record_text_heart_rate);

        // init pickers
        calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        updateDateTime();

        textDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewRecordActivity.this, NewRecordActivity.this,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        textTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        NewRecordActivity.this, NewRecordActivity.this,
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), false);
                timePickerDialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            if (!areInputsValid()) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
            }

            CardiacRecord record = new CardiacRecord(
                    new Date(calendar.getTimeInMillis()),
                    systolic,
                    diastolic,
                    heartRate,
                    comment);
            Intent intent = new Intent();
            intent.putExtra(MainActivity.EXTRA_RECORD, record);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        updateDateTime();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH, month);
        updateDateTime();
    }

    int parseIntFromEditText(EditText editText) {
        String text = editText.getText().toString();
        int value = INVALID_VALUE;
        try {
            value = Integer.parseInt(text);
        } catch (NumberFormatException ignored) {

        } catch (Exception ex) {
            Log.e("Custom", "parseIntFromEditText: " + ex.getMessage());
        }

        return value;
    }

    /**
     * Reversing the name and function would be a lot more confusing.
     * @param inputLayout
     * @param value
     * @return
     */
    private boolean validateDigit(TextInputLayout inputLayout, int value) {
        if (value == INVALID_VALUE) {
            Log.d("Custom", "validateDigit: " + value);
            inputLayout.setError("Invalid value");
            return false;
        } else {
            inputLayout.setError(null);
            return true;
        }
    }

    private boolean areInputsValid() {
        systolic = parseIntFromEditText(textSystolic);
        if (!validateDigit(inputLayoutSystolic, systolic)) {
            return false;
        }

        diastolic = parseIntFromEditText(textDiastolic);
        if (!validateDigit(inputLayoutDiastolic, diastolic)) {
            return false;
        }

        heartRate = parseIntFromEditText(textHeartRate);
        if (!validateDigit(inputLayoutHeartRate, heartRate)) {
            return false;
        }

        comment = textComment.getText().toString();
        return comment.length() <= CardiacRecord.COMMENT_LIMIT;
    }

    private void updateDateTime() {
        Date date = new Date(calendar.getTimeInMillis());

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat(CardiacRecord.DATE_PATTERN);
        textDate.setText(dateFormat.format(date));

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat(CardiacRecord.TIME_FORMAT);
        textTime.setText(timeFormat.format(date));
    }
}