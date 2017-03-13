package example.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Murager on 3/13/17.
 */

public class MainData {

    @SerializedName("temp")
    private String temperature;

    @SerializedName("pressure")
    private String pressure;

    @SerializedName("humidity")
    private String humidity;

    @SerializedName("temp_max")
    private String tempMax;

    @SerializedName("temp_min")
    private String tempMin;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MainData{");
        sb.append("temperature='").append(temperature).append('\'');
        sb.append(", pressure='").append(pressure).append('\'');
        sb.append(", humidity='").append(humidity).append('\'');
        sb.append(", tempMax='").append(tempMax).append('\'');
        sb.append(", tempMin='").append(tempMin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
