package com.atcampus.chasabad.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.atcampus.chasabad.Adapter.MenuAdapter;
import com.atcampus.chasabad.Adapter.TipsAdapter;
import com.atcampus.chasabad.AppService.FetchIntentAddressService;
import com.atcampus.chasabad.AppService.LocationConstants;
import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.Model.TipsModel;
import com.atcampus.chasabad.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView menuRecyclerView, tipsRecyclerView;
    private TextView locationTextView;

    private final static int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private ResultReceiver resultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTextView = findViewById(R.id.locationText);
        LocationData();
        resultReceiver = new addressResultReceiver(new Handler());

        menuRecyclerView = findViewById(R.id.menu_recyclerView);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<MenuModel> menuModelList = new ArrayList<>();
        menuModelList.add(new MenuModel(R.drawable.leaf, "Leaf"));
        menuModelList.add(new MenuModel(R.drawable.agriculture, "Agriculture"));
        menuModelList.add(new MenuModel(R.drawable.medicine, "Medicine"));
        menuModelList.add(new MenuModel(R.drawable.price, "Price"));
        menuModelList.add(new MenuModel(R.drawable.news, "News"));
        menuModelList.add(new MenuModel(R.drawable.shop, "Shop"));
        MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
        menuRecyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

        tipsRecyclerView = findViewById(R.id.tips_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        tipsRecyclerView.setLayoutManager(linearLayoutManager);

        List<TipsModel> tipsModelList = new ArrayList<>();
        tipsModelList.add(new TipsModel("Tips for red leafs!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        tipsModelList.add(new TipsModel("Tips for red leafs!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        tipsModelList.add(new TipsModel("Tips for red leafs!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        TipsAdapter tipsAdapter = new TipsAdapter(tipsModelList);
        tipsRecyclerView.setAdapter(tipsAdapter);
        tipsAdapter.notifyDataSetChanged();
    }

    private void LocationData() {
        if (ContextCompat.checkSelfPermission(
                getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION
            );
        } else {
            getCurrentLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            getCurrentLocation();
        } else {
            Toast.makeText(this, "permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentLocation() {

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocation = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(latestLocation).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocation).getLatitude();
                            locationTextView.setText(String.format("lat %s\nlong %s",latitude,longitude));
                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            fetchAddressFromLocation(location);
                        }

                    }
                }, Looper.getMainLooper());
    }

    private void fetchAddressFromLocation(Location location){
        Intent intent = new Intent(this, FetchIntentAddressService.class);
        intent.putExtra(LocationConstants.RECEIVER,resultReceiver);
        intent.putExtra(LocationConstants.LOCATION_DATA_EXTRA,location);
        startService(intent);
    }

    private class addressResultReceiver extends ResultReceiver {

        addressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == LocationConstants.SUCCESS_RESULT){
                //locationTextView.setText(resultData.getString(LocationConstants.RESULT_DATA_KEY));
            }else{
                Toast.makeText(MainActivity.this,resultData.getString(LocationConstants.RESULT_DATA_KEY),Toast.LENGTH_LONG).show();
            }
        }
    }
}
