package com.atcampus.chasabad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.atcampus.chasabad.Adapter.MenuAdapter;
import com.atcampus.chasabad.Adapter.TipsAdapter;
import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.Model.TipsModel;
import com.atcampus.chasabad.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView menuRecyclerView,tipsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuRecyclerView = findViewById(R.id.menu_recyclerView);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        List<MenuModel> menuModelList = new ArrayList<>();
        menuModelList.add(new MenuModel(R.drawable.leaf,"Leaf"));
        menuModelList.add(new MenuModel(R.drawable.agriculture,"Agriculture"));
        menuModelList.add(new MenuModel(R.drawable.medicine,"Medicine"));
        menuModelList.add(new MenuModel(R.drawable.price,"Price"));
        menuModelList.add(new MenuModel(R.drawable.news,"News"));
        menuModelList.add(new MenuModel(R.drawable.shop,"Shop"));
        MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
        menuRecyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

        tipsRecyclerView = findViewById(R.id.tips_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        tipsRecyclerView.setLayoutManager(linearLayoutManager);

        List<TipsModel> tipsModelList = new ArrayList<>();
        tipsModelList.add(new TipsModel("Tips for red leafs!","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        tipsModelList.add(new TipsModel("Tips for red leafs!","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        tipsModelList.add(new TipsModel("Tips for red leafs!","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        TipsAdapter tipsAdapter = new TipsAdapter(tipsModelList);
        tipsRecyclerView.setAdapter(tipsAdapter);
        tipsAdapter.notifyDataSetChanged();
    }
}