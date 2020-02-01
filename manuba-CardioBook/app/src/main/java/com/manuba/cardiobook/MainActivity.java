package com.manuba.cardiobook;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.manuba.cardiobook.database.CardiacRecord;
import com.manuba.cardiobook.database.CardiacRecordViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private final static int INVALID_INDEX = -1;

    private RecyclerView recyclerView;
    private RecordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.main_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        CardiacRecordViewModel viewModel = CardiacRecordViewModel.create(getApplication());
        viewModel.getAllRecords().observe(this, new Observer<List<CardiacRecord>>() {
            @Override
            public void onChanged(List<CardiacRecord> cardiacRecords) {
                adapter.setCardiacRecords(cardiacRecords);
            }
        });

        adapter = new RecordListAdapter(this);
        recyclerView.setAdapter(adapter);

        // todo: remove
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // goes to record activity without any records to edit
                goToEditRecordActivity(INVALID_INDEX);
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
    public void onClick(final View view) {
        int position = recyclerView.getChildLayoutPosition(view);
        goToEditRecordActivity(position);
    }

    private void goToEditRecordActivity(int position) {
        Intent intent = new Intent(MainActivity.this, EditRecordActivity.class);

        if (position != INVALID_INDEX) {
            CardiacRecord cardiacRecord = adapter.getItem(position);

            if (cardiacRecord != null) {
                Bundle bundle = new Bundle();
                bundle.putLong(EditRecordActivity.CARDIAC_RECORD_KEY, cardiacRecord.getCrid());
                intent.putExtras(bundle);
            }
        }

        startActivity(intent);
    }
}
