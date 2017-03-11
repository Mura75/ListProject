package ileu.biz.fragmentprj.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import ileu.biz.fragmentprj.OnRecyclerViewItemClick;
import ileu.biz.fragmentprj.R;
import ileu.biz.fragmentprj.models.Photo;

/**
 * Created by Murager on 3/11/17.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Photo> photoList;

    private OnRecyclerViewItemClick listener;

    private Random random = new Random();

    public PhotoAdapter(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public PhotoAdapter(List<Photo> photoList, OnRecyclerViewItemClick listener) {
        this.photoList = photoList;
        this.listener = listener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_photo, parent, false);
        return new PhotoViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.tvPhotoName.setText(photo.getTitle());

        Log.d("Photo_url", photo.toString());

        String imageUrl = "https://placeholdit.imgix.net/~text?txtsize=56&bg="+
                photo.imageUrlLastString() +
                "&txt=600%C3%97600&w=600&h=600";

        Picasso.with(holder.context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivPhoto);

        holder.buttonChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newImage = "https://placeholdit.imgix.net/~text?txtsize=56&bg="+
                        generateColor() +
                        "&txt=600%C3%97600&w=600&h=600";

                Picasso.with(holder.context)
                        .load(newImage)
                        .placeholder(R.mipmap.ic_launcher)
                        .error(android.R.drawable.stat_notify_error)
                        .into(holder.ivPhoto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }


    private String generateColor() {
        int newColor = 0x1000000 + random.nextInt(0x1000000);
        return Integer.toHexString(newColor).substring(1, 7);
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        Context context;

        ImageView ivPhoto;

        TextView tvPhotoName;

        Button buttonChangeColor;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ivPhoto = (ImageView)itemView.findViewById(R.id.ivPhoto);
            tvPhotoName = (TextView)itemView.findViewById(R.id.tvPhotoTitle);
            buttonChangeColor = (Button)itemView.findViewById(R.id.buttonChangeColor);
        }
    }
}
