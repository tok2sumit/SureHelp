package com.example.govermentfacility;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openPension(View v){

        Intent intent= new Intent(this, Pension.class);
        startActivity(intent);

    }
    public void openRVY(View v){

        Intent intent= new Intent(this, RVY.class);
        startActivity(intent);

    }

    public void openPMVVY(View v){

        Intent intent= new Intent(this, PMVVY.class);
        startActivity(intent);

    }
    public void openAnnapurna(View v){

        Intent intent= new Intent(this, annapurna.class);
        startActivity(intent);

    }

    public void openconcession(View v){
        Intent intent= new Intent(this, concession.class);
        startActivity(intent);
    }

    public void openPMJAY(View v){
        Intent intent = new Intent(this,PMJAY.class);
        startActivity(intent);
    }

}


