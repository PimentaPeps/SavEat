package com.example.gusta.saveat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gusta.saveat.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 25/08/2017.
 */

public class ProductAdapter extends BaseAdapter {

    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        if (productList != null)
            this.productList = productList;
        else this.productList = new ArrayList<Product>();
    }

    public List<Product> getList() {
        return productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FrameLayout itemView = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = (FrameLayout) inflater.inflate(R.layout.item_produto, null);
        } else {
            itemView = (FrameLayout) convertView;
        }

        ((TextView) itemView.findViewById(R.id.item_produto_text_view)).setText(productList.get(position).getName());

        return itemView;
    }
}
