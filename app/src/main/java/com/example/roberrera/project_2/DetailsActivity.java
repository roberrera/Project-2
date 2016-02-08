package com.example.roberrera.project_2;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import Classes.NeighborhoodSQLOpenHelper;

public class DetailsActivity extends AppCompatActivity {

    TextView mName, mAddress, mDesc;

    public static String mStarbucksDesc = "We’re not just passionate purveyors of coffee, but everything " +
            "else that goes with a full and rewarding coffeehouse experience. We also offer a " +
            "selection of premium teas, fine pastries and other delectable treats to please the " +
            "taste buds. And the music you hear in store is chosen for its artistry and appeal.\n" +
            "\nIt’s not unusual to see people coming to Starbucks to chat, meet up or even work. " +
            "We’re a neighborhood gathering place, a part of the daily routine – and we couldn’t " +
            "be happier about it. Get to know us and you’ll see: we are so much more than what we brew.\n" +
            "\nWe make sure everything we do is through the lens of humanity – from our commitment " +
            "to the highest quality coffee in the world, to the way we engage with our customers " +
            "and communities to do business responsibly.";

    public static String mEatalyDesc = "We cook what we sell, and we sell what we cook.\n" +
            "\nEataly is a bustling Italian marketplace with 50,000 square feet overflowing with " +
            "restaurants, take-away counters, and a cooking school that serve the same " +
            "high-quality food and drink offered just steps away in the full market. Eat, shop, and learn!\n" +
            "\nTo navigate the market, we recommend that first-time visitors stop by our Guest " +
            "Relations desk by the 23rd Street entrance. Our team of Eataly ambassadors will be " +
            "happy to guide you on your journey, whether you are here to shop, taste, or enjoy " +
            "a full meal.";

    public static String mGADesc = "At General Assembly, we are creating a global community of " +
            "individuals empowered to pursue work they love, by offering full-time immersive " +
            "programs, long-form courses, and classes and workshops on the most relevant skills " +
            "of the 21st century – from web development and user experience design, to business " +
            "fundamentals, to data science, to product management and digital marketing.\n" +
            "Established in early 2011 as an innovative community in New York City for " +
            "entrepreneurs and startup companies, General Assembly is an educational institution " +
            "that transforms thinkers into creators through education in technology, business " +
            "and design at fifteen campuses across four continents.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int id = getIntent().getIntExtra("id", -1);

        NeighborhoodSQLOpenHelper helper = new NeighborhoodSQLOpenHelper(DetailsActivity.this);
        helper.getReadableDatabase();

//        mName = (TextView)findViewById(R.id.name_detailsActivity);
//        mAddress = (TextView)findViewById(R.id.address_detailsActivity);
        mDesc = (TextView)findViewById(R.id.description_textView);



        final Cursor cursor = NeighborhoodSQLOpenHelper.getInstance(DetailsActivity.this).getNeighborhoodList();
        CursorAdapter cursorAdapter = new CursorAdapter(DetailsActivity.this, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.activity_details,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
//                String placeName = NeighborhoodSQLOpenHelper.getInstance(DetailsActivity.this).getLocationNameByID(id);
//                mName.setText(placeName);

                ImageView image = (ImageView)view.findViewById(R.id.place_image);
                if (cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_DESC)).equals("Starbucks")) {
                    image.setImageResource(R.drawable.starbucks);
                } if (cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_DESC)).equals("General Assembly")) {
                    image.setImageResource(R.drawable.generalassembly);
                }

                String placeDesc = NeighborhoodSQLOpenHelper.getInstance(DetailsActivity.this).getLocationDescByID(id);
                mDesc.setText(placeDesc);

                ImageView fab = (FloatingActionButton) findViewById(R.id.fab);
                if (cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_FAVE) == 1){
                    fab.setImageResource(R.drawable.favorite_full);
                } else {
                    fab.setImageResource(R.drawable.favorite_empty);
                }

            }
        };
        if (cursor != null) {
            setTitle(cursor.getString(cursor.getColumnIndex(NeighborhoodSQLOpenHelper.COL_PLACE_NAME)));
        } else {
            setTitle("Details");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set up a flag so we can tell whether the item has been added to favorites.
//                boolean flag = false;
//                ImageView faveButton = (ImageView) findViewById(R.id.favorites_icon);
//
//                if (flag == false){
//                    // Change heart icon to be a filled heart and add item to the favorites list.
//                    faveButton.setImageResource(R.drawable.favorite_full);
//                    // TODO: Set COL_FAVE to 1 to add to favorites list.
//                } else {
//                    // Change heart icon to be empty and remove from the favorites list.
//                    faveButton.setImageResource(R.drawable.favorite_empty);
//                    // TODO: Set COL_FAVE to 0 to take off of favorites list.
//                }
//                Snackbar.make(view, "Favorites updated", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


}