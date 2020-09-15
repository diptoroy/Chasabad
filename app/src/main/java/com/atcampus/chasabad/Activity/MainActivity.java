package com.atcampus.chasabad.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import com.atcampus.chasabad.Adapter.MenuAdapter;
import com.atcampus.chasabad.Adapter.TipsAdapter;
import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.Model.TipsModel;
import com.atcampus.chasabad.Model.WeatherDataModel;
import com.atcampus.chasabad.R;
import com.atcampus.chasabad.Service.ApiClient;
import com.atcampus.chasabad.Service.WeatherService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private RecyclerView menuRecyclerView, tipsRecyclerView;
    private TextView locationTextView;
    private TextView tempText;

    //user request
    private int REQUEST_LOCATION = 99;
    //location
    private LocationManager locationManager;
    private String provider;
    private UserLocationListener mylistener;
    private Criteria criteria;

    //Weather
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "1af5922fab1bb3d30821d6bb74d6bf4e";
    public static String lat;
    public static String lon;
    private WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationTextView = findViewById(R.id.locationText);
        tempText = findViewById(R.id.tempText);

        // user defines the criteria
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);   //default
        criteria.setCostAllowed(false);
        // get the best provider depending on the criteria
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    {ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }

        final Location location = locationManager.getLastKnownLocation(provider);
        mylistener = new UserLocationListener();
        
        locationData(location);
        
        //App menu

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

        //Tips
        tipsRecyclerView = findViewById(R.id.tips_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        tipsRecyclerView.setLayoutManager(linearLayoutManager);

        //Todo
        List<TipsModel> tipsModelList = new ArrayList<>();
        tipsModelList.add(new TipsModel("Tips for red leafs!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        tipsModelList.add(new TipsModel("Tips for red leafs!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        tipsModelList.add(new TipsModel("Tips for red leafs!", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        TipsAdapter tipsAdapter = new TipsAdapter(tipsModelList);
        tipsRecyclerView.setAdapter(tipsAdapter);
        tipsAdapter.notifyDataSetChanged();


        weatherService = ApiClient.getRetrofit().create(WeatherService.class);
        Call<WeatherDataModel> call = weatherService.getCurrentWeatherData(lat,lon,AppId);
        call.enqueue(new Callback<WeatherDataModel>() {
            @Override
            public void onResponse(Call<WeatherDataModel> call, Response<WeatherDataModel> response) {
                WeatherDataModel weatherDataModel = response.body();
                
            }

            @Override
            public void onFailure(Call<WeatherDataModel> call, Throwable t) {

            }
        });
    }

    private void locationData(Location location) {
        if (location != null) {

            mylistener.onLocationChanged(location);
        } else {
            // leads to the settings because there is no last known location
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            //Using 12 seconds timer till it gets location
            final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Obtaining Location ...");
            alertDialog.setMessage("00:12");
            alertDialog.show();

            new CountDownTimer(12000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    alertDialog.setMessage("00:" + (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    alertDialog.dismiss();
                }
            }.start();
        }
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // this will beautomatically asked to be implemented
        }
        // location updates: at least 1 meter and 200millsecs change
        locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
        if(location!=null) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            lat = String.valueOf(latitude);
            lon = String.valueOf(longitude);

            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String cityName = addresses.get(0).getLocality();
            String stateName = addresses.get(0).getAdminArea();
            String countryName = addresses.get(0).getCountryName();
            String postalcode = addresses.get(0).getPostalCode();
            String area = addresses.get(0).getAdminArea();
            String latlon = lat +","+ lon;
            //set text of xml file
            locationTextView.setText(latlon);
        }
        else {
            Toast.makeText(MainActivity.this, "Please open your location", Toast.LENGTH_SHORT).show();
        }
    }

    private class UserLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(MainActivity.this, "" + location.getLatitude() + location.getLongitude(),
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(MainActivity.this, provider + "'s status changed to " + status + "!",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(MainActivity.this, "Provider " + provider + " enabled!",
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(MainActivity.this, "Provider " + provider + " disabled!",
                    Toast.LENGTH_SHORT).show();
        }
    }



//
//    @Override
//    public void onLocationChanged(Location location) {
//        try {
//            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//
//            locationTextView.setText(addresses.get(0).getLocality() + addresses.get(0).getLocality());
//            tvState.setText(addresses.get(0).getAdminArea());
//            tvCountry.setText(addresses.get(0).getCountryName());
//            tvPin.setText(addresses.get(0).getPostalCode());
//            locationTextView.setText(addresses.get(0).getAddressLine(0));
//
//        } catch (Exception e) {
//        }
//    }

}
