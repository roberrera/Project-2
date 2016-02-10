package com.example.roberrera.project_2;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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

import Classes.NeighborhoodSQLOpenHelper;

public class MainActivity extends AppCompatActivity {
//        implements NavigationView.OnNavigationItemSelectedListener {

    ListView mListView;
    CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Places!");

        // Setup for navigation drawer
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        // TODO: Change favorites list button (currently a toolbar heart icon) to be tappable navigation drawer item?
/*
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
*/

/*
        // Commented out so data is not added more than once.
        // Adding entries to the database
        helper.addPlace("Eataly", DetailsActivity.mEatalyDesc, "200 Fifth Avenue\nNew York, NY", 0);
        helper.addPlace("General Assembly", DetailsActivity.mGADesc, "10 E. 21st Street\nNew York, NY", 1);
        helper.addPlace("Starbucks", DetailsActivity.mStarbucksDesc, "14 W. 24th Street\nNew York, NY", 0);
*/
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

                TextView placeName = (TextView)view.findViewById(R.id.name_textView);
                placeName.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)));

                TextView address = (TextView)view.findViewById(R.id.address_textView);
                address.setText(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_ADDRESS)));

                ImageView image = (ImageView)view.findViewById(R.id.imageView_mainActivity);
                image.setImageResource(helper.getDrawableValue( cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)) ));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

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
//        Toast.makeText(MainActivity.this, "Toast from outside if statement", Toast.LENGTH_SHORT).show();

        if (Intent.ACTION_SEARCH.equals( intent.getAction() )){
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(this).searchPlaces(query);
            mCursorAdapter.swapCursor(cursor);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;

            case R.id.action_faves:
                Intent intent = new Intent(MainActivity.this, FavoritesListActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_home:
                Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(MainActivity.this).getNeighborhoodList();
                mCursorAdapter.swapCursor(cursor);
//                cursor.close();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    // TODO: Decide whether to keep the navigation drawer.
    // Actions that will be taken when an item in the navigation drawer is tapped.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    */
}