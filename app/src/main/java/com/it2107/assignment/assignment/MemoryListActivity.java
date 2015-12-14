package com.it2107.assignment.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MemoryListActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ListView lv;
    ArrayList<String> total = new ArrayList<>();
    ArrayList<String> calculations = new ArrayList<>();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = (ListView) findViewById(R.id.listView);

        total = this.getIntent().getStringArrayListExtra("total");
        calculations = this.getIntent().getStringArrayListExtra("calculations");
        Collections.reverse(total);
        Collections.reverse(calculations);
        for (String i : total) {
            Log.d("lel", i);
        }
        adapter = new ArrayAdapter<String>(this,
                R.layout.customtv, total);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                intent = MemoryListActivity.this.getIntent();
                intent.putExtra("total", total.get(position));
                intent.putExtra("calculations", calculations.get(position));
                intent.putStringArrayListExtra("totalArr", total);
                intent.putStringArrayListExtra("calculationsArr", calculations);
                for (String i : calculations) {
                    Log.d("lel", i);
                }

                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });
        registerForContextMenu(lv);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memory_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        setResult(Activity.RESULT_OK, intent);
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_clear) {
            total.clear();
            calculations.clear();
            adapter.clear();
            intent = new Intent(MemoryListActivity.this, MainActivity.class);
            setResult(Activity.RESULT_FIRST_USER, intent);
            adapter.notifyDataSetChanged();
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        Snackbar.make(findViewById(android.R.id.content), calculations.get(index) + " = " + total.get(index) + " has been deleted!", Snackbar.LENGTH_LONG)
                .show();
        total.remove(index);
        calculations.remove(index);
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public void onBackPressed() {
        intent = MemoryListActivity.this.getIntent();
        intent.putStringArrayListExtra("total", total);
        intent.putStringArrayListExtra("calculations", calculations);
        setResult(3, intent);
        finish();
    }

}
