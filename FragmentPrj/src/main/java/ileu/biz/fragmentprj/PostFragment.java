package ileu.biz.fragmentprj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ileu.biz.fragmentprj.models.Post;
import ileu.biz.fragmentprj.network.RestClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    private Button button;

    private TextView tvHello;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        tvHello = (TextView) view.findViewById(R.id.tvHello);
        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Hello fragment", Toast.LENGTH_SHORT).show();
            }
        });

        getPostList();

        return view;
    }


    private void getPostList() {
        Call<List<Post>> call = RestClient.request().getPostList();

        Log.d("My_post_list_url", call.request().url().toString());

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {

                    List<Post> list = response.body();
                    tvHello.setText(list.toString());


                    Log.d("My_post_list", response.body().toString());

                    //OLD WAY Parsing of json
//                    JsonArray jsonArray = response.body();
//
//                    JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
//
//                    Post post = new Post();
//
//                    post.setUserId(jsonObject.get("userId").getAsInt());
//                    post.setId(jsonObject.get("id").getAsInt());
//                    post.setTitle(jsonObject.get("title").getAsString());
//                    post.setBody(jsonObject.get("body").getAsString());
//
//
//
//
//
//                    List<Post> postList = new ArrayList<>();
//
//                    for (int i = 0; i < jsonArray.size(); i++) {
//                        JsonObject jo = jsonArray.get(i).getAsJsonObject();
//                        Post p = new Post();
//
//                        p.setUserId(jo.get("userId").getAsInt());
//                        p.setId(jo.get("id").getAsInt());
//                        p.setTitle(jo.get("title").getAsString());
//                        p.setBody(jo.get("body").getAsString());
//
//                        postList.add(p);
//                    }
//
//                    tvHello.setText(postList.toString());

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("My_post_list_error", t.toString());
            }
        });
    }

}
