package ileu.biz.fragmentprj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ileu.biz.fragmentprj.adapters.PostAdapter2;
import ileu.biz.fragmentprj.models.Post;
import ileu.biz.fragmentprj.network.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoFragment extends Fragment implements OnRecyclerViewItemClick {

    private RecyclerView recyclerView;

    private PostAdapter2 adapter2;

    public TodoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

                    adapter2 = new PostAdapter2(list, TodoFragment.this);
                    recyclerView.setAdapter(adapter2);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("My_post_list_error", t.toString());
            }
        });
    }

    @Override
    public void onShareClick(String data) {
        Toast.makeText(getActivity(), "From todo fragment " + data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLikeClick(String data) {
        Toast.makeText(getActivity(), "From todo fragment " + data, Toast.LENGTH_SHORT).show();
    }
}
