package com.example.savelife.GeoFencing;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.example.savelife.R;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapsActivityGeoFence extends FragmentActivity implements OnMapReadyCallback, GeoQueryEventListener {

    private GoogleMap mMap;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentUser;
    private DatabaseReference mLocationRef;
    private GeoFire geoFire;

    private List<LatLng> dangerZone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        checkPermission();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());

        // add Circle
        for (LatLng latLng : dangerZone){

            mMap.addCircle(new CircleOptions().center(latLng)
                    .radius(400)
                    .strokeColor(Color.RED)
                    .fillColor(0x22FF0000)
                    .strokeWidth(5.0f)
            );

            // Creating Geoquery when user enters danger zone
            GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(latLng.latitude,latLng.longitude),0.5f);
            geoQuery.addGeoQueryEventListener(MapsActivityGeoFence.this);


        }
    }

    @Override
    protected void onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        super.onStop();
    }

    public void checkPermission(){

        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

                        buildLocationRequest();
                        buildLocationCallBack();
                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsActivityGeoFence.this);

                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(MapsActivityGeoFence.this);

                        initZone();

                        settingGeoFire();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        Toast.makeText(MapsActivityGeoFence.this, "Enable permission to access current location!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

    }

    private void initZone() {

        dangerZone = new ArrayList<>();

        dangerZone.add(new LatLng(11.026200, 76.930509));
        dangerZone.add(new LatLng(11.126010, 76.934476));
        dangerZone.add(new LatLng(11.226743, 76.931704));
        dangerZone.add(new LatLng(12.606060, 78.485728));
        dangerZone.add(new LatLng(12.602040, 78.439723));
        dangerZone.add(new LatLng(12.497182, 78.457042));
        dangerZone.add(new LatLng(12.499643, 78.528063));
        dangerZone.add(new LatLng(12.566511, 78.574710));
        dangerZone.add(new LatLng(12.528296, 78.487916));
        dangerZone.add(new LatLng(12.576261, 78.503996));
        dangerZone.add(new LatLng(12.567821, 78.506987));
        dangerZone.add(new LatLng(12.560910, 78.507588));
        dangerZone.add(new LatLng(9.851577, 78.158027));
        dangerZone.add(new LatLng(8.189745, 77.682088));
        dangerZone.add(new LatLng(8.581024, 78.099569));
        dangerZone.add(new LatLng(10.358154, 79.352010));
        dangerZone.add(new LatLng(10.314922, 79.571737));
        dangerZone.add(new LatLng(8.138005, 77.437412));
        dangerZone.add(new LatLng(12.717967, 80.183994));
        dangerZone.add(new LatLng(10.301338, 79.343538));
        dangerZone.add(new LatLng(10.949211, 79.782991));
        dangerZone.add(new LatLng(10.020173, 79.189729));
        dangerZone.add(new LatLng(9.153550, 78.398714));
        dangerZone.add(new LatLng(8.936556, 78.178987));
        dangerZone.add(new LatLng(10.647041, 79.826936));
        dangerZone.add(new LatLng(12.025819, 79.629183));
        dangerZone.add(new LatLng(12.133250, 79.892854));
        dangerZone.add(new LatLng(12.905451, 80.222444));
        dangerZone.add(new LatLng(12.991107, 80.244417));
        dangerZone.add(new LatLng(9.587138, 78.530550));
        dangerZone.add(new LatLng(10.992354, 77.541780));
        dangerZone.add(new LatLng(9.738765, 77.805452));
        dangerZone.add(new LatLng(11.466496, 76.684847));
        dangerZone.add(new LatLng(11.509561, 77.168245));
        dangerZone.add(new LatLng(11.982835, 78.025179));
        dangerZone.add(new LatLng(12.283580, 78.618440));
        dangerZone.add(new LatLng(9.457119, 77.563753));
        dangerZone.add(new LatLng(9.110162, 77.365999));
        dangerZone.add(new LatLng(10.992354, 77.783480));
        dangerZone.add(new LatLng(11.272623, 79.035921));
        dangerZone.add(new LatLng(10.539050, 78.926058));
        dangerZone.add(new LatLng(9.543804, 78.113069));
        dangerZone.add(new LatLng(10.020173, 77.541780));
        dangerZone.add(new LatLng(8.719433, 77.168245));
        dangerZone.add(new LatLng(11.197192, 79.508333));
        dangerZone.add(new LatLng(9.851577, 78.158027));
        dangerZone.add(new LatLng(10.754994, 78.695345));
        dangerZone.add(new LatLng(10.474238, 77.508821));
        dangerZone.add(new LatLng(11.660236, 77.135286));
        dangerZone.add(new LatLng(12.573259, 78.178987));
        dangerZone.add(new LatLng(12.219163, 79.134798));
        dangerZone.add(new LatLng(13.044627, 79.826936));
        dangerZone.add(new LatLng(11.121742, 78.003206));
        dangerZone.add(new LatLng(10.117527, 78.860140));
        dangerZone.add(new LatLng(10.959997, 79.414949));
        dangerZone.add(new LatLng(10.398606, 79.305086));
        dangerZone.add(new LatLng(11.240298, 79.464388));
        dangerZone.add(new LatLng(10.889881, 79.815950));
        dangerZone.add(new LatLng(11.665616, 78.211946));
        dangerZone.add(new LatLng(8.762868, 77.871370));
        dangerZone.add(new LatLng(9.283683, 79.277620));
        dangerZone.add(new LatLng(8.241321, 77.256136));
        dangerZone.add(new LatLng(10.652440, 79.848909));
        dangerZone.add(new LatLng(11.493412, 79.766512));
        dangerZone.add(new LatLng(11.218746, 78.113069));
        dangerZone.add(new LatLng(9.392091, 78.843660));
        dangerZone.add(new LatLng(9.224045, 79.354524));
        dangerZone.add(new LatLng(9.868676, 78.514070));
        dangerZone.add(new LatLng(11.558001, 79.013948 ));
        dangerZone.add(new LatLng(12.707260, 79.958772));
        dangerZone.add(new LatLng(13.044627, 80.145540));
        dangerZone.add(new LatLng(11.595671, 78.706331));
        dangerZone.add(new LatLng(12.889387, 78.893099));
        dangerZone.add(new LatLng(12.728694, 77.843904));
        dangerZone.add(new LatLng(10.959997, 79.332552));

    }

    private void settingGeoFire() {

        mLocationRef = FirebaseDatabase.getInstance().getReference("userLocation");
        geoFire = new GeoFire(mLocationRef);


    }

    private void buildLocationCallBack() {

        locationCallback = new LocationCallback(){

            @Override
            public void onLocationResult(final LocationResult locationResult) {
                if(mMap != null )
                {


                    geoFire.setLocation("userLocation", new GeoLocation(locationResult.getLastLocation().getLatitude(),
                            locationResult.getLastLocation().getLongitude()), new GeoFire.CompletionListener() {
                        @Override
                        public void onComplete(String key, DatabaseError error) {

                            if(currentUser != null) currentUser.remove();
                            currentUser = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(locationResult.getLastLocation().getLatitude(),
                                            locationResult.getLastLocation().getLongitude()))
                                    .title("You are here"));

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUser.getPosition(),12.0f));
                        }
                    });

                }
            }
        };


    }

    private void buildLocationRequest() {

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);

    }


    @Override
    public void onKeyEntered(String key, GeoLocation location) {

        sendNotification("User",String.format("%s entered danger zone",key));
    }

    @Override
    public void onKeyExited(String key) {

        sendNotification("User",String.format("%s Exited danger zone",key));
    }

    @Override
    public void onKeyMoved(String key, GeoLocation location) {

        sendNotification("User",String.format("%s inside danger zone",key));
    }



    @Override
    public void onGeoQueryReady() {

    }

    @Override
    public void onGeoQueryError(DatabaseError error) {

        Toast.makeText(this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();

    }


    private void sendNotification(String title, String Content) {

        String NOTIFICATION_CHANNEL_ID = "user_multiple_location";

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"My Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            //Configuration
            notificationChannel.setDescription("Channel Description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(Content)
                .setAutoCancel(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));

        Notification notification = builder.build();
        notificationManager.notify(new Random().nextInt(),notification);

    }
}
