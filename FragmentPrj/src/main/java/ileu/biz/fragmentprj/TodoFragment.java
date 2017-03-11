package ileu.biz.fragmentprj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
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
public class TodoFragment extends Fragment implements OnRecyclerViewItemClick, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;

    private SwipeRefreshLayout srlList;

    private PostAdapter2 adapter2;

    private List<Post> postList = new ArrayList<>();

    public TodoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        srlList = (SwipeRefreshLayout)view.findViewById(R.id.srtList);
        srlList.setOnRefreshListener(this);
        recyclerView = (RecyclerView)view.findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter2 = new PostAdapter2(postList, TodoFragment.this);
        recyclerView.setAdapter(adapter2);


        srlList.post(new Runnable() {
            @Override
            public void run() {
                getPostList();
            }
        });

        return view;
    }

    private void getPostList() {
        srlList.setRefreshing(true);
        Call<List<Post>> call = RestClient.request().getPostList();

        Log.d("My_post_list_url", call.request().url().toString());

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                srlList.setRefreshing(false);
                if (response.isSuccessful()) {
                    postList.addAll(response.body());
                    adapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("My_post_list_error", t.toString());
                srlList.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        postList.clear();
        adapter2.notifyDataSetChanged();
        getPostList();
    }
}
