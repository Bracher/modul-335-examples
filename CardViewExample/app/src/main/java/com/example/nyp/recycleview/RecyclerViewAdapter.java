package com.example.nyp.recycleview;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] values;
    Context context;

    //Transfer Numbers To Local Variables
    public RecyclerViewAdapter(Context context, String[] values){
        this.values = values;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        viewHolder.textView.setText(values[position]);
        viewHolder.textView.setBackgroundColor(Color.CYAN);
        viewHolder.textView.setTextColor(Color.BLUE);
    }

    @Override
    public int getItemCount(){
        return values.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public ViewHolder(View v){
            super(v);
            textView = (TextView) v.findViewById(R.id.textview1);
        }
    }
}