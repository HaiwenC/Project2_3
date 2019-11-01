package csci310.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import model.Request;
import model.Tutee;
import model.Tutor;


public class TutorHome extends AppCompatActivity {
    private Button profile;
    private RecyclerView list;
    //private MyRecyclerViewAdapter adapter;
    public static List<Request> groups = new ArrayList<Request>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = findViewById(R.id.listNotes);
        //Log.d("debug listview", list.toString());
        groups.add(new Request(new Tutee("1", "1", "1","1"),
                new Tutor("1", "1", "!", "1"), "csci109", 1 ,8, 9));
        setContentView(R.layout.tutorpage);
        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TutorProfile.class);
                startActivity(i);
            }
        });
        //adapter = new MyRecyclerViewAdapter(getApplicationContext(), groups);
        //Log.d("debug adapter", adapter.toString());
        //list.setAdapter(adapter);
    }
    /*private class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
        private List<Request> mData;
        private LayoutInflater mInflater;

        // data is passed into the constructor
        MyRecyclerViewAdapter(Context context, List<Request> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }
        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.requestlayout, parent, false);
            return new ViewHolder(view);
        }
        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            String animal = mData.get(position);
            holder.myTextView.setText(animal);
        }
        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }
        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById(R.id.tvAnimalName);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }*/
}
