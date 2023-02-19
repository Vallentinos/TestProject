package com.ezen.persistence;

import com.ezen.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Page<Purchase> findAllByMember_Username(String username, Pageable pageable);
}
