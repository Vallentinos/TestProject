package com.ezen.service;

import com.ezen.entity.Member;
import com.ezen.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseService {

    void insertPurchase(Purchase purchase); // 구매

    void updatePurchase(Purchase purchase);

    void deletePurchase(Purchase purchase);

    Purchase getPurchase(Purchase purchase);

    Page<Purchase> findByMember_Username(int page, String username);

    Page<Purchase> getPurchaseList(int page);

}
