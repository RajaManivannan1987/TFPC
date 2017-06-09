package com.example.tfpc.tfpc.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tfpc.tfpc.R;


/**
 * Created by IM028 on 8/16/16.
 */
public class CommonActionBarListAdapter extends BaseAdapter {
    private Context context;
    private String[] listName = {
            "Home",
            "Member Dashboard",
            "About TFPC",
            "Events",
            "Gallery",
            "Member Search",
            "Useful Documents",
            "Contact Us"};
    /*private int[] listImage = {
            R.drawable.menu_icon,
            R.drawable.menu_icon,
            R.drawable.menu_icon,
            R.drawable.menu_icon,
            R.drawable.menu_icon,
            R.drawable.menu_icon,
            R.drawable.menu_icon,
    };*/
    private LayoutInflater inflater;

    public CommonActionBarListAdapter(Activity context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listName.length;
    }

    @Override
    public Object getItem(int position) {
        return listName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.commom_navigation_item, parent, false);
        }
        TextView nameTextView = (TextView) convertView.findViewById(R.id.commonNavigationItemTextView);

//        ImageView imageView = (ImageView) convertView.findViewById(R.id.commonNavigationItemImageView);
        nameTextView.setText(listName[position]);
//        imageView.setImageResource(listImage[position]);
        return convertView;
    }
}
