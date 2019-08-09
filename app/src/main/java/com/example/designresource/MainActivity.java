package com.example.designresource;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ListDrawFragment.ISelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(this.getString(R.string.name_app));
        setContentView(R.layout.activity_main);

        ListDrawFragment content = new ListDrawFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, content).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setTitle(this.getString(R.string.name_app));
    }

    @Override
    public void classSelected(int select) {

        String[] classTittles = getResources().getStringArray(R.array.tittles);
        setTitle(classTittles[select]);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, DrawFragment.getInstance(select)).addToBackStack(null).commit();
    }

}