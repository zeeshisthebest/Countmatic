package com.zecho.countmatic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {
    private Context context;
    private HashMap<String, Integer> map;
    private ArrayList<String> keyList;


    SaveAdapter(Context context, HashMap<String, Integer> map) {
        this.map = map;

        this.keyList = new ArrayList<>(map.keySet());

        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_for_saves, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvSerial.setText(String.valueOf(position));

        holder.tvKey.setText(keyList.get(position));

        holder.tvValue.setText(String.valueOf(map.get(keyList.get(position))));

    }


    @Override
    public int getItemCount() {
        return keyList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSerial, tvKey, tvValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSerial = itemView.findViewById(R.id.tv_serial);

            tvKey = itemView.findViewById(R.id.tv_key);

            tvValue = itemView.findViewById(R.id.tv_value);
        }
    }
}
