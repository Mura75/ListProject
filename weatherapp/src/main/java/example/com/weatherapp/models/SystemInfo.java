package example.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Murager on 3/13/17.
 */

public class SystemInfo {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    @SerializedName("KZ")
    private String country;

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("sunset")
    private String sunset;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SystemInfo{");
        sb.append("type='").append(type).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", sunrise='").append(sunrise).append('\'');
        sb.append(", sunset='").append(sunset).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
