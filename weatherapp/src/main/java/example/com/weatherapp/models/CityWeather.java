package example.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Murager on 3/13/17.
 */

public class CityWeather {

    @SerializedName("coord")
    private Coordinates coordinates;

    @SerializedName("weather")
    private List<Weather> weatherList;

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    private MainData main;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("dt")
    private String dt;

    @SerializedName("sys")
    private SystemInfo sys;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private int cod;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainData getMain() {
        return main;
    }

    public void setMain(MainData main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public SystemInfo getSys() {
        return sys;
    }

    public void setSys(SystemInfo sys) {
        this.sys = sys;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CityWeather{");
        sb.append("coordinates=").append(coordinates);
        sb.append(", weatherList=").append(weatherList);
        sb.append(", base='").append(base).append('\'');
        sb.append(", main=").append(main);
        sb.append(", visibility='").append(visibility).append('\'');
        sb.append(", wind=").append(wind);
        sb.append(", clouds=").append(clouds);
        sb.append(", dt='").append(dt).append('\'');
        sb.append(", sys=").append(sys);
        sb.append(", id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", cod=").append(cod);
        sb.append('}');
        return sb.toString();
    }
}
