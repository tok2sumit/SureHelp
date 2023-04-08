package com.tok2sumit.surehelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class govfacility_main extends AppCompatActivity {

    CardView pension_card,openRVY,openPMVVY,openAnnapurna,openPMJAY,openconcession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govfacility);

        pension_card = findViewById(R.id.pension_card);
        openRVY = findViewById(R.id.openRVY);
        openPMVVY = findViewById(R.id.openPMVVY);
        openAnnapurna = findViewById(R.id.openAnnapurna);
        openPMJAY = findViewById(R.id.openPMJAY);
        openconcession = findViewById(R.id.openconcession);

        pension_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), Pension.class);
                startActivity(intent);
            }
        });

        openRVY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), RVY.class);
                startActivity(intent);
            }
        });

        openPMVVY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), PMVVY.class);
                startActivity(intent);
            }
        });

        openAnnapurna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), annapurna.class);
                startActivity(intent);
            }
        });

        openconcession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), concession.class);
                startActivity(intent);
            }
        });

        openPMJAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), PMJAY.class);
                startActivity(intent);
            }
        });
    }


}


