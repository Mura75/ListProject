package example.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Murager on 3/13/17.
 */

public class Coordinates {

    @SerializedName("lon")
    private String longitude;

    @SerializedName("lat")
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Coordinates{");
        sb.append("longitude='").append(longitude).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
