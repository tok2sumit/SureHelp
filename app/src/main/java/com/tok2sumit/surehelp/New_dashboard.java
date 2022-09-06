package com.tok2sumit.surehelp;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.Image;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedAdapter2;
import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedAdapter3;
import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedHelperClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// For sending location
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class New_dashboard extends AppCompatActivity {
    //    Variables
    ImageView pill;
    ImageView yoga_workout;
    RecyclerView featured_recycler, featured_recycler2, featured_recycler3;
    RecyclerView.Adapter adapter, adapter2, adapter3;

    //    For Sending location
    ImageView btnGetLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    String enumber1;
    String locationlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_dashboard);

//        Hooks
        yoga_workout = findViewById(R.id.img_yoga_workout);
        pill = findViewById(R.id.img_pill);
        pill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Med_Remainder_Activity.class);
                startActivity(intent);
            }
        });

        yoga_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Yoga_Workout.class);
                startActivity(intent);
            }
        });

        featured_recycler = findViewById(R.id.featured_recycler);
        featured_recycler2 = findViewById(R.id.featured_recycler2);
        featured_recycler3 = findViewById(R.id.featured_recycler3);
        featuredRecycler();

//        for sending location
        btnGetLocation = (ImageView) findViewById(R.id.btnGetLocation);
        enumber1 = "8104005081";
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission
                if (ActivityCompat.checkSelfPermission(New_dashboard.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted
                    getLocation();

                } else {
                    //if permission denied
                    ActivityCompat.requestPermissions(New_dashboard.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {
                    //initialize GeoCoder

                    Geocoder geocoder = new Geocoder(New_dashboard.this, Locale.getDefault());

                    //Initialize address List
                    try {
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //set long lat on textview
                        locationlink = "https://www.google.com/maps/search/?api=1&query=" + addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
//                        showLat.setText(Html.fromHtml(locationlink));


                        //checking sms permission
                        if (ContextCompat.checkSelfPermission(New_dashboard.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            //when permission is granted
                            SendMessage(locationlink);

                        } else {
                            ActivityCompat.requestPermissions(New_dashboard.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void SendMessage(String locationlink){
        //Initialize sms Manager
        SmsManager smsManager = SmsManager.getDefault();

        //Send text Message
        smsManager.sendTextMessage(enumber1,null,locationlink,null,null);

        //Displaying Toast
        Toast.makeText(getApplicationContext(),"Location sent Successfully via SMS",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //when permission is granted
            //Send Message call method
            SendMessage(locationlink);
        }else{
            //when permission denied , display toast
            Toast.makeText(getApplicationContext(),"Permission Denied!", Toast.LENGTH_LONG).show();
        }

    }


    private void featuredRecycler(){
        featured_recycler.setHasFixedSize(true);
        featured_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        featured_recycler2.setHasFixedSize(true);
        featured_recycler2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        featured_recycler3.setHasFixedSize(true);
        featured_recycler3.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.location,"Location","adfa fasdf aasdfas dfafd adfa ad fad fadf adf adf a"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.yoga,"Pill","gfsdfg fasdf aasdfas dfafd adfa ad fad fadf adf adf a"));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.pill,"yoga","sfgsfdg fasdf aasdfas dfafd adfa ad fad fadf adf adf a"));


        adapter = new FeaturedAdapter(featuredLocations);
        featured_recycler.setAdapter(adapter);

        adapter2 = new FeaturedAdapter2(featuredLocations);
        featured_recycler2.setAdapter(adapter2);

        adapter3 = new FeaturedAdapter3(featuredLocations);
        featured_recycler3.setAdapter(adapter3);

//        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});


    }

}