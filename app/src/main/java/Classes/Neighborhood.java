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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> branch3
    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public ImageView getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(ImageView mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }
<<<<<<< HEAD
=======
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things


<<<<<<< HEAD
    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmFave() {
        return mFave;
    }

    public void setmFave(int mFave) {
        this.mFave = mFave;
    }
=======
>>>>>>> parent of 8aa7103... Did more setup of layouts and database
=======
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
=======
=======
>>>>>>> branch3

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

<<<<<<< HEAD
>>>>>>> parent of 15ddd7d... Removed navigation drawer for now--it was overcomplicating things
}
=======
    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmFave() {
        return mFave;
    }

    public void setmFave(int mFave) {
        this.mFave = mFave;
    }
}
>>>>>>> branch3
