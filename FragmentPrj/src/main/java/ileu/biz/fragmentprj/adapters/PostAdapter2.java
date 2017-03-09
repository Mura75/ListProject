package ileu.biz.fragmentprj.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ileu.biz.fragmentprj.OnRecyclerViewItemClick;
import ileu.biz.fragmentprj.R;
import ileu.biz.fragmentprj.models.Post;

/**
 * Created by Murager on 3/9/17.
 */

public class PostAdapter2 extends RecyclerView.Adapter<PostAdapter2.TodoViewHolder> {

    private List<Post> postList;

    private OnRecyclerViewItemClick listener;

    public PostAdapter2(List<Post> postList) {
        this.postList = postList;
    }

    public PostAdapter2(List<Post> postList, OnRecyclerViewItemClick listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_post, parent, false);
        return new TodoViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final TodoViewHolder holder, final int position) {
        Post post = postList.get(position);
        holder.tvPostName.setText(post.getTitle());

        holder.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onShareClick(position + " shared");
                }
                //Toast.makeText(holder.context, position + " shared", Toast.LENGTH_SHORT).show();
            }
        });

        holder.buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLikeClick(position + " liked");
                }
                //Toast.makeText(holder.context, position + " liked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        Context context;

        TextView tvPostName;

        Button buttonLike, buttonShare;

        public TodoViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvPostName = (TextView)itemView.findViewById(R.id.textView);
            buttonLike = (Button)itemView.findViewById(R.id.buttonLike);
            buttonShare = (Button)itemView.findViewById(R.id.buttonShare);
        }
    }
}
