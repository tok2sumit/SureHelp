package com.tok2sumit.surehelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FusedLocationProviderClient fusedLocationProviderClient;
    String enumber1,enumber2,enumber3;
    String locationlink;

    private SQLiteDatabase db;

    // Variables for Google Sign-In & Sign-Out
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name;
    Button signOutBtn;
    CardView medicine_rem,yoga_workout,send_your_location,nearby_hospital,learn_tech,Government_fal;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        medicine_rem = findViewById(R.id.card_medicine_remainder);
        yoga_workout = findViewById(R.id.card_yoga_workout);
        send_your_location = findViewById(R.id.card_send_location);
        nearby_hospital = findViewById(R.id.card_nearby_hospital);
        learn_tech = findViewById(R.id.card_learn_tech);
        Government_fal = findViewById(R.id.card_government_facl);

        // Code for Google Sign-In
        name = findViewById(R.id.txt_name);
        signOutBtn = findViewById(R.id.btn_logout);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        if (getIntent().getStringExtra("fullname")!=null){
            name = findViewById(R.id.txt_name);
            String fullname = getIntent().getStringExtra("fullname");
            name.setText(fullname);
        }

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct!=null){
            String personName = acct.getDisplayName();
            name.setText(personName);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

//        Tool Bar
        setSupportActionBar(toolbar);

//        Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

//        for making navigation drawer item clikable
        navigationView.setNavigationItemSelectedListener(this);

        medicine_rem.setOnClickListener(new View.OnClickListener() {
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


        nearby_hospital.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent nearby_l = new Intent(DashboardActivity.this,Nearby_Hospital.class);
            startActivity(nearby_l);
        }
    });

        // for sending location
        db = openOrCreateDatabase("ContactDB", Context.MODE_PRIVATE, null);
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS contact (contact1 VARCHAR, contact2 VARCHAR, contact3 VARCHAR);"
        );



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        send_your_location.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



            String count = "SELECT count(*) FROM contact";
            Cursor mcursor = db.rawQuery(count, null);
            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            if(icount==0){
                Toast.makeText(DashboardActivity.this, "Enter Emergency contacts", Toast.LENGTH_SHORT).show();
                Intent eintent= new Intent(DashboardActivity.this, Emergency_contact.class);
                startActivity(eintent);
            }
            mcursor.close();

            Cursor c = db.rawQuery("SELECT * FROM contact", null);

            c.moveToNext();
            enumber1= c.getString(0);
            enumber2= c.getString(1);
            enumber3= c.getString(2);


            c.close();

            //check permission
            if (ActivityCompat.checkSelfPermission(DashboardActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //if permission granted
                getLocation();

            } else {
                //if permission denied
                ActivityCompat.requestPermissions(DashboardActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }
        }
    });




        // Calling Goverment Facility feature via OnClick
        Government_fal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, govfacility_main.class);
                startActivity(intent);
            }
        });

        // Calling Learn Technology feature via OnClick
        learn_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Learn_Technology.class);
                startActivity(intent);
            }
        });

    }






    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.profile:

                    String full_name = getIntent().getStringExtra("fullname");
                    String user_name = getIntent().getStringExtra("username");
                    String email_id = getIntent().getStringExtra("email");

                Toast.makeText(DashboardActivity.this, full_name+" "+user_name+" "+email_id, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(DashboardActivity.this, Profile.class);
                    intent.putExtra("fullname", full_name);
                    intent.putExtra("username", user_name);
                    intent.putExtra("email", email_id);

                    startActivity(intent);
                    break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location != null) {
                    //initialize GeoCoder

                    Geocoder geocoder = new Geocoder(DashboardActivity.this, Locale.getDefault());

                    //Initialize address List
                    try {
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //set long lat on textview
                        locationlink = "https://www.google.com/maps/search/?api=1&query=" + addresses.get(0).getLatitude() + "," + addresses.get(0).getLongitude();
//                        showLat.setText(Html.fromHtml(locationlink));


                        //checking sms permission
                        if (ContextCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                            //when permission is granted
                            SendMessage(locationlink);

                        } else {
                            ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{Manifest.permission.SEND_SMS}, 100);
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
        smsManager.sendTextMessage(enumber2,null,locationlink,null,null);
        smsManager.sendTextMessage(enumber3,null,locationlink,null,null);
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


    // Method for Google Account Sign-In
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                Intent intent = new Intent(DashboardActivity.this,Login_Activity.class);
                intent.putExtra("finish", true);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
                startActivity(intent);
                finish();

            }
        });
    }
}

