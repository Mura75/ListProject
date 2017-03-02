package ileu.biz.fragmentprj.network;


import org.json.JSONArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Murager on 3/2/17.
 */

public interface ApiService {

    @GET("posts")
    Call<ResponseBody> getPostList();

}
