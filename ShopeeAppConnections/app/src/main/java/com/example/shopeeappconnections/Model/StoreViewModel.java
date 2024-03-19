package com.example.shopeeappconnections.Model;

import androidx.lifecycle.ViewModel;

public class StoreViewModel extends ViewModel {
    private String storeId;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}