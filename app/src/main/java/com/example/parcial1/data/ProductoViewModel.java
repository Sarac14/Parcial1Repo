package com.example.parcial1.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.parcial1.dto.Producto;

import java.util.List;

public class ProductoViewModel extends AndroidViewModel {
    private ProductoRepository mRepository;

    private MutableLiveData<List<Producto>> usersLiveData;

    public ProductoViewModel(Application application) {
        super(application);
        mRepository = new ProductoRepository(application);
        usersLiveData = mRepository.getUsersLiveData();
    }

    public MutableLiveData<List<Producto>> getUsersLiveData() {
        return usersLiveData;
    }
}
