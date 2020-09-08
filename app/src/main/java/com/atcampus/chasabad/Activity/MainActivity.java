package com.atcampus.chasabad.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.atcampus.chasabad.Adapter.MenuAdapter;
import com.atcampus.chasabad.Adapter.TipsAdapter;
import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.Model.TipsModel;
import com.atcampus.chasabad.R;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class MainActivity extends AppCompatActivity implements LocationListener {


    private RecyclerView menuRecyclerView, tipsRecyclerView;
    private TextView locationTextView;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTextView = findViewById(R.id.locationText);
        LocationData();

        menuRecyclerView = findViewById(R.id.menu_recyclerView);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<MenuModel> menuModelList = new ArrayList<>();
        menuModelList.add(new MenuModel(R.drawable.leaf, "ফসল পরিচিতি"));
        menuModelList.add(new MenuModel(R.drawable.agriculture, "চাষাবাদ পদ্ধতি ও সময়"));
        menuModelList.add(new MenuModel(R.drawable.medicine, "সার ও কীটনাশক"));
        menuModelList.add(new MenuModel(R.drawable.price, "আজকের বাজার"));
        menuModelList.add(new MenuModel(R.drawable.news, "কৃষিতথ্য"));
        menuModelList.add(new MenuModel(R.drawable.shop, "শপ"));
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
        
        //Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationEnabled();
        getLocation();
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void locationEnabled() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            locationTextView.setText(addresses.get(0).getLocality() + addresses.get(0).getLocality());
//            tvState.setText(addresses.get(0).getAdminArea());
//            tvCountry.setText(addresses.get(0).getCountryName());
//            tvPin.setText(addresses.get(0).getPostalCode());
//            locationTextView.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void LocationData() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

    }



}
