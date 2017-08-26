package com.example.gusta.saveat;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gusta.saveat.model.Supermarket;
import com.example.gusta.saveat.model.Supermarket2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 25/08/2017.
 */

public class MarketAdapter extends BaseAdapter {

    private List<Supermarket2> supermarketList;
    private Context context;

    public MarketAdapter(Context context, List<Supermarket2> supermarketList) {
        this.context = context;
        if (supermarketList != null)
            this.supermarketList = supermarketList;
        else this.supermarketList = new ArrayList<Supermarket2>();
    }

    public List<Supermarket2> getList() {
        return supermarketList;
    }

    @Override
    public int getCount() {
        return supermarketList.size();
    }

    @Override
    public Object getItem(int position) {
        return supermarketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout itemView = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = (LinearLayout) inflater.inflate(R.layout.item_mercado, null);
        } else {
            itemView = (LinearLayout) convertView;
        }

        ((TextView) itemView.findViewById(R.id.item_mercado_text_view)).setText(supermarketList.get(position).getName() + " - " + "R$" + supermarketList.get(position).getTotalPrice());
        ((ImageView) itemView.findViewById(R.id.item_mercado_image_view)).setImageResource(R.mipmap.paguemenos);
        itemView.findViewById(R.id.item_mercado_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MapFragment mapFragment = new MapFragment(supermarketList.get(position));
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.middle_frame, mapFragment, "3").commit();
            }
        });

        return itemView;
    }
}
