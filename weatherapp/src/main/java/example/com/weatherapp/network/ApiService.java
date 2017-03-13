package example.com.weatherapp.network;

import example.com.weatherapp.models.CityWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Murager on 3/13/17.
 */

public interface ApiService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    String ID = "id";

    String APP_ID = "appid";

    @GET("weather")
    Call<CityWeather> getCityWeatherById(@Query(ID) String id,
                                         @Query(APP_ID) String appId);
}
