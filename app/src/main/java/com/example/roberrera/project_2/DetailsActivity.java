package com.example.roberrera.project_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import Classes.NeighborhoodSQLOpenHelper;

public class DetailsActivity extends AppCompatActivity {

    TextView mAddress, mDesc;
    ImageView mImage;

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

        // Get the intent from MainActivity.
        final int id = getIntent().getIntExtra("id", -1);

        final NeighborhoodSQLOpenHelper helper = NeighborhoodSQLOpenHelper.getInstance(DetailsActivity.this);

        // Set the activity title based on which item we're viewing.
        if (helper.getLocationNameByID(id) != null) {
            setTitle(helper.getLocationNameByID(id));
        } else {
            setTitle("Details");
        }


        mImage = (ImageView)findViewById(R.id.place_image);
        mAddress = (TextView)findViewById(R.id.address_detailsActivity);
        mDesc = (TextView) findViewById(R.id.description_textView);

        String placeAddress = helper.getLocationAddressByID(id);
        String placeDesc = helper.getLocationDescByID(id);

        mImage.setImageResource(NeighborhoodSQLOpenHelper.getDrawableValue(helper.getLocationNameByID(id)));
        mAddress.setText(placeAddress);
        mDesc.setText(placeDesc);


        // Check if the place is also in the favorites list, and show the proper heart icon in response.
        ImageView fab = (FloatingActionButton) findViewById(R.id.fab);
        if (helper.getFavoritesByID(id) == 1){
            fab.setImageResource(R.drawable.favorite_full);
        } else {
            fab.setImageResource(R.drawable.favorite_empty);
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView faveButton = (ImageView) findViewById(R.id.fab);
                SQLiteDatabase db = helper.getReadableDatabase();

                if (helper.getFavoritesByID(id) == 0){
                    // Change heart icon to be a filled heart and add item to the favorites list.
                    faveButton.setImageResource(R.drawable.favorite_full);
                    helper.updateFavoriteByID(id, 1);

                    Toast.makeText(DetailsActivity.this, helper.getLocationNameByID(id)
                            + " added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    // Change heart icon to be empty and remove from the favorites list.
                    faveButton.setImageResource(R.drawable.favorite_empty);
                    helper.updateFavoriteByID(id, 0);

                    Toast.makeText(DetailsActivity.this, helper.getLocationNameByID(id)
                            + " removed from favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO: Show updated favorites list when back is pressed.

    }
}