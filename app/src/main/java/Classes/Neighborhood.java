package Classes;

import android.widget.ImageView;

import com.example.roberrera.project_2.R;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmFave() {
        return mFave;
    }

    public void setmFave(int mFave) {
        this.mFave = mFave;
    }

    // Cases for which photo to use based on which column name is being pulled by the details activity.
    public static int getDrawableValue(String image){
        switch(image){
            case "Starbucks":
                return R.drawable.starbucks;
            case "Eataly":
                return R.drawable.eataly;
            case "General Assembly":
                return R.drawable.generalassembly;
            case "Maison-Kayser Flatiron":
                return R.drawable.maison;
            case "Mozzarellis":
                return R.drawable.mozzarellis;
            default:
                return 0;
        }
    }
}