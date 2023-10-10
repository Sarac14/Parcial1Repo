package com.example.parcial1.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.parcial1.api.APIClient;
import com.example.parcial1.api.APIInterface;
import com.example.parcial1.dto.Producto;
import com.example.parcial1.dto.ProductoList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoRepository {
    private MutableLiveData<List<Producto>> productoLiveData = new MutableLiveData<>();
    private final APIInterface api;

    ProductoRepository(Application application){
        api = APIClient.getClient().create(APIInterface.class);
        api.findAll().enqueue(new Callback<ProductoList>() {
            @Override
            public void onResponse(Call<ProductoList> call, Response<ProductoList> response) {
                Log.w("onResponse", "OK!");
                productoLiveData.setValue(response.body().products);
            }

            @Override
            public void onFailure(Call<ProductoList> call, Throwable t) {
                Log.w("onFailure", t.getMessage());
                call.cancel();
            }
        });
    }

    public MutableLiveData<List<Producto>> getUsersLiveData() {
        return productoLiveData;
    }


}
