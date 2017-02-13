package tutorial.com.contactbook.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Murager on 11.02.2017.
 */

public class Contact implements Parcelable {

    private int id;

    private String name;

    private String phoneNumber;

    private String email;

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

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
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
    }

    public Contact () {

    }

    public Contact (Parcel in) {
        id = in.readInt();
        name = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
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
