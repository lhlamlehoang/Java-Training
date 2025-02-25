package com.training.javatrainingphase2.repository;

import com.training.javatrainingphase2.model.Bill;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    // Retrieve all bills except status = 0
    @Query("SELECT b1 FROM Bill b1 LEFT JOIN b1.billItems b2 WHERE b1.status <> 0")
    Page<Bill> getAllBills(Pageable pageable);

    // Soft delete bill by updating status = 0
    @Modifying
    @Transactional
    @Query("UPDATE Bill b SET b.status = 0 WHERE b.id = ?1")
    void softDeleteBillById(Long id);
}
