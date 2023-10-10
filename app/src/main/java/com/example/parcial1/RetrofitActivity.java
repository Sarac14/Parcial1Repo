package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.parcial1.adapters.RetrofitAdapter;
import com.example.parcial1.api.APIClient;
import com.example.parcial1.api.APIInterface;
import com.example.parcial1.data.ProductoViewModel;
import com.example.parcial1.databinding.ActivityRetrofitBinding;
import com.example.parcial1.dto.Producto;
import com.example.parcial1.dto.ProductoSingle;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    ActivityRetrofitBinding binding;

    private ProductoViewModel productoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRetrofitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int spanCount = 1;

        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            spanCount = 2;
        }

        RecyclerView recyclerView = binding.recycler;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), spanCount));
        RetrofitAdapter adapter = new RetrofitAdapter(new RetrofitAdapter.Diff(),this);
        recyclerView.setAdapter(adapter);

        productoViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        productoViewModel.getUsersLiveData().observe(this, productos -> {
            adapter.submitList(productos);
        });



        APIInterface api = APIClient.getClient().create(APIInterface.class);

        api.find(2).enqueue(new Callback<ProductoSingle>() {
            @Override
            public void onResponse(Call<ProductoSingle> call, Response<ProductoSingle> response) {
                Log.w("onResponse", response.body().toString());
            }

            @Override
            public void onFailure(Call<ProductoSingle> call, Throwable t) {
               Log.w("onFailure", t.getLocalizedMessage());
                call.cancel();
            }
        });
    }
}