package com.zecho.countmatic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> keyList;


    SaveAdapter(Context context) {

        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_for_saves, parent, false);

        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SharedPreferences sp = context.getSharedPreferences(MainActivity2.PREFERENCE_KEY, 0);
        SharedPreferences.Editor editor = sp.edit();

        holder.tvSerial.setText(String.valueOf(position + 1));

        holder.tvKey.setText(keyList.get(position));

        holder.tvValue.setText(String.valueOf(sp.getInt(keyList.get(position), 0)));

        holder.btnRemove.setOnClickListener(v -> {
            editor.remove(keyList.get(position));
            editor.apply();
            keyList.remove(position);
            notifyDataSetChanged();
        });

    }


    @Override
    public int getItemCount() {
        return keyList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSerial, tvKey, tvValue;
        Button btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSerial = itemView.findViewById(R.id.tv_serial);

            tvKey = itemView.findViewById(R.id.tv_key);

            tvValue = itemView.findViewById(R.id.tv_value);

            btnRemove = itemView.findViewById(R.id.btn_self_delete);
        }
    }
    
    public void setItems(ArrayList<String> list){
        this.keyList = list;
    }
}
