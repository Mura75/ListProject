package ileu.biz.fragmentprj;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ileu.biz.fragmentprj.adapters.PhotoAdapter;
import ileu.biz.fragmentprj.models.Photo;
import ileu.biz.fragmentprj.network.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private PhotoAdapter photoAdapter;

    private List<Photo> photoList = new ArrayList<>();

    public PhotosFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.mainRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);
        photoAdapter = new PhotoAdapter(photoList);
        recyclerView.setAdapter(photoAdapter);

        getPhotos();

        return view;
    }


    private void getPhotos() {
        Call<List<Photo>> call = RestClient.request().getPhotosList();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    photoList.addAll(response.body());
                    photoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
