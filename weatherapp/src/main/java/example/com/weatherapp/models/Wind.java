package example.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Murager on 3/13/17.
 */

public class Wind {

    @SerializedName("speed")
    private String speed;

    @SerializedName("deg")
    private String deg;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wind{");
        sb.append("speed='").append(speed).append('\'');
        sb.append(", deg='").append(deg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
