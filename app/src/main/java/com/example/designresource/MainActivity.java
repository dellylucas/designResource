package com.example.designresource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListDrawFragment.ISelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListDrawFragment content = new ListDrawFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, content).commit();
    }

    @Override
    public void classSelected(int select) {
        // getSupportFragmentManager().beginTransaction().replace(R.id.container, DrawFragment.getInstance(select)).addToBackStack(null).commit();
    }

}