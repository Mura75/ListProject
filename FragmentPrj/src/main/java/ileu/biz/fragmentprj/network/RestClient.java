package ileu.biz.fragmentprj.network;

import retrofit2.Retrofit;

/**
 * Created by Murager on 3/2/17.
 */

public class RestClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();



    public static ApiService request() {
        return retrofit.create(ApiService.class);
    }
}
