package com.training.javatrainingphase2.repository;

import com.training.javatrainingphase2.model.BillItems;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillItemsRepository extends JpaRepository<BillItems, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE BillItems b SET b.quantity = ?2 WHERE b.id = ?1")
    void modifyQuantity(Long billItemId, int quantity);
}
