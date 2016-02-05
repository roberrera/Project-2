package Classes;

import android.widget.ImageView;

/**
 * Created by Rob on 2/2/16.
 */
public class Neighborhood {

    public static Neighborhood instance;

    private String mDescription;
    private ImageView mPhoto;
    private String mAddress;
    private int id;
    private String mName;
    private int mFave;

    public Neighborhood(int id, String name, String description, ImageView photo, String address, int favorite) {
        this.id = id;
        this.mName = name;
        this.mDescription = description;
        this.mPhoto = photo;
        this.mAddress = address;
        this.mFave = favorite;
    }



}
