package com.example.vaibhav.sahajya2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;

public class ReliefCamp extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener{

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 11.0f;
        // Add a marker in Sydney and move the camera
        // LatLng rvce = new LatLng(12.9249, 77.4994);


        LatLng reva = new LatLng(13.115518, 77.635889);
        mMap.addMarker(new MarkerOptions().position((reva))
                .title("Relief Camp  1"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(reva,zoomLevel));

        LatLng afstation = new LatLng(13.131517, 77.602406);
        mMap.addMarker(new MarkerOptions().position((afstation))
                .title("Relief Camp 2"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        //float zoomLevel = 16.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(afstation,zoomLevel));

        LatLng chalamakunte = new LatLng(13.114666, 77.719567);
        mMap.addMarker(new MarkerOptions().position((chalamakunte))
                .title("Relief Camp3"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chalamakunte,zoomLevel));

        LatLng pesit = new LatLng(12.9249052, 77.6275933);
        mMap.addMarker(new MarkerOptions().position((pesit))
                .title("Relief Camp4"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pesit,zoomLevel));
        //fghvhhh

        LatLng btm = new LatLng(12.919796, 77.610273);
        mMap.addMarker(new MarkerOptions().position((btm))
                .title("Relief Camp5"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(btm,zoomLevel));

        LatLng xyz = new LatLng(12.912350, 77.585382);
        mMap.addMarker(new MarkerOptions().position((xyz))
                .title("Relief Camp6"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(xyz,zoomLevel));

        LatLng abc = new LatLng(12.910673, 77.646539);
        mMap.addMarker(new MarkerOptions().position((abc))
                .title("Relief Camp7"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(abc,zoomLevel));

        LatLng def = new LatLng(12.970726, 77.578251);
        mMap.addMarker(new MarkerOptions().position((def))
                .title("Relief Camp8"));
        mMap.setMapType(mMap.MAP_TYPE_TERRAIN);
        //mMap.setMaxZoomPreference(20.0f);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(def,zoomLevel));





//        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
        //              .clickable(true)
        //          .add(
        //                    new LatLng(13.026727, 77.540901),
        //                new LatLng(13.115518, 77.635889),
        //              new LatLng(13.131517, 77.602406),
        //            new LatLng(13.114666, 77.719567)));
        //  googleMap.setOnPolylineClickListener(this);
        //googleMap.setOnPolygonClickListener(this);
    }


    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}

