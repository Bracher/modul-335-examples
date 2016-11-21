package com.example.hunziker.googlemapsapiexample;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    static final LatLng MELBOURNE = new LatLng(-37.813, 144.962);
    private Marker mSydneyMarker;
    private Marker mMelbourneMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        
        LatLng sydney = new LatLng(-33.867, 151.206);
        mSydneyMarker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMelbourneMarker = mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .title("Marker in Melbourne")
                .snippet("This is the marker in Melbourne!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(mSydneyMarker)) {
            Toast.makeText(getApplicationContext(), "Sydney!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if(marker.equals(mMelbourneMarker)) {
            Toast.makeText(getApplicationContext(), "Melbourne!", Toast.LENGTH_SHORT).show();
        }
    }
}
