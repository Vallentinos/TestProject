package com.ezen.service;

import com.ezen.entity.Purchase;

public interface PurchaseService {

    void insertPurchase(Purchase purchase); // 구매
    void updatePurchase(Purchase purchase);
    void deletePurchase(Purchase purchase);

}
