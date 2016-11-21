package com.example.nyp.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
// Tutorials at http://www.android-examples.com/android-recyclerview-with-gridview-gridlayoutmanager/
public class MainActivity extends AppCompatActivity {
    //Array of Text to be used in cards
    String[] numbers = {
            "I'm card 1",
            "I'm a card 2",
            "I'm a card 3",
            "I'm a card 4","I'm a card 5","I'm a card 6","I'm a card 7","I'm a card 8","I'm a card 9",
            "I'm a card 10","I'm a card 11","I'm a card 12","I'm a card 13 ","I'm a card 14","I'm a card 15",
            "I'm a card","I'm a card","I'm a card","I'm a card","I'm a card","I'm a card",
            "I'm a card","I'm a card","I'm a card","I'm a card","I'm a card","I'm a card",
            "I'm a card","I'm a card","I'm a card","I'm a card","I'm a card","I'm a card",
            "I'm a card","I'm a card","I'm a card","I'm a card","I'm a card","I'm a card",
            "I'm a card","I'm a card","I'm a card","I'm a card","I'm a card","I'm a card",
            "I'm a card","I'm a card","I'm a card","I'm a card","I'm a card",

    };


    @Override //Called on Creation(start) of the app
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);

        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        RecyclerView.Adapter recyclerViewAdapter = new RecyclerViewAdapter(context, numbers);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
