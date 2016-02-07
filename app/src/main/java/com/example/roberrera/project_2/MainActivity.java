package com.example.roberrera.project_2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Classes.Neighborhood;
import Classes.NeighborhoodSQLOpenHelper;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    ListView mFavesListView;
    ArrayAdapter<Neighborhood> mFavesAdapter;
    ArrayList<Neighborhood> mFavesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Neighborhood Search");

        // Creating list view of favorites, to appear in navigation drawer.
        mFavesListView = (ListView) findViewById(R.id.favorites_list);
        mFavesAdapter = new ArrayAdapter<Neighborhood>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                mFavesArray);

        // Create helper object and make the database available to be read.
        NeighborhoodSQLOpenHelper helper = new NeighborhoodSQLOpenHelper(MainActivity.this);
        helper.getReadableDatabase();

        // Adding entries to the database
        helper.addPlace("General Assembly", DetailsActivity.mGADesc, "10 E. 21st Street\nNew York, NY", 0);
        helper.addPlace("Eataly", DetailsActivity.mEatalyDesc, "200 Fifth Avenue\nNew York, NY", 0);
        helper.addPlace("Starbucks", DetailsActivity.mStarbucksDesc, "14 W. 24th Street\nNew York, NY", 0);


        mListView = (ListView) findViewById(R.id.searchResultsListView);

        final Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(MainActivity.this).getNeighborhoodList();
        CursorAdapter cursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.neighborhood_list_item,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView searchTextView = (TextView)view.findViewById(R.id.place_name_text_view);
                searchTextView.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)));
            }
        };

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (MainActivity.this, DetailsActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_ID)));
                startActivity(intent);
            }
        });
    }
}
