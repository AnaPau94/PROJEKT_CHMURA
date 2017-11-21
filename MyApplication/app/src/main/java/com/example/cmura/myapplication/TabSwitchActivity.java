package com.example.cmura.myapplication;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class TabSwitchActivity extends AppCompatActivity {

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_switch);
        initializeTabHoster();
    }

    private void initializeTabHoster() {
        tabHost = findViewById(R.id.tab_host);
        //Important
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab Tag");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab Tag");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab Tag");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("Forth tab Tag");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected

        tab1.setContent(R.id.list_view_books_to_buy);

        tab2.setContent(R.id.list_view_book_to_read);

        tab3.setContent(R.id.list_view_read_books);

        tab4.setContent(R.id.list_view_read_books);

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }
}
