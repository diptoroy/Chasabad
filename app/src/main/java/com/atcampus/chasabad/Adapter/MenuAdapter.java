package com.atcampus.chasabad.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.R;

import java.util.List;

import javax.xml.namespace.QName;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<MenuModel> menuModels;

    public MenuAdapter(List<MenuModel> menuModels) {
        this.menuModels = menuModels;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_gridview_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, int position) {

        int img = menuModels.get(position).getMenuImage();
        String title = menuModels.get(position).getMenuTitle();

        holder.setData(img,title);
    }

    @Override
    public int getItemCount() {
        return menuModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.menu_image);
            title = itemView.findViewById(R.id.menu_name);
        }

        public void setData(int mImage,String mName){
            img.setImageResource(mImage);
            title.setText(mName);
        }
    }
}
