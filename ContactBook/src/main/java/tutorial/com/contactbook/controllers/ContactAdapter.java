package tutorial.com.contactbook.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import tutorial.com.contactbook.R;
import tutorial.com.contactbook.model.Contact;

/**
 * Created by Murager on 11.02.2017.
 */

public class ContactAdapter extends BaseAdapter {

    private Context context;

    private List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_item_contact, viewGroup, false);
            holder = new ViewHolder();
            holder.tvName = (TextView)view.findViewById(R.id.tvName);
            holder.tvPhone = (TextView)view.findViewById(R.id.tvPhone);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }

        holder.tvName.setText(contactList.get(i).getName());
        holder.tvPhone.setText(contactList.get(i).getPhoneNumber());

        return view;
    }

    private class ViewHolder {
        TextView tvName;
        TextView tvPhone;
    }
}
