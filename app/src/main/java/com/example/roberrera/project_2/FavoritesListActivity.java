package com.example.roberrera.project_2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import Classes.NeighborhoodSQLOpenHelper;

public class FavoritesListActivity extends AppCompatActivity {

    ListView mFavesListView;
    CursorAdapter mFavesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        setTitle("Favorites");

        final NeighborhoodSQLOpenHelper helper = new NeighborhoodSQLOpenHelper(FavoritesListActivity.this);
        helper.getReadableDatabase();

        // Cursor adapter setup
        final Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(FavoritesListActivity.this).getFavorites(1);
        mFavesAdapter = new CursorAdapter(FavoritesListActivity.this, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.list_neighborhood_layout,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView placeName = (TextView)view.findViewById(R.id.name_textView);
                TextView address = (TextView)view.findViewById(R.id.address_textView);
                ImageView image = (ImageView)view.findViewById(R.id.imageView_mainActivity);

                placeName.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)));
                address.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_ADDRESS)));
                image.setImageResource(helper.getDrawableValue(
                        cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)) ));
            }
        };

        mFavesListView = (ListView) findViewById(R.id.favorites_list_plain);
        mFavesListView.setAdapter(mFavesAdapter);

        // When user taps on an item in the list, move to DetailsActivity and refer to that list
        // item's database row based on its column ID.
        mFavesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = mFavesAdapter.getCursor();
                Intent intent = new Intent(FavoritesListActivity.this, DetailsActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_ID)));
                startActivity(intent);
            }
        });
    }
}
