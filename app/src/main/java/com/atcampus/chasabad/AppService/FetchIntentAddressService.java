package com.atcampus.chasabad.AppService;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FetchIntentAddressService extends IntentService {

    private ResultReceiver resultReceiver;

    public FetchIntentAddressService() {
        super("FetchIntentAddressService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(LocationConstants.RECEIVER);
            Location location = intent.getParcelableExtra(LocationConstants.LOCATION_DATA_EXTRA);
            if (location == null){
                return;
            }
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
            if (addresses == null || addresses.isEmpty()){
                deliveryResultToReceiver(LocationConstants.FAILURE_RESULT,errorMessage);
            }else {
                Address address = addresses.get(0);
                ArrayList<String> addressFragment = new ArrayList<>();
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                    addressFragment.add(address.getAddressLine(i));
                }
                deliveryResultToReceiver(
                        LocationConstants.SUCCESS_RESULT,
                        TextUtils.join(
                                Objects.requireNonNull(System.getProperty("line.separator")),
                                addressFragment
                                )
                );
            }
        }

    }

    private void deliveryResultToReceiver(int resultCode, String addressMessage){
        Bundle bundle = new Bundle();
        bundle.putString(LocationConstants.RESULT_DATA_KEY,addressMessage);
        resultReceiver.send(resultCode,bundle);
    }
}
