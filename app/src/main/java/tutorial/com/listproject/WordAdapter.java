package tutorial.com.listproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Murager on 06.02.2017.
 */

public class WordAdapter extends BaseAdapter {

    Context context;

    List<String> list;

    public WordAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view == null) {
//            view = LayoutInflater
//                    .from(context)
//                    .inflate(R.layout.row_word, viewGroup, false);
//        }
//
//        TextView tvWord = (TextView) view.findViewById(R.id.tvWord);
//
//        String word = list.get(i);
//
//        tvWord.setText(word);

        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_word, viewGroup, false);
            holder = new ViewHolder();
            holder.tvWord = (TextView)view.findViewById(R.id.tvWord);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        holder.tvWord.setText(list.get(i));

        return view;
    }

    private class ViewHolder {
        TextView tvWord;
    }
}
