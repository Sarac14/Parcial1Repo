package com.example.parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.parcial1.api.APIClient;
import com.example.parcial1.api.APIInterface;
import com.example.parcial1.databinding.ActivityProductDetailViewBinding;
import com.example.parcial1.dto.Producto;
import com.example.parcial1.dto.ProductoSingle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailViewBinding binding;
    public static final String EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        int userId = getIntent().getIntExtra(EXTRA_PRODUCT_ID, -1);
        if (userId != -1) {
            fetchUserDetails(userId);
        }
    }

  private void fetchUserDetails(int userId) {
        APIInterface api = APIClient.getClient().create(APIInterface.class);
        api.find(userId).enqueue(new Callback<ProductoSingle>() {
            @Override
            public void onResponse(Call<ProductoSingle> call, Response<ProductoSingle> response) {
                if (response.isSuccessful()) {
                    Producto user = response.body().getData();
                    binding.id.setText(String.valueOf(user.id));
                    binding.titleDetail.setText(user.title);
                    binding.DescriptionDetail.setText(user.description);
                    binding.price.setText((int) user.price);
                    binding.discount.setText((int) user.discountPercentage);
                    binding.rating.setText((int) user.rating);
                    binding.brand.setText(user.brand);
                    binding.stock.setText(user.stock);
                    binding.category.setText(user.category);

                    Glide.with(ProductDetailActivity.this)
                            .load(user.thumbnail)
                            .into(binding.ivAvatar);
                }
            }

            @Override
            public void onFailure(Call<ProductoSingle> call, Throwable t) {
            }
        });
    }
}