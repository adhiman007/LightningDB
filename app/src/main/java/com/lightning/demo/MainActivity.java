package com.lightning.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.lightning.demo.adapters.UserAdapter;
import com.lightning.demo.pojo.UserTable;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private UserTable table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        table = new UserTable(getApplicationContext());
        listView.setAdapter(new UserAdapter(getApplicationContext(), table.get()));
    }
}
