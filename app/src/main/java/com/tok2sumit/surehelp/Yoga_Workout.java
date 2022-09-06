package com.tok2sumit.surehelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import androidx.appcompat.app.AppCompatActivity;
import com.tok2sumit.surehelp.databinding.ActivityYogaWorkoutBinding;

import java.util.ArrayList;

public class Yoga_Workout extends AppCompatActivity {
    ActivityYogaWorkoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityYogaWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        int[] imageId= {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h};
        String[] name={"Deep Breathing","Neck Stretch","Shoulder Circles","Full Body Roll Up","Neck Extension","Chair Stand Strengthening","Shoulder and Upper Back Stretch","Side Bends"};
        String[] lastMessage={"3 reps","1 rep","2 reps","1 rep","2 reps","1 rep","1 rep","2 reps"};
        String[] lastMsgTime={"","","","","","","",""};
        String[] phoneNo={" "," "," "," "," "," "," "," "};
        String[] county= new String[]{" ", " ", " ", " ", " ", " ", " ", " "};


        ArrayList<User> userArrayList = new ArrayList<>();
        for(int i = 0;i<imageId.length;i++){
            User user = new User(name[i],lastMessage[i],lastMsgTime[i],phoneNo[i],county[i],imageId[i]);
            userArrayList.add(user);
        }
        ListAdapter listAdapter= new com.tok2sumit.surehelp.Adapter.ListAdapter(Yoga_Workout.this,userArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Yoga_Workout.this,user_activity.class);
                i.putExtra("name",name[position]);
                i.putExtra("phone",phoneNo[position]);
                i.putExtra("country",county[position]);
                i.putExtra("imageId",imageId[position]);
                startActivity(i);
            }
        });
    }
}

