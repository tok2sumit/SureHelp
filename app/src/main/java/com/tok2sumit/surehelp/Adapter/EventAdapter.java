package com.tok2sumit.surehelp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.tok2sumit.surehelp.Database.DatabaseClass;
import com.tok2sumit.surehelp.Database.EntityClass;
import com.tok2sumit.surehelp.Database.EventDao;
import com.tok2sumit.surehelp.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EntityClass> entityClasses;
    DatabaseClass database;

    public EventAdapter(Context context, List<EntityClass> entityClasses) {
        this.context = context;
        this.entityClasses = entityClasses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listings1111_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.eventText.setText(entityClasses.get(position).getEventname());
        holder.timeAndDateText.setText(entityClasses.get(position).getEventdate() + " " + entityClasses.get(position).getEventtime());
//        delete
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseClass db = Room.databaseBuilder(holder.eventText.getContext(),
                                DatabaseClass.class,
                                "product_database").allowMainThreadQueries().build();
                EventDao eventDao = db.EventDao();
                eventDao.delete(entityClasses.get(position).getEventname());
                entityClasses.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return entityClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        private TextView eventText, timeAndDateText;
        private LinearLayout toplayout;

//        delete Button
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.event);
            timeAndDateText = (TextView) itemView.findViewById(R.id.time_and_date);
            toplayout = (LinearLayout) itemView.findViewById(R.id.toplayout);
            delete = itemView.findViewById(R.id.btn_cancel_alarm);
        }
    }
}
