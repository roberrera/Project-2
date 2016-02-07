package com.example.roberrera.project_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
import android.widget.ImageView;
>>>>>>> branch3

import Classes.NeighborhoodSQLOpenHelper;
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        int id = getIntent().getIntExtra("id", -1);


        setTitle(NeighborhoodSQLOpenHelper.getInstance(DetailsActivity.this).getLocationNameByID(
                Integer.parseInt(NeighborhoodSQLOpenHelper.COL_ID)));

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 8aa7103... Did more setup of layouts and database
=======
>>>>>>> branch3
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> branch3
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
<<<<<<< HEAD
=======
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
>>>>>>> parent of 8aa7103... Did more setup of layouts and database
            }
        });
=======
// TODO Add back FAB onClickListener.
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
=======
// TODO Add back FAB onClickListener.
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
=======
            }
        });
>>>>>>> branch3
    }


}