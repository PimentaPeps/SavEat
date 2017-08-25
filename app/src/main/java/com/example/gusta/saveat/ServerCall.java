package com.example.gusta.saveat;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.gusta.saveat.API.APIClient;
import com.example.gusta.saveat.API.APIInterface;
import com.example.gusta.saveat.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
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
    public void getProductDetails(String id) {
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

                ProgressDialogLoader.progressdialog_dismiss();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, t.toString());
                ProgressDialogLoader.progressdialog_dismiss();
            }
        });

    }

}
