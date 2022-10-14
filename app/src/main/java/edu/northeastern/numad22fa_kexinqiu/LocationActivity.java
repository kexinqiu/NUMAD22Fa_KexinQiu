package edu.northeastern.numad22fa_kexinqiu;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;
import java.util.logging.Logger;

public class LocationActivity extends AppCompatActivity {

    private TextView text_longitude;
    private TextView text_latitude;
    private TextView text_distance;
    private Button reset_btn;
    private LocationManager locationManager;
    private String locationProvider = null;

    public double[] startLocation;
    private double[] endLocation;
    private boolean flag = false;

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            if (!flag) {
                startLocation[0] = location.getLatitude();
                startLocation[1] = location.getLongitude();
                flag = true;
            }
            if (location != null) {
                endLocation[0] = location.getLatitude();
                endLocation[1] = location.getLongitude();

                Location loc1 = new Location("");
                loc1.setLatitude(startLocation[0]);
                loc1.setLongitude(startLocation[1]);

                Location loc2 = new Location("");
                loc2.setLatitude(endLocation[0]);
                loc2.setLongitude(endLocation[1]);

                float distanceInMeters = loc1.distanceTo(loc2);

                text_longitude.setText(String.valueOf(location.getLongitude()));
                text_latitude.setText(String.valueOf(location.getLatitude()));
                text_distance.setText(String.valueOf(distanceInMeters + " meters"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        text_longitude = findViewById(R.id.textView_longtitude);
        text_latitude = findViewById(R.id.textView_latitude);
        text_distance = findViewById(R.id.textView_dist);
        reset_btn = findViewById(R.id.loc_reset_btn);

        startLocation = new double[2];
        endLocation = new double[2];

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });

        //check
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //location manager
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    //provider
                    List<String> providers = locationManager.getProviders(true);
                    if (providers.contains(LocationManager.GPS_PROVIDER)) {
                        locationProvider = LocationManager.GPS_PROVIDER;
                    } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                        locationProvider = LocationManager.NETWORK_PROVIDER;
                    } else {
                        Toast.makeText(this, "No Location!", Toast.LENGTH_SHORT).show();
                    }
                    //get location
                    try {
                        locationManager.requestLocationUpdates(locationProvider, 1000, 0, locationListener);
                    } catch (SecurityException e) {
                    }
                }
                break;
            }
        }
    };

}
