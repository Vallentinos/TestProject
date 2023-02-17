package com.ezen.service;

import com.ezen.entity.Purchase;
import com.ezen.persistence.PurchaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional // 메서드가 포함하고 있는 작업 중에 하나라도 실패할 경우 전체 작업을 취소한다.
    @Override
    public void insertPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public void updatePurchase(Purchase purchase) {

    }

    @Override
    public void deletePurchase(Purchase purchase) {

    }

    @Override
    public Page<Purchase> findByMember_Username(int page, String username) {

        Pageable pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "purchaseSeq");
        return purchaseRepository.findAllByMember_Username(username, pageable);
    }
}
