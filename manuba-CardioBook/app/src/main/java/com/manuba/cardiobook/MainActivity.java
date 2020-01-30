package com.manuba.cardiobook;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_RECORD_ACTIVITY_REQUEST_CODE = 1;
    public static final String EXTRA_RECORD = "com.manuba.cardiobook.RECORD";

    private RecyclerView recyclerView;
    private RecordListAdapter adapter;
    private CardiacRecordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.main_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(
                getApplication()).create(CardiacRecordViewModel.class);
        viewModel.getAllRecords().observe(this, new Observer<List<CardiacRecord>>() {
            @Override
            public void onChanged(List<CardiacRecord> cardiacRecords) {
                adapter.setCardiacRecords(cardiacRecords);
            }
        });

        ArrayList<CardiacRecord> records = new ArrayList<>();
        records.add(new CardiacRecord("", "", 1, 1, 1, "Yeah"));

        adapter = new RecordListAdapter(records);
        recyclerView.setAdapter(adapter);

        // todo: remove
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewRecordActivity.class);
                startActivityForResult(intent, NEW_RECORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_RECORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK
            && data != null && data.getExtras() != null) {
            // todo: do your stuff here
             CardiacRecord cardiacRecord = data.getExtras().getParcelable(EXTRA_RECORD);
            Log.d("Potato", "onActivityResult: ");
             viewModel.insert(cardiacRecord);
            Toast.makeText(
                    getApplicationContext(),
                    R.string.save_successful,
                    Toast.LENGTH_LONG
            ).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
