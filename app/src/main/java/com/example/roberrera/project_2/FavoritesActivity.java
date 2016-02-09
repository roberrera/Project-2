//package com.example.roberrera.project_2;
//
//import android.app.Activity;
//import android.content.Context;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.DefaultItemAnimator;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CursorAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import Classes.FavoritesAdapter;
//import Classes.NeighborhoodSQLOpenHelper;
//
//public class FavoritesActivity extends AppCompatActivity {
//
////public class FavoritesActivity extends Activity {
//
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_favorites);
//
//        mRecyclerView = (RecyclerView)findViewById(R.id.list);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        mAdapter = new FavoritesAdapter(mLayoutManager.getInstance().getCountries(), R.layout.row_country, this);
//        mRecyclerView.setAdapter(mAdapter);
//
//
///*            setContentView(R.layout.list_favorites_layout);
//            mRecyclerView = (RecyclerView) findViewById(R.id.favorites_list);
//
//            // use this setting to improve performance if you know that changes
//            // in content do not change the layout size of the RecyclerView
//            mRecyclerView.setHasFixedSize(true);
//
//            // use a linear layout manager
//            mLayoutManager = new LinearLayoutManager(this);
//            mRecyclerView.setLayoutManager(mLayoutManager);
//
////            // specify an adapter (see also next example)
////            mAdapter = new FavoritesAdapter();
////            mRecyclerView.setAdapter(mAdapter);
//*/
//    }
//}
