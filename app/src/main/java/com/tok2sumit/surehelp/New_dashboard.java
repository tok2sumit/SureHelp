package com.tok2sumit.surehelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedAdapter2;
import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedAdapter3;
import com.tok2sumit.surehelp.HelperClasses.HomeAdapter.FeaturedHelperClass;

import java.util.ArrayList;

public class New_dashboard extends AppCompatActivity {
//    Variables
    ImageView pill;
    RecyclerView featured_recycler,featured_recycler2,featured_recycler3;
    RecyclerView.Adapter adapter,adapter2,adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_dashboard);

//        Hooks
        pill = findViewById(R.id.img_pill);
        pill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Med_Remainder_Activity.class);
                startActivity(intent);
            }
        });

        featured_recycler = findViewById(R.id.featured_recycler);
        featured_recycler2 = findViewById(R.id.featured_recycler2);
        featured_recycler3 = findViewById(R.id.featured_recycler3);
        featuredRecycler();

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