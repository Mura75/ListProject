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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
        Call<ResponseBody> call = RestClient.request().getPostList();

        Log.d("My_post_list_url", call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        Log.d("My_post_list", response.body().string());

                        JSONArray jsonArray = new JSONArray(response.body().string());

                        JSONObject jo = jsonArray.getJSONObject(1);

                        Log.d("My_post_array", jsonArray.toString());

                        Log.d("My_post_obj", jo.toString());

                        tvHello.setText(jo.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("My_post_obj", e.toString());

                    }


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("My_post_list_error", t.toString());
            }
        });
    }

}
