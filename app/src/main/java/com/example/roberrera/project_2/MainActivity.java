package com.example.roberrera.project_2;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import Classes.Neighborhood;
import Classes.NeighborhoodSQLOpenHelper;
import Setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {

    ListView mListView;
    CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets the title for the activity in the title bar, instead of using the filename.
        setTitle("Places!");

        // Refers the app to the database file in the assets folder.
        DBAssetHelper dbAssetHelper = new DBAssetHelper(MainActivity.this);
        dbAssetHelper.getReadableDatabase();

        // Cursor adapter setup
        final Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(MainActivity.this).getNeighborhoodList();
        mCursorAdapter = new CursorAdapter(MainActivity.this, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.list_neighborhood_layout,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                // Create helper object and make the database available to be read.
                NeighborhoodSQLOpenHelper helper = new NeighborhoodSQLOpenHelper(MainActivity.this);
                helper.getReadableDatabase();

                // Tell the adapter what text and images to set by referring to the database columns.
                TextView placeName = (TextView)view.findViewById(R.id.name_textView);
                placeName.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)));

                TextView address = (TextView)view.findViewById(R.id.address_textView);
                address.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_ADDRESS)));

                ImageView image = (ImageView)view.findViewById(R.id.imageView_mainActivity);
                image.setImageResource(Neighborhood.getDrawableValue(cursor.getString(
                        cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME))));
            }
        };

        mListView = (ListView) findViewById(R.id.searchResultsListView);
        mListView.setAdapter(mCursorAdapter);

        handleIntent(getIntent());


        // When user taps on an item in the list, move to DetailsActivity and refer to that list
        // item's database row based on its column ID.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = mCursorAdapter.getCursor();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                cursor.moveToPosition(position);
                intent.putExtra("id", cursor.getInt(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_ID)));
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar.
        getMenuInflater().inflate(R.menu.main, menu);

        // Setup for the search action.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();

        SearchableInfo info = searchManager.getSearchableInfo( getComponentName() );
        searchView.setSearchableInfo(info);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    public void handleIntent(Intent intent){

        // Run the search query on the search action so results will be returned.
        if (Intent.ACTION_SEARCH.equals( intent.getAction() )){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(this).searchPlaces(query);
            mCursorAdapter.swapCursor(cursor);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

            // The heart button opens the FavoritesListActivity.
            case R.id.action_faves:
                Intent intent = new Intent(MainActivity.this, FavoritesListActivity.class);
                startActivity(intent);
                return true;

            // The home button brings the user back to the full list of places.
            case R.id.action_home:
                Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(MainActivity.this).getNeighborhoodList();
                mCursorAdapter.swapCursor(cursor);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}