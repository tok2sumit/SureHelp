package com.tok2sumit.surehelp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tok2sumit.surehelp.R;

import java.util.ArrayList;

public class FeaturedAdapter3 extends RecyclerView.Adapter<FeaturedAdapter3.FeaturedviewHolder> {
    ArrayList<FeaturedHelperClass> FeaturedLocations;
    //    It will hold the data
    public FeaturedAdapter3(ArrayList<FeaturedHelperClass> featuredLocations) {
        FeaturedLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedAdapter3.FeaturedviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design3,parent,false);
        FeaturedAdapter3.FeaturedviewHolder featuredviewHolder = new FeaturedAdapter3.FeaturedviewHolder(view);
        return featuredviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedviewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = FeaturedLocations.get(position);

        holder.title.setText(FeaturedHelperClass.getTitle());
        holder.desc.setText(FeaturedHelperClass.getDescription());
        holder.image.setImageResource(FeaturedHelperClass.getImage());
    }


    @Override
    public int getItemCount() {
        return FeaturedLocations.size();
    }

    //    It will hold the view
    public static class FeaturedviewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title,desc;

        public FeaturedviewHolder(@NonNull View itemView) {
            super(itemView);

//        Hooks
            image = itemView.findViewById(R.id.card3_image);
            title = itemView.findViewById(R.id.card3_title);
            desc = itemView.findViewById(R.id.card3_desc);
        }
    }
}
