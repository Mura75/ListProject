package example.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Murager on 3/13/17.
 */

public class Clouds {

    @SerializedName("all")
    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Clouds{");
        sb.append("all='").append(all).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
