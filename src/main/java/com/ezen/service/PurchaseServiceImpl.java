package com.ezen.service;

import com.ezen.entity.Purchase;
import com.ezen.persistence.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public void insertPurchase(Purchase purchase) {

    }

    @Override
    public void updatePurchase(Purchase purchase) {

    }

    @Override
    public void deletePurchase(Purchase purchase) {

    }
}
