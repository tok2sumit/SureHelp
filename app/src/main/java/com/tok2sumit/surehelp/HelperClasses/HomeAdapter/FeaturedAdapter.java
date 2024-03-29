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

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedviewHolder>{

    ArrayList<FeaturedHelperClass> FeaturedLocations;
//    It will hold the data
    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        FeaturedLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);
        FeaturedviewHolder featuredviewHolder = new FeaturedviewHolder(view);
        return featuredviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedviewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = FeaturedLocations.get(position);

        holder.image.setImageResource(FeaturedHelperClass.getImage());
        holder.title.setText(FeaturedHelperClass.getTitle());
        holder.desc.setText(FeaturedHelperClass.getDescription());
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
        image = itemView.findViewById(R.id.featured_image);
        title = itemView.findViewById(R.id.featured_title);
        desc = itemView.findViewById(R.id.featured_desc);

    }
}
}
