package com.atcampus.chasabad.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.R;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    List<MenuModel> menuModels;

    public MenuAdapter(List<MenuModel> menuModels) {
        this.menuModels = menuModels;
    }

    @Override
    public int getCount() {
        return menuModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_gridview_row, null);

            ImageView img = view.findViewById(R.id.menu_image);
            TextView title = view.findViewById(R.id.menu_name);

            img.setImageResource(menuModels.get(position).getMenuImage());
            title.setText(menuModels.get(position).getMenuTitle());

        }else {
            view = convertView;
        }
        return view;
    }
}
