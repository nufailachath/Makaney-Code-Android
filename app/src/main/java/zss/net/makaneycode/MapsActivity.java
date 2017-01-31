package zss.net.makaneycode;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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
    public void onZooming(View view){
        if(view.getId() == R.id.zoomIn){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        if(view.getId() == R.id.zoomOut){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
    public void changeMapType(View view){
        if(mMap.getMapType()== GoogleMap.MAP_TYPE_NORMAL){
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            //Toast.makeText(this,"deug",Toast.LENGTH_SHORT).show();
        }
        else
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

    }
    public void onSearch(View view){

        EditText location_srch= (EditText) findViewById(R.id.makaney_search);
        String location = location_srch.getText().toString();

        Geocoder geocoder = new Geocoder(this);
        List<Address> addressList = null;
        try {
            addressList = geocoder.getFromLocationName(location,1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Address address = addressList.get(0);
        double lat = address.getLatitude();
        double lng = address.getLongitude();
        goToLocationZoom(lat,lng,12);

        /*
        LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
       */

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        goToLocation(36.5699702,-5.5012867);

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    //method for getting location  through latitude, longitude and zoom

    public void goToLocation(double lat,double lng){
        LatLng latLng = new LatLng(lat,lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

    }
    public void goToLocationZoom(double lat,double lng,float zoom){
        LatLng latLng = new LatLng(lat,lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,zoom);
        mMap.animateCamera(cameraUpdate);

    }
}
