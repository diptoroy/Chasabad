package com.atcampus.chasabad.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atcampus.chasabad.Model.TipsModel;
import com.atcampus.chasabad.R;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.ViewHolder> {

    private List<TipsModel> tipsModels;

    public TipsAdapter(List<TipsModel> tipsModels) {
        this.tipsModels = tipsModels;
    }

    @NonNull
    @Override
    public TipsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsAdapter.ViewHolder holder, int position) {

        String title = tipsModels.get(position).getTitle();
        String desc = tipsModels.get(position).getDesc();

        holder.setData(title,desc);

    }

    @Override
    public int getItemCount() {
        return tipsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tips_title);
            desc = itemView.findViewById(R.id.tips_details);

        }
        public void setData(String tipsTitle,String tipsDesc){
            title.setText(tipsTitle);
            desc.setText(tipsDesc);
        }
    }
}
