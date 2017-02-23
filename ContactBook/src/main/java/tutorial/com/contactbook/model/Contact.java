package tutorial.com.contactbook.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Murager on 11.02.2017.
 */

public class Contact implements Parcelable {

    private int id;

    private String name;

    private String phoneNumber;

    private String email;

    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    //Dekodiruem stroku to baiti potom v bitmap
    //String -> byte[] -> bitmap
    public Bitmap getBitmapImage() {
        if (photo != null) {
            byte[] decodedBytes = Base64.decode(photo, 0);
            Bitmap resultBitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            return resultBitmap;
        }
        else {
            return null;
        }
    }

    //NE ISPOLZOVIAT
    //Kodiruem foto v formate JPEG v String
    //bitmap -> byte[] -> String
    public void setPhoto(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, out);
        this.photo = Base64.encodeToString(out.toByteArray(), Base64.DEFAULT);
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(email);
        dest.writeString(photo);
    }

    public Contact () {

    }

    public Contact (Parcel in) {
        id = in.readInt();
        name = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        photo = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Contact[size];
        }
    };
}
