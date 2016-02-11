package com.example.roberrera.project_2;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import Classes.Neighborhood;
import Classes.NeighborhoodSQLOpenHelper;

public class DetailsActivity extends AppCompatActivity {

    TextView mAddress, mDesc, mType;
    ImageView mImage;
    RatingBar mRating;

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
        mType = (TextView)findViewById(R.id.type_detailsActivity);
        mDesc = (TextView) findViewById(R.id.description_textView);
        mRating = (RatingBar)findViewById(R.id.ratingBar);

        String placeAddress = helper.getLocationAddressByID(id);
        String placeDesc = helper.getLocationDescByID(id);
        String placeType = helper.getTypeByID(id);

        mImage.setImageResource(Neighborhood.getDrawableValue(helper.getLocationNameByID(id)));
//        mImage.setImageResource(helper.getImageByID(id));
        mAddress.setText(placeAddress);
        mDesc.setText(placeDesc);
        mType.setText(placeType);
        // Gets the stored rating for the current place.
        mRating.setRating(helper.getRatingByID(id));


        // Check if the place is also in the favorites list, and show the proper heart icon in response.
        ImageView fab = (FloatingActionButton) findViewById(R.id.fab);
        if (helper.getFavoritesByID(id) == 1){
            fab.setImageResource(R.drawable.favorite_full);
        } else {
            fab.setImageResource(R.drawable.favorite_empty);
        }

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

        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                helper.updateRatingByID(id, rating);
                Toast.makeText(DetailsActivity.this, "Rated " + rating + " stars", Toast.LENGTH_SHORT).show();
            }
        });

    }

}