package example.com.weatherapp.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import example.com.weatherapp.R;
import example.com.weatherapp.models.CityWeather;
import example.com.weatherapp.network.RestClient;
import example.com.weatherapp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCityWeather();
    }

    private void getCityWeather() {

        Call<CityWeather> call = RestClient
                .request()
                .getCityWeatherById(Constants.ALMATY_ID,
                        Constants.WEATHER_API_KEY);

        call.enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if (response.isSuccessful()) {
                    Log.d("Aplmaty_weather", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {

            }
        });
    }
}
