package com.example.savelife;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.saveAN.SettingsActivity;

public class ModuleStoreMap extends AppCompatActivity {
    SwipeRefreshLayout mySwipeRefreshLayout;
    WebView simpleWebView;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_store_map);

        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);


        //Status bar

        if (android.os.Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        simpleWebView = (WebView) findViewById(R.id.simpleWebView);
        simpleWebView.setWebViewClient(new MyWebViewClient());
        String url = "https://www.google.com/maps/search/medical+store/@12.5455611,78.4891373,12z/data=!3m1!4b1";
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load a web page in a web view

        //web page load

        pd = new ProgressDialog(ModuleStoreMap.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        simpleWebView.reload();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(mySwipeRefreshLayout.isRefreshing()) {
                                    mySwipeRefreshLayout.setRefreshing(false);
                                }
                            }
                        }, 1000);
//                        simpleWebView.stopLoading();
                    }
                }

        );

        simpleWebView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog = new ProgressDialog(ModuleStoreMap.this);

//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                pd = new ProgressDialog(WebsiteView.this);
//                pd.setMessage("Please wait");
//                pd.setCancelable(false);
//                pd.show();
//                progressDialog.setTitle("Loading...");
//                progressDialog.setMessage("Please wait...");
//                progressDialog.setCancelable(false);
//                progressDialog.show();
//            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                if (pd != null){
//                    progressDialog.dismiss();
                    pd.dismiss();
                }
            }

        });


    }
    //back page website
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (simpleWebView.canGoBack()) {
                        simpleWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.website_view,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.translate:
                Intent intent = new Intent(this, WebsiteView.class);
                startActivity(intent);
                return true;
            case R.id.more:
                Intent intent1 = new Intent(this, TermsAndConditions.class);
                startActivity(intent1);
                return true;
            case R.id.settings:
                Intent intent2 = new Intent(this, SettingsActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

//package com.example.savelife;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Location;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.FragmentActivity;
//
//import com.example.savelife.Object.MapObject;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.material.bottomsheet.BottomSheetBehavior;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.android.material.snackbar.Snackbar;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class ModuleStoreMap extends FragmentActivity implements OnMapReadyCallback{
//
//    private final String TAG = "HomeMap";
//    private GoogleMap googleMaps;
//    private FusedLocationProviderClient fusedLocationClient;
//    SupportMapFragment mapFragment;
//    private double lat = 0.0, lon = 0.0;
//    private static final int LOCATION_PERMISSION_CODE = 100;
//    BottomSheetDialog dialog;
//    MarkerOptions listmarkeroption;
//    MarkerOptions markerOptions;
//    Marker marker;
//
//    ArrayList<MapObject> mapObjects = new ArrayList<>(10);
//    HashMap<LatLng,String> address =new HashMap<>();
//
//    // Bottom sheet
//    private BottomSheetBehavior bottomSheetBehavior;
//    private Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.module_store_map);
//
//        button = findViewById(R.id.button);
//        View bottomSheet = findViewById(R.id.bottom_sheet);
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        checkPermission();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ModuleStoreMap.this, "Loading", Toast.LENGTH_SHORT).show();
//                mapFunc();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.basic_topbar,menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.website:
//                Intent intent = new Intent(this, WebsiteView.class);
//                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        if(googleMap != null) {
//
//            googleMaps = googleMap;
//
//            LatLng latLng = new LatLng(lat, lon);
//            googleMaps.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .draggable(true)
//                    .title("Your Location"));
//
//            googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            googleMaps.animateCamera(CameraUpdateFactory.zoomTo(15));
//            googleMaps.getUiSettings().setZoomControlsEnabled(true);
//
////            googleMaps.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
////                @Override
////                public boolean onMarkerClick(Marker marker) {
////
////                    if(marker.getTag() != null) {
////
////                        System.out.println("marker.getTag() "+marker.getTag());
////
////                    }
////
////                    return true;
////                }
////            });
//        }
//
//
//
//
//    }
//
//
//    private void checkPermission() {
//        if (ContextCompat.checkSelfPermission(ModuleStoreMap.this, Manifest.permission.ACCESS_FINE_LOCATION) + ContextCompat.checkSelfPermission(ModuleStoreMap.this,Manifest.permission.ACCESS_FINE_LOCATION)
//                + ContextCompat.checkSelfPermission(ModuleStoreMap.this,Manifest.permission.CALL_PHONE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale (ModuleStoreMap.this, Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(ModuleStoreMap.this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    && ActivityCompat.shouldShowRequestPermissionRationale(ModuleStoreMap.this, Manifest.permission.CALL_PHONE)) {
//
//                Snackbar.make(ModuleStoreMap.this.findViewById(android.R.id.content),
//                        "Please Grant Permissions to Access your location and storage",
//                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                requestPermissions(
//                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE},
//                                        LOCATION_PERMISSION_CODE);
//                            }
//                        }).show();
//            } else {
//                requestPermissions(
//                        new String[]{Manifest.permission
//                                .ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE},
//                        LOCATION_PERMISSION_CODE);
//            }
//        } else {
//            // write your logic code if permission already granted
//            getCurrentLocation();
//
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        switch (requestCode) {
//            case LOCATION_PERMISSION_CODE:
//                if (grantResults.length > 0) {
//                    boolean readStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean fineLocation = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//                    boolean callPhone = grantResults[2] == PackageManager.PERMISSION_GRANTED;
//
//
//                    if(fineLocation && readStorage && callPhone)
//                    {
//                        getCurrentLocation();
//
//                    } else {
//                        Snackbar.make(ModuleStoreMap.this.findViewById(android.R.id.content),
//                                "Please Grant Permissions Access Current Location and Storage",
//                                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
//                                new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        requestPermissions(
//                                                new String[]{Manifest.permission
//                                                        .ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE},
//                                                LOCATION_PERMISSION_CODE);
//                                    }
//                                }).show();
//                    }
//                }
//                break;
//        }
//    }
//
//
//    private void getCurrentLocation() {
//
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        // Got last known location. In some rare situations this can be null.
//                        if (location != null) {
//                            // Logic to handle location object
//                            lat = location.getLatitude();
//                            lon = location.getLongitude();
//                            System.out.println("\n\n\n"+"Lat "+location.getLatitude()+"\n\n\n");
//                            System.out.println("Lon "+location.getLongitude()+"\n\n\n");
//
//                            mapFragment  = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_home);
//                            mapFragment.getMapAsync(ModuleStoreMap.this);
//
//
//                        }
//                    }
//                });
//    }
//
//
//
//    private class Geocoders extends AsyncTask<MapObject, Void, LatLng> {
//
//        MapObject mapList;
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected LatLng doInBackground(MapObject... mapObjects) {
//
//            mapList = mapObjects[0];
//            android.location.Geocoder geocoder = new android.location.Geocoder(ModuleStoreMap.this);
//
//            List<Address> list = new ArrayList<>();
//
//            if (mapList.getMap_address1().equals("")) {
//                return null;
//            }
//            try {
//                list = geocoder.getFromLocationName(mapList.getMap_address1(), 2);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            if (list.size() == 0) {
//                return null;
//            } else {
//                address.put(new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude()), mapList.getMap_address1());
//                return new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
//            }
//
//        }
//
//        @Override
//        protected void onPostExecute(LatLng latLng) {
//            super.onPostExecute(latLng);
//
//            if (latLng != null && googleMaps != null) {
//
//                listmarkeroption = new MarkerOptions()
//                        .position(latLng)
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
//
//                googleMaps.addMarker(listmarkeroption);
//
//            }
//        }
//
//    }
//
//    public void mapFunc(){
//
//
//        mapObjects.add(new MapObject("Natrampalli -Tirupattur Kuppam Rd, Natrampalli, Tamil Nadu 635852"));
//        mapObjects.add(new MapObject("K. 635854, Bandarapalli, Tamil Nadu"));
//        mapObjects.add(new MapObject("Thiriyalam, Tamil Nadu 635851"));
//        mapObjects.add(new MapObject("Natrampalli, Tamil Nadu 635852"));
//        mapObjects.add(new MapObject("Kudiyanakuppam, Tamil Nadu 635851"));
//        mapObjects.add(new MapObject("GH Rd, Achamangalam, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("185-186, Munichalai Rd, Nelpettai, Krishna Puram, Madurai, Tamil Nadu 625009"));
//        mapObjects.add(new MapObject("GH Rd, Achamangalam, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("New No. 51, Old No. 18, Bunder Garden Street, Opp Lourdes School, Perambur, Chennai, Tamil Nadu 600011"));
//        mapObjects.add(new MapObject("21/7B, Thandavarayan St, Otteri, Old Washermanpet, Chennai, Tamil Nadu 600084"));
//        mapObjects.add(new MapObject("21, Dr Thirumoorthy Nagar Main Rd, Tirumurthy Nagar, Nungambakkam, Chennai, Tamil Nadu 600034"));
//        mapObjects.add(new MapObject("21 Greams Lane, Off, Greams Road, Thousand Lights West, Thousand Lights, Chennai, Tamil Nadu 600006"));
//        mapObjects.add(new MapObject("1, West Cott Road, Royapettah, Chennai, Tamil Nadu 600014"));
//        mapObjects.add(new MapObject("81, TTK Road, Junction, CIT Colony, Alwarpet, Chennai, Tamil Nadu 600018"));
//        mapObjects.add(new MapObject("242b, Trichy Rd, Mathiyalagan Nagar, PKT Nagar, Sulur, Tamil Nadu 641402"));
//        mapObjects.add(new MapObject("Tiruppur - Perumanallur Rd, Kanakkam Palayam Pirivu, Tamil Nadu 641666"));
//        mapObjects.add(new MapObject("Avinashi Rd, Peelamedu, Coimbatore, Tamil Nadu 641004"));
//        mapObjects.add(new MapObject("76/A, Mettupalayam Rd, VKL Nagar, Thudiyalur, Tamil Nadu 641034"));
//        mapObjects.add(new MapObject("Annur Rd, Mettupalayam, Tamil Nadu 641301"));
//        mapObjects.add(new MapObject("4 /120-F, Pandikovil Ring Road, Mattuthavani Airport High way, Tamil Nadu 625107"));
//        mapObjects.add(new MapObject("249, Vishwanathapuram, New Natham Connection Road, Iyer Bungalow, Madurai, Tamil Nadu 625014"));
//        mapObjects.add(new MapObject("Nagamalaipudukottai, Tamil Nadu 625019"));
//        mapObjects.add(new MapObject("3/424, Ring Road Junction, Sivagangai Main Rd, near Pandi Kovil, Madurai, Tamil Nadu 625020"));
//        mapObjects.add(new MapObject("19 B, North St, Kamrajapuram, Tamil Nadu 625009"));
//        mapObjects.add(new MapObject("50B, Bypass Rd, Opp. Aparna Tower, Ponmeni, Madurai, Tamil Nadu 625010"));
//        mapObjects.add(new MapObject("Gandhi Rd, Srirangam, Tiruchirappalli, Tamil Nadu 620006"));
//        mapObjects.add(new MapObject("No 46, near, Super Bazaar, Singarathope, Devathanam, Tiruchirappalli, Tamil Nadu 620008"));
//        mapObjects.add(new MapObject("6, Royal Rd, Cantonment, Tiruchirappalli, Tamil Nadu 620001"));
//        mapObjects.add(new MapObject("Chennai Bypass Road Ariyamangalam Area, Old Palpannai, Tiruchirappalli, Tamil Nadu 620010"));
//        mapObjects.add(new MapObject("No 95, 1, Pattabiraman Salai, beside maruti hospital, Anna Nagar, Tennur, Tiruchirappalli, Tamil Nadu 620017"));
//        mapObjects.add(new MapObject("A/10/156, Salai Rd, Thillai Nagar East, Tennur, Tiruchirappalli, Tamil Nadu 620018"));
//        mapObjects.add(new MapObject("1, Annamalai Nagar Main Rd, Woraiyur, Tiruchirappalli, Tamil Nadu 620018"));
//        mapObjects.add(new MapObject("Indira Nagar, Meenachinayakkanpatti, Pallapatti, Tamil Nadu 624001"));
//        mapObjects.add(new MapObject("1/1, Siluvathur Rd, MSP North Teacher's Colony, NGO Colony, Elil Nagar, Dindigul, Tamil Nadu 624003"));
//        mapObjects.add(new MapObject("11, 91, NVGB Hall Rd, EB Colony, Nagal Nagar, Tamil Nadu 624003"));
//        mapObjects.add(new MapObject("34, Narayana Nagar, Naicker New 1st St, Narayana Nagar, Dindigul, Tamil Nadu 624001"));
//        mapObjects.add(new MapObject("No: 53, Dindigul - Palani Rd, opposite Sornam Honda, New Agraharam, west, Dindigul, Tamil Nadu 624001"));
//        mapObjects.add(new MapObject("80 Feet Road, Nehruji Nagar, Dindigul, Tamil Nadu 624001"));
//        mapObjects.add(new MapObject("10/210, Veerappanchatram, Tamil Nadu 638004"));
//        mapObjects.add(new MapObject("16, Palaniappa St, opp. to GH, Nggo Colony, Erode, Tamil Nadu 638009"));
//        mapObjects.add(new MapObject("Housing Unit, Nadarmedu, Tamil Nadu 638002"));
//        mapObjects.add(new MapObject("No: 78/1-10, Perundurai Road, Parimalam Mahal, behind Skoda Show Room, Erode, Tamil Nadu 638012"));
//        mapObjects.add(new MapObject("02/2, Gandhi Nagar Colony, Perundurai Road, Erode, Tamil Nadu 638011"));
//        mapObjects.add(new MapObject("No: 78/1-10, Perundurai Road, Parimalam Mahal, behind Skoda Show Room, Erode, Tamil Nadu 638012"));
//        mapObjects.add(new MapObject("17-A, Hospital Road, Cuddalore, Tamil Nadu 607001"));
//        mapObjects.add(new MapObject("Padaleeswarar Koil St, Muthaiya Nagar, Thirupapuliyur, Cuddalore, Tamil Nadu 607001"));
//        mapObjects.add(new MapObject("Friends Nagar, Chavadi, Kondur, Tamil Nadu 607006"));
//        mapObjects.add(new MapObject("Allpettai, Manjakuppam, Cuddalore, Tamil Nadu 607001"));
//        mapObjects.add(new MapObject("47, Gundusalai Road, Pondy Bye-pass, Cuddalore, Tamil Nadu 607001"));
//        mapObjects.add(new MapObject("Semmandalam, Tamil Nadu 607001"));
//        mapObjects.add(new MapObject("Lakkiampatty, Dharmapuri, Tamil Nadu 636705"));
//        mapObjects.add(new MapObject("Boys School, 4/486-A, Pennagaram Main Road Near Vijay Vidhyalaya, Dharmapuri, Tamil Nadu 636701"));
//        mapObjects.add(new MapObject("Nethaji Bypass Rd, Dharmapuri, Tamil Nadu 636701"));
//        mapObjects.add(new MapObject("No: 4, Sengodipuram Road, Dharmapuri, Tamil Nadu 636701"));
//        mapObjects.add(new MapObject("No 72 B, Salem Main Rd, Dharmapuri, Tamil Nadu 636705"));
//        mapObjects.add(new MapObject("Devangar St, Dharmapuri, Tamil Nadu 636701"));
//        mapObjects.add(new MapObject("Palaiyur, Tamil Nadu 611001"));
//        mapObjects.add(new MapObject("Elancheran Nagar, Tamil Nadu 611001"));
//        mapObjects.add(new MapObject("Naganathar Sannathi, Melakottaivasal, Nagore, Nagapattinam, Tamil Nadu 611001"));
//        mapObjects.add(new MapObject("t, Sattayaper East Street, ASN Colony, Melakottaivasal, Nagapattinam, Tamil Nadu 611003"));
//        mapObjects.add(new MapObject("Naduvar Sannathi, Nagapattinam, East Coast Road, Nagapattinam, Nagapattinam, 611001"));
//        mapObjects.add(new MapObject("RJR Herbal Hospitals Mak Complex Durairaj Pillai Illam No-1, Kariyangudy, Chetty St, Velippalayam, Nagapattinam, Tamil Nadu 611001"));
//        mapObjects.add(new MapObject("5, Tiruchengode - Namakkal - Trichy Rd, S P Pudur, Thillaipuram, Namakkal, Tamil Nadu 637001"));
//        mapObjects.add(new MapObject("131/4, Trichy Main Road, Ganesapuram, K K Nagar, Trichy Main Rd, Ganesapuram, K K Nagar, Namakkal, Tamil Nadu 637001"));
//        mapObjects.add(new MapObject("K K Nagar, Namakkal, Tamil Nadu 637001"));
//        mapObjects.add(new MapObject("Paramathi Rd, Thillaipuram, Namakkal, Tamil Nadu 637001"));
//        mapObjects.add(new MapObject("452/454, Salem Main Road, Near KMS Lodge, Namakkal, Tamil Nadu 637001"));
//        mapObjects.add(new MapObject("6/917, C E B Colony, Paramathi Road, Namakkal, Tamil Nadu 637001"));
//        mapObjects.add(new MapObject("Unnamed Rd,, Sungu Pettai, Perambalur, Tamil Nadu 621212"));
//        mapObjects.add(new MapObject("Thuraiyur - Perambalur Rd, opp. aavin milk depot, Thuraimangalam, Perambalur, Tamil Nadu 621212"));
//        mapObjects.add(new MapObject("Vadakkumadevi Rd, Samiyappa Nagar, Perambalur, Tamil Nadu 621212"));
//        mapObjects.add(new MapObject("#154, Venketwasapuram, Tamil Nadu 621212"));
//        mapObjects.add(new MapObject("#482 /2B, Venkatesapuram,Near New Bus Stand, SH 142, Perambalur, Tamil Nadu 621212"));
//        mapObjects.add(new MapObject("Sungu Pettai, Perambalur, Tamil Nadu 621212"));
//        mapObjects.add(new MapObject("No. 83, Trchy-Pudukottai, Ramanathapuram Rd, Charles Nagar, Palace Nagar, Pudukkottai, Tamil Nadu 622003"));
//        mapObjects.add(new MapObject("opp. to SP office, M.K.K.Nagar, Rajagopalapuram, Pudukkottai, Tamil Nadu 622003"));
//        mapObjects.add(new MapObject("Wadair Santha, Brindavan, Pudukkottai, Tamil Nadu 622001"));
//        mapObjects.add(new MapObject("4215, S 4th St, T Nagar, Kadukkakadu, Pudukkottai, Tamil Nadu 622001"));
//        mapObjects.add(new MapObject("Alangudi Rd, Ashok Nagar, Senthamiz Nagar, Pudukkottai, Tamil Nadu 622001"));
//        mapObjects.add(new MapObject("Near, Keeranur Bus Stand Rd, Santhanathapuram, Tamil Nadu 622001"));
//        mapObjects.add(new MapObject("Kenikarai, Ramanathapuram, Tamil Nadu 623501"));
//        mapObjects.add(new MapObject("No:6/1155-1,D'block opp to RTO office,Rameswaram Road,Ramanathapuram."));
//        mapObjects.add(new MapObject("Gobilthankavelu, Ramanathapuram, Tamil Nadu 623501"));
//        mapObjects.add(new MapObject("Kochi - Madurai - Dhanushkodi Road, Davendrar Nagar, Periyar Nagar, Ramanathapuram, Tamil Nadu 623501"));
//        mapObjects.add(new MapObject("2, Kollam Pattirai St, Vasantha Nagar, Ramanathapuram, Tamil Nadu 623504"));
//        mapObjects.add(new MapObject("Om Sakthi Nagar, Nehru Nagar, Ramanathapuram, Tamil Nadu 623501"));
//        mapObjects.add(new MapObject("9/50, Trichy Main Rd, Opp.to Chandra Mahal, M G R Nagar, Seelanaickenpatti, Salem, Tamil Nadu 636201"));
//        mapObjects.add(new MapObject("Sewapet, Salem, Tamil Nadu 636002"));
//        mapObjects.add(new MapObject("Kalarampatti, Erumapalayam, Salem, Tamil Nadu 636006"));
//        mapObjects.add(new MapObject("202, Bangalore Bypass Road, Salem, Tamil Nadu 636004"));
//        mapObjects.add(new MapObject("5, Rajaji Rd, Seerangapalayam, Salem, Tamil Nadu 636007"));
//        mapObjects.add(new MapObject("Gandhinagar east, Siddhar Kovil Rd, Elumathanoor, Elampillai, Tamil Nadu 637502"));
//        mapObjects.add(new MapObject("Madurai Main Road, Managiri, Sivagangai District, Karaikudi, Tamil Nadu 630307"));
//        mapObjects.add(new MapObject("CB Colony, Sivaganga, NH-230, Madurai Thondi Road, Sivaganga, Sivaganga, 630561"));
//        mapObjects.add(new MapObject("4 /120-F, Pandikovil Ring Road, Mattuthavani Airport High way, Tamil Nadu 625107"));
//        mapObjects.add(new MapObject("22, Sivaganga Rd, Near Anna Bus stand, Gandhi Nagar, Shenoy Nagar, Sathamangalam, Tamil Nadu 625020"));
//        mapObjects.add(new MapObject("19 B, North St, Kamrajapuram, Tamil Nadu 625009"));
//        mapObjects.add(new MapObject("261, Melur - Azhagarkovil Road, Nagaikadai Bazaar, Melur, Tamil Nadu 625106"));
//        mapObjects.add(new MapObject("NO 3279/3281/1 Arulananda Nagar Road, Main Road, VOC Nagar, Thanjavur, Tamil Nadu 613007"));
//        mapObjects.add(new MapObject("111, Medical College - Vallam Rd, opp. South Zone Cultural Center, Krishna Nagar, Jayalakshmi Nagar, Thanjavur, Tamil Nadu 613004"));
//        mapObjects.add(new MapObject("244/2, Trichy Main Road, Near New Bus Stand, Nilagiri, Therkku Thottam, Thanjavur, Tamil Nadu 613005"));
//        mapObjects.add(new MapObject("6, NH 83, 6th Street, Cauvery Nagar South, Thanjavur, Tamil Nadu 613007"));
//        mapObjects.add(new MapObject("No: 1085/2, Mission Church Street, Maharnonbu Chavadi, Thanjavur, Tamil Nadu 613001"));
//        mapObjects.add(new MapObject("No.26, Shivaji Nagar, Oppo. Adlabs Theatre, Tamil Nadu 613001"));
//        mapObjects.add(new MapObject("Viswanathadass Nagar, Theni Allinagaram, Tamil Nadu 625531"));
//        mapObjects.add(new MapObject("63/2, Lake Road, Sri Ram Nagar, Theni Allinagaram, Tamil Nadu 625531"));
//        mapObjects.add(new MapObject("146, Old GH Rd, Municipality Colony, NRT Nagar, Theni Allinagaram, Tamil Nadu 625531"));
//        mapObjects.add(new MapObject("Periyakulam Road, Sri Ram Nagar, opp. Aravind Eye Hospital, Theni Allinagaram, Tamil Nadu 625531"));
//        mapObjects.add(new MapObject("NRT Nagar, Theni Allinagaram, Tamil Nadu 625531"));
//        mapObjects.add(new MapObject("Cumbum Rd, MRR Nagar, Palani Chettipatti, Tamil Nadu 625531"));
//        mapObjects.add(new MapObject("10-10/A, Mettupalayam Annur Rd, Mettupalayam, Tamil Nadu 641301"));
//        mapObjects.add(new MapObject("Near, Railway Station Rd, Nilambur, Kerala 679330"));
//        mapObjects.add(new MapObject("Hospital Rd, Upper Bazar, Ooty, Tamil Nadu 643001"));
//        mapObjects.add(new MapObject("Near, YWCA Rd, Bedford, Coonoor, Tamil Nadu 643101"));
//        mapObjects.add(new MapObject("Plot No.1 Thadagam Main Road, Near, Raju Gardens Rd, TVS Nagar, Coimbatore, Tamil Nadu 641025"));
//        mapObjects.add(new MapObject("Nethaji Rd, Opposite Selvan Hospital, Naniyar Nagar, Raja Nagar, Melapalayam, Tirunelveli, Tamil Nadu 627005"));
//        mapObjects.add(new MapObject("Royal Hospital 2nd Sharon Street, Kamarajar Rd, Tirunelveli, Tamil Nadu 627007"));
//        mapObjects.add(new MapObject("110E, 20/1, N Bypass Rd, Vannarpettai, Tirunelveli, Tamil Nadu 627003"));
//        mapObjects.add(new MapObject("82, Near Junction Flyover, Kailasapuram, Middle Street, Tirunelveli Town, Meenakshipuram, Tirunelveli, Tamil Nadu 627001"));
//        mapObjects.add(new MapObject("Door No. 66G/3, Trivandrum Road, Vasanth Nagar, Tirunelveli, Tamil Nadu 627005"));
//        mapObjects.add(new MapObject("1/111, Somasinayanar Street, Murugankurichi, Palayamkottai, Tirunelveli, Tamil Nadu 627002"));
//        mapObjects.add(new MapObject("67a, Kaliamman Koil St, Phase 1, Abirami Nagar, Virugambakkam, Chennai, Tamil Nadu 600092"));
//        mapObjects.add(new MapObject("Azad Nagar Govindan Street Aminjikarai, Ayyavoo Colony, Balavinayagar Nagar, Chennai, Tamil Nadu 600029"));
//        mapObjects.add(new MapObject("No 31, Railway Border Rd, Akbarabad, Kodambakkam, Chennai, Tamil Nadu 600024"));
//        mapObjects.add(new MapObject("No. 1051, Poonamalle High Road, Amaravathi Nagar, Arumbakkam, Chennai, Tamil Nadu 600106"));
//        mapObjects.add(new MapObject("No.1, Inner Ring Rd, Vadapalani, Chennai, Tamil Nadu 600026"));
//        mapObjects.add(new MapObject("9C, 4th Ave, AA Block, Shanthi Colony, Anna Nagar, Chennai, Tamil Nadu 600040"));
//        mapObjects.add(new MapObject("No. 54, Chengam Road, Shivaji Nagar, Athiyandal, Tamil Nadu 606603"));
//        mapObjects.add(new MapObject("NH-234, Vellore Tiruvannamalai Road, Tiruvannamalai, Tamil Nadu 606604"));
//        mapObjects.add(new MapObject("No. 57, Thindivanam Road, Anna salai, Tiruvannamalai, Tamil Nadu 606601"));
//        mapObjects.add(new MapObject("No: 36 Muthu Vinaynagar Koil Street nr. sree shai bhavan international hotel, Mathalangulam, Tiruvannamalai, Tamil Nadu 606601"));
//        mapObjects.add(new MapObject("Manalurpet Road, SH 9A, Bavaji Nagar, Tiruvannamalai, 606603 "));
//        mapObjects.add(new MapObject("Pei Gopuram First Street No.10/7c,Mannu pillai Nagar, Tamil Nadu 606601"));
//        mapObjects.add(new MapObject("Valavaikal, Thiruvarur, NH-67, Nagapattinam Coimbatore Gundlupet Highway, Thiruvarur, Thiruvarur, 610003"));
//        mapObjects.add(new MapObject("610001, Rajgopal Nagar, Santhamangalam, Thiruvarur, Tamil Nadu"));
//        mapObjects.add(new MapObject("15 vadakku vada poolai StreetRahmath Nagar, 610001, Kodikkalpalayam, Thiruvarur, Tamil Nadu 610001"));
//        mapObjects.add(new MapObject("Vilamal, Thiruvarur, SH-202, Thiruvarur Mannargudi Muthupet Road, Thiruvarur, Thiruvarur, 610001  "));
//        mapObjects.add(new MapObject("West St, Malai Nagar, Thiruvarur, Tamil Nadu 610001"));
//        mapObjects.add(new MapObject("Swamy Mada St, Madapuram, Thiruvarur, Tamil Nadu 610001"));
//        mapObjects.add(new MapObject("Periyanayagapuram Road, Periyanayagapuram, Thoothukudi, Tamil Nadu 628101"));
//        mapObjects.add(new MapObject("61, Kaliappa Pillai St, Mattakadai, Vadaku Theru, Cruz Puram, Thoothukudi, Tamil Nadu 628001"));
//        mapObjects.add(new MapObject("Davis Puram, Thoothukudi, Tamil Nadu 628002"));
//        mapObjects.add(new MapObject("26, Thiruchendur Road Oppo. Annammal College Of Education For Women, Tamil Nadu 628003"));
//        mapObjects.add(new MapObject("Harbour Estate, Tuticorin Beach Road, Salt Pans, Muttayyapuram, Tamil Nadu 628004"));
//        mapObjects.add(new MapObject("70/5, Main road, Poobalarayerpuram, Thoothukudi, Tamil Nadu 628001"));
//        mapObjects.add(new MapObject("Tiruppur - Vanjipalayam - Avinashi Rd, Vanjipalayam, Pudupalayam, Tamil Nadu 641663"));
//        mapObjects.add(new MapObject("Palayakadu, Tiruppur, Tamil Nadu 641607"));
//        mapObjects.add(new MapObject("19, Kathir Nagar, Kangeyam Road, Tiruppur, Tamil Nadu 641604"));
//        mapObjects.add(new MapObject("No 10, Valayangadu North Main Rd, Valayankadu South, Valagan, Kumar Nagar, Tiruppur, Tamil Nadu 641603"));
//        mapObjects.add(new MapObject("Semmipalayam, Tamil Nadu 641662"));
//        mapObjects.add(new MapObject("Tarapada Vedu, Taj Complex, No. 7/8, Pilliyar Kovil Street, Road, Vellore, Tamil Nadu 632007"));
//        mapObjects.add(new MapObject("Arani Road, Opposite Staff Quarters, Adukkamparai, Vellore, Tamil Nadu 632011"));
//        mapObjects.add(new MapObject("Kavanur, Tamil Nadu 632507"));
//        mapObjects.add(new MapObject("Kellys Rd, Navalpur, Ranipet, Tamil Nadu 632401"));
//        mapObjects.add(new MapObject("Mutharasikuppam, Tamil Nadu 632516"));
//        mapObjects.add(new MapObject("Arni Rd, near dinesh hospital, Kosapet, Vellore, Tamil Nadu 632001"));
//        mapObjects.add(new MapObject("KK Rd, K K Nagar, Viluppuram, Tamil Nadu 605602"));
//        mapObjects.add(new MapObject("NO:1,2 Singarathoppu St, Hospital Rd, Viluppuram, Tamil Nadu 605602"));
//        mapObjects.add(new MapObject("No: 111/243, Thiru Vi Ka Road, Near Gandhi Statue, Viluppuram, Tamil Nadu 605602"));
//        mapObjects.add(new MapObject("Thiru Vi Ka Street, Viluppuram, Tamil Nadu 605602"));
//        mapObjects.add(new MapObject("E Pondy Rd, Near Gandhi Statue, Mandhakarai, Viluppuram, Tamil Nadu 605602"));
//        mapObjects.add(new MapObject("355, East Coast Rd, Sudhakar Nagar, K K Nagar, Viluppuram, Tamil Nadu 605602"));
//        mapObjects.add(new MapObject("Service Rd, GT Nagar, Virudhunagar, Tamil Nadu 626001"));
//        mapObjects.add(new MapObject("2/496-2 sulakkarai medu near tamil nadu armed reserve, VOC Nagar, Sulakkarai, Virudhunagar, Tamil Nadu 626003"));
//        mapObjects.add(new MapObject("RR Nagar, MGR Nagar, Ganesh Nagar, Sivagami Puram, Virudhunagar, Tamil Nadu 626001"));
//        mapObjects.add(new MapObject("MGR Nagar, Anna Nagar, Virudhunagar, Tamil Nadu 626001"));
//        mapObjects.add(new MapObject("20, 1, Link Rd, Professors colony, Anna Nagar, Virudhunagar, Tamil Nadu 626001"));
//        mapObjects.add(new MapObject("Rajajinagar, Ariyalur, Tamil Nadu 621704"));
//        mapObjects.add(new MapObject("Komutty Theru, Ethraj Nagar, Ariyalur, Tamil Nadu 621704"));
//        mapObjects.add(new MapObject("3rd cross, Alagappa Nagar, Vila Ngara, Ariyalur, Tamil Nadu 621704"));
//        mapObjects.add(new MapObject("27 E, Pattunoolkara Theru, Ethraj Nagar, Ariyalur, Tamil Nadu 621704"));
//        mapObjects.add(new MapObject("No 5, Perumal Koil Street, MIN Nagar, Ariyalur, Tamil Nadu 621704"));
//        mapObjects.add(new MapObject("27, Pattunool Kara St, Near Anuba Gas Station, Rajajinagar, Ariyalur, Tamil Nadu 621704"));
//        mapObjects.add(new MapObject("7 Vallal Pachiyappan street, SH58, (Moongil Mandapam Stop), Kanchipuram, Tamil Nadu 631501"));
//        mapObjects.add(new MapObject("Hospital Rd, Ennaikaran, Tamil Nadu 631501"));
//        mapObjects.add(new MapObject("Mettupalayam St, Ennaikaran, Kanchipuram, Tamil Nadu 631501"));
//        mapObjects.add(new MapObject("55A, SVN Pillai St, Pillaiyarpalayam, Kanchipuram, Tamil Nadu 631502"));
//        mapObjects.add(new MapObject("No.21,Gokulam St, varadharaja perumal koil back side,tollgate, Tamil Nadu 631501"));
//        mapObjects.add(new MapObject("Gandhi Nagar, Vandavasi Rd, next to ramanas cafe, Kanchipuram, Tamil Nadu 631502"));
//        mapObjects.add(new MapObject("Kanyakumari, Tamil Nadu 629702"));
//        mapObjects.add(new MapObject("5/1a,, Main Rd, Vivekanandapuram, Kanyakumari, Tamil Nadu 629702"));
//        mapObjects.add(new MapObject("#3/6A, 629702, North St, Kanyakumari, Tamil Nadu 629203"));
//        mapObjects.add(new MapObject("College Rd, Agasteeswaram, Tamil Nadu 629701"));
//        mapObjects.add(new MapObject("179 A2, Ganesapuram road, Chidambara Nagar, Kottar, Tamil Nadu 629001"));
//        mapObjects.add(new MapObject("Asraman, Tamil Nadu 629704"));
//        mapObjects.add(new MapObject("5/300Andankoil East, Kovai Main Rd, Ashok Nagar, Karur, Tamil Nadu 639002"));
//        mapObjects.add(new MapObject("20 Kamachilamman Koil, North Street, North Street, Karur, Tamil Nadu 639002"));
//        mapObjects.add(new MapObject("Kamarajapuram, Karur, Tamil Nadu 639002"));
//        mapObjects.add(new MapObject("N Pradhaksanam Rd, Ramkrishnapuram, Karur, Tamil Nadu 639001"));
//        mapObjects.add(new MapObject("13/75a minnampalli pirivu,.,, Panchamadevi, 639004"));
//        mapObjects.add(new MapObject("39, Pradarshanam Road, Ramkrishnapuram, Karur, Tamil Nadu 639001"));
//        mapObjects.add(new MapObject("Unnamed Road, Pochampalli, Wahab Nagar, Krishnagiri, Tamil Nadu 635001"));
//        mapObjects.add(new MapObject("Rayakottai Rd, Rajaji Nagar, Krishnagiri, Tamil Nadu 635001"));
//        mapObjects.add(new MapObject("Thiruvalluvar Nagar, Krishnagiri, Tamil Nadu 635001"));
//        mapObjects.add(new MapObject("Old Post Office Rd, Co-operative Colony, New Pet, Krishnagiri, Tamil Nadu 635001"));
//        mapObjects.add(new MapObject("4th Cross Rd, Co-operative Colony, Thiruvalluvar Nagar, Krishnagiri, Tamil Nadu 635001"));
//        mapObjects.add(new MapObject("48, Kallakurichi - Kachirapalayam Rd, Raja Nagar, Kallakurichi, Tamil Nadu 606202"));
//        mapObjects.add(new MapObject("No - 162/8A, ANNAMALAI TOWERS, DURUGAM, MAIN ROAD, Kallakurichi, Tamil Nadu 606202"));
//        mapObjects.add(new MapObject("Durugam Road, Villupuram, near LIC Building, Kallakurichi, Tamil Nadu 606202"));
//        mapObjects.add(new MapObject("Kallakurichi, Viluppuram, SH-204, Salem Ulundurpettai Highway, Viluppuram, Viluppuram, 606202"));
//        mapObjects.add(new MapObject("nephal street"));
//        mapObjects.add(new MapObject("Opp. K.P.R. Petrol Bunk, Salem Main Road,, Kallakurichi, Tamil Nadu 606202"));
//        mapObjects.add(new MapObject("Kodikuruchi, Tenkasi - Madurai Rd, near Bus Stop, Tenkasi, Tamil Nadu 627811"));
//        mapObjects.add(new MapObject("Tenkasi, Tamil Nadu 627811"));
//        mapObjects.add(new MapObject("282, Samba Street, Tirunelveli Main Rd, Tenkasi, Tamil Nadu 627811"));
//        mapObjects.add(new MapObject("SH 40, tenkasi vaikalpaalam, Tenkasi, Tamil Nadu 627814"));
//        mapObjects.add(new MapObject("Tenkasi Ayikudi Rd, Tenkasi, Tamil Nadu 627852"));
//        mapObjects.add(new MapObject("Door-No-327, Melagaram, Courtallam Road Tirunelveli Palayamkottai Ambasamudram, Tenkasi, Tamil Nadu 627811"));
//        mapObjects.add(new MapObject("G.S.T. Road, Kancheepuram, Chengalpattu, Tamil Nadu 603001"));
//        mapObjects.add(new MapObject("No 70, Kanchipuram High Rd, J C K Nagar, Chengalpattu, Tamil Nadu 603002"));
//        mapObjects.add(new MapObject("Dr. V. Govindharaj, Plastic Surgeon No14Varadhanar Str Vedhachala Nagar, Near Head Post Office, Chengalpattu, Tamil Nadu 603001"));
//        mapObjects.add(new MapObject("No .12 , Varada Reddy Street, Vedachalam Nagar, Chengalpattu, Tamil Nadu 603001"));
//        mapObjects.add(new MapObject("Gokulapuram, Tamil Nadu 603001"));
//        mapObjects.add(new MapObject("Vanapadi Rd, South Thirumalai Nagar, Ranipet, Tamil Nadu 632403"));
//        mapObjects.add(new MapObject("Kellys Rd, Navalpur, Ranipet, Tamil Nadu 632401"));
//        mapObjects.add(new MapObject("44, Walajapet, Vellore SH-61, Walajah Sholinghur Arakonam Road, Arcot, 632513"));
//        mapObjects.add(new MapObject("Madras Bombay Trunk Rd, Thiruvalam, Tamil Nadu 632515"));
//        mapObjects.add(new MapObject("Bharathidasan St, Arcot, Tamil Nadu 632503"));
//        mapObjects.add(new MapObject("NH 46, Ranipet, Tamil Nadu 632401"));
//        mapObjects.add(new MapObject("GH Rd, Achamangalam, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("15, Dharmaraja Temple Street, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("351, Sai Baba Colony, Asiriyar Nagar, SH18, Achamangalam, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("Vaniyambadi Main Rd, Sakthi Nagar, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("Ashok Nagar, Teacher's Colony, Tirupattur, Tamil Nadu 635601"));
//        mapObjects.add(new MapObject("Tirupattur, Tamil Nadu 635601"));
//
//        for (int i = 0; i < mapObjects.size(); i++) {
//
//            new Geocoders().execute(mapObjects.get(i));
//
//        }
//    }
//
//}