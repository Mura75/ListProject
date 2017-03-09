package ileu.biz.fragmentprj.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ileu.biz.fragmentprj.R;
import ileu.biz.fragmentprj.models.Post;

/**
 * Created by Murager on 3/9/17.
 */

public class PostAdapter2 extends RecyclerView.Adapter<PostAdapter2.TodoViewHolder> {

    private List<Post> postList;

    public PostAdapter2(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new TodoViewHolder(row);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.tvPostName.setText(post.getTitle());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView tvPostName;

        public TodoViewHolder(View itemView) {
            super(itemView);
            tvPostName = (TextView)itemView.findViewById(android.R.id.text1);
        }
    }
}
