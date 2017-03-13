package example.com.weatherapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Murager on 3/13/17.
 */

public class RestClient {

    private static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public static ApiService request() {
        return retrofit.create(ApiService.class);
    }

}
