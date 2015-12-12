package com.it2107.assignment.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MemoryListActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.listView);

        final ArrayList<String> total = this.getIntent().getStringArrayListExtra("total");
        Collections.reverse(total);

        final ArrayList<String> calculations = this.getIntent().getStringArrayListExtra("calculation");
        Collections.reverse(calculations);

        String[] calculationsArr = new String[calculations.size()];
        String[] result = new String[total.size()];

        calculations.toArray(calculationsArr);
        total.toArray(result);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.customtv, result);

        // Assign adapter to ListView
        lv.setAdapter(adapter);

        // ListView Item Click Listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(MemoryListActivity.this, MainActivity.class);

                intent.putExtra("total", total.get(position));
                intent.putExtra("calculation", calculations.get(position));

                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });

    }

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
        return super.onOptionsItemSelected(item);
    }
}
