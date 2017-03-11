package ileu.biz.fragmentprj.network;


import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

import ileu.biz.fragmentprj.models.Photo;
import ileu.biz.fragmentprj.models.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Murager on 3/2/17.
 */

public interface ApiService {

    @GET("posts")
    Call<List<Post>> getPostList();

    @GET("photos")
    Call<List<Photo>> getPhotosList();

}

