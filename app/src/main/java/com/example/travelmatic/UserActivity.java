package com.example.travelmatic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class UserActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        toolbar = findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.main_activity_tool_bar_action);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.new_travel:
                        startActivity(new Intent(getApplicationContext(),NewTravelDetailsActivity.class));
                }
                return false;
            }
        });

    }
}
