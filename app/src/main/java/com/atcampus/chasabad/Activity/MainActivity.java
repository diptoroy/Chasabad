package com.atcampus.chasabad.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atcampus.chasabad.Adapter.MenuAdapter;
import com.atcampus.chasabad.Adapter.TipsAdapter;
import com.atcampus.chasabad.Model.MenuModel;
import com.atcampus.chasabad.Model.TipsModel;
import com.atcampus.chasabad.Model.WeatherData.WeatherDataModel;
import com.atcampus.chasabad.R;
import com.atcampus.chasabad.Service.ApiClient;
import com.atcampus.chasabad.Service.WeatherService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private RecyclerView menuRecyclerView, tipsRecyclerView;
    private TextView locationTextView;
    TextView tempText, humText, windText, rainText;
    private ImageView wIcon;

    //Weather
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "3e27923a09225792589eb4781bda21eb";
    public String lat;
    public String lon;
    private WeatherService weatherService;

    //location
    FusedLocationProviderClient fusedLocationProviderClient;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTextView = findViewById(R.id.locationText);
        tempText = findViewById(R.id.tempText);
        wIcon = findViewById(R.id.weatherIcon);
        humText = findViewById(R.id.humidity_yext);
        windText = findViewById(R.id.wind_text);
        rainText = findViewById(R.id.rain_text);

        //App menu
        menuRecyclerView = findViewById(R.id.menu_recyclerView);
        menuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<MenuModel> menuModelList = new ArrayList<>();
//        menuModelList.add(new MenuModel(R.drawable.leaf, "ফসল পরিচিতি"));
//        menuModelList.add(new MenuModel(R.drawable.agriculture, "চাষাবাদ পদ্ধতি ও সময়"));
//        menuModelList.add(new MenuModel(R.drawable.medicine, "সার ও কীটনাশক"));
//        menuModelList.add(new MenuModel(R.drawable.price, "আজকের বাজার"));
//        menuModelList.add(new MenuModel(R.drawable.news, "কৃষিতথ্য"));
//        menuModelList.add(new MenuModel(R.drawable.shop, "শপ"));
        menuModelList.add(new MenuModel(R.drawable.leaf, "fd"));
        menuModelList.add(new MenuModel(R.drawable.agriculture, "ff"));
        menuModelList.add(new MenuModel(R.drawable.medicine, "ff"));
        menuModelList.add(new MenuModel(R.drawable.price, "ff"));
        menuModelList.add(new MenuModel(R.drawable.news, "ff"));
        menuModelList.add(new MenuModel(R.drawable.shop, "ff"));
        MenuAdapter menuAdapter = new MenuAdapter(menuModelList);
        menuRecyclerView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

        //Tips
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


        //location
        //check condition
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(MainActivity.this
                , ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //if permission granted
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this
                    , new String[]{ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
                == PackageManager.PERMISSION_GRANTED)) {
            getCurrentLocation();
        } else {
            Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    location = task.getResult();
                    if (location != null) {
                        //set
                        lat = String.valueOf(location.getLatitude());
                        lon = String.valueOf(location.getLongitude());

                        Geocoder geocoder;
                        List<Address> addresses;
                        geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                            locationTextView.setText(address);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //get weather data using latitude and longitude
                        weatherService = ApiClient.getRetrofit().create(WeatherService.class);
                        Call<WeatherDataModel> call = weatherService.getCurrentWeatherData(lat, lon, AppId);
                        call.enqueue(new Callback<WeatherDataModel>() {
                            @Override
                            public void onResponse(Call<WeatherDataModel> call, Response<WeatherDataModel> response) {
                                final WeatherDataModel weatherDataModel = response.body();
                                assert weatherDataModel != null;
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       humText.setText(String.valueOf(weatherDataModel.getMain().getHumidity()));
                                   }
                               });
                                //Toast.makeText(getApplicationContext(),weatherDataModel.getWeathers().get(0).getDescription(),Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(Call<WeatherDataModel> call, Throwable t) {

                            }
                        });

                    } else {
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                super.onLocationResult(locationResult);
                                Location location1 = locationResult.getLastLocation();
                                //set
//                                locationTextView.setText(location1.getLatitude() + "," + String.valueOf(location1.getLongitude()));
                            }
                        };
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }

                }
            });
        } else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }


}
