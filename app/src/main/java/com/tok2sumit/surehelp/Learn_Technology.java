package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Learn_Technology extends AppCompatActivity {

    ImageView img_openWhatsapp,img_openGpay,img_openUber,img_openLoc,img_openYt,img_openFood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_technology);

        img_openWhatsapp = findViewById(R.id.img_openWhatsapp);
        img_openGpay = findViewById(R.id.img_openGpay);
        img_openUber = findViewById(R.id.img_openUber);
        img_openLoc = findViewById(R.id.img_openLoc);
        img_openYt = findViewById(R.id.img_openYt);
        img_openFood = findViewById(R.id.img_openFood);


        img_openWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), whatsapp.class);
                startActivity(intent);
            }
        });

        img_openGpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), gpay.class);
                startActivity(intent);
            }
        });

        img_openUber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), uber.class);
                startActivity(intent);
            }
        });

        img_openLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), loc.class);
                startActivity(intent);
            }
        });

        img_openYt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), yt.class);
                startActivity(intent);
            }
        });

        img_openFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), food.class);
                startActivity(intent);
            }
        });
    }
}