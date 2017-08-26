package com.example.gusta.saveat;

import android.app.Activity;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gusta.saveat.API.APIClient;
import com.example.gusta.saveat.API.APIInterface;
import com.example.gusta.saveat.model.Product;
import com.example.gusta.saveat.model.Supermarket;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by gusta on 25/08/2017.
 */

public class ServerCall {
    private APIInterface apiService;
    private final Activity activity;

    public ServerCall(Activity activity) {
        apiService = APIClient.getClient().create(APIInterface.class);
        this.activity = activity;
    }

    public void getProductDetails() {
        Map<String, String> queryParams = new HashMap<>();
        //queryParams.put("key", "value");
        ProgressDialogLoader.progressdialog_creation(activity, "Loading");

        Call<Product[]> call = apiService.getProduct(queryParams);
        call.enqueue(new Callback<Product[]>() {
            @Override
            public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                if (response.body() != null)
                    if (response.body()[0].getName() != null) {
                        Log.d(TAG, "User ID: " + response.body()[0].getId());
                    } else {
                        Toast.makeText(activity.getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "User details does not exist");
                    }

                ProgressDialogLoader.progressdialog_dismiss();
            }

            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                Log.e(TAG, t.toString());
                ProgressDialogLoader.progressdialog_dismiss();
            }
        });

    }

    public void getProductDetails(String id, final BaseAdapter adapter) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("", id);
        ProgressDialogLoader.progressdialog_creation(activity, "Loading");

        Call<Product> call = apiService.getProduct(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.body() != null)
                    if (response.body().getName() != null) {
                        Log.d(TAG, "User ID: " + response.body());
                    } else {
                        Toast.makeText(activity.getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "User details does not exist");
                    }
                ((ProductAdapter) adapter).getList().add(response.body());
                adapter.notifyDataSetChanged();
                ProgressDialogLoader.progressdialog_dismiss();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.toString());
                ProgressDialogLoader.progressdialog_dismiss();
            }
        });

    }
    public void getMarketDetails(List<Product> productList, final BaseAdapter adapter) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("item", new Gson().toJson(productList));
        ProgressDialogLoader.progressdialog_creation(activity, "Loading");

        Call<Supermarket> call = apiService.getProductList(queryParams);
        call.enqueue(new Callback<Supermarket>() {
            @Override
            public void onResponse(Call<Supermarket> call, Response<Supermarket> response) {
                if (response.body() != null)
                    if (response.body().getName() != null) {
                        Log.d(TAG, "User ID: " + response.body());
                    } else {
                        Toast.makeText(activity.getApplicationContext(), "User does not exist", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "User details does not exist");
                    }
                //((MarketAdapter) adapter).getList().add(response.body());
                adapter.notifyDataSetChanged();
                ProgressDialogLoader.progressdialog_dismiss();
            }

            @Override
            public void onFailure(Call<Supermarket> call, Throwable t) {
                Log.e(TAG, t.toString());
                ProgressDialogLoader.progressdialog_dismiss();
            }
        });

    }

}
