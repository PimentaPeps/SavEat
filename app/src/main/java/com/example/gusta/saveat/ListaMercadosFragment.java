package com.example.gusta.saveat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.gusta.saveat.model.Product;
import com.example.gusta.saveat.model.Supermarket;
import com.example.gusta.saveat.model.Supermarket2;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gusta on 23/08/2017.
 */

public class ListaMercadosFragment extends Fragment {
    List<Product> productList;
    MarketAdapter marketAdapter;

    public ListaMercadosFragment(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        InputStream raw = getResources().openRawResource(R.raw.mock);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try
        {
            i = raw.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = raw.read();
            }
            raw.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block

            e.printStackTrace();
        }

        List<Supermarket2> supermarketList = new ArrayList<>();
        supermarketList.add(new Gson().fromJson(byteArrayOutputStream.toString(), Supermarket2.class));
        marketAdapter = new MarketAdapter(getContext(), supermarketList);
//        ServerCall serverCall = new ServerCall(getActivity());
//        serverCall.getMarketDetails(productList, marketAdapter);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_mercados, container, false);
        ((ListView)view.findViewById(R.id.mercados_list_view)).setAdapter(marketAdapter);
        return view;
    }
}
