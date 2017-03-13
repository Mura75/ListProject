package example.com.weatherapp.network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Murager on 3/13/17.
 */

public interface ApiService {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<>
}
