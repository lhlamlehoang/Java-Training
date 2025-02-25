package com.training.javatrainingphase2.repository;

import com.training.javatrainingphase2.model.MenuItems;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuItems, Long> {
    // Not include the soft deleted item
    @Query("SELECT m FROM MenuItems m WHERE m.status <> 0")
    Page<MenuItems> getAllItems(Pageable pageable);

    // Soft delete item by update status = 0
    @Modifying
    @Transactional
    @Query("UPDATE MenuItems m SET m.status = 0 WHERE m.id = ?1")
    void softDeleteItemById(Long id);


    // find item by keyword (except status 0)
    @Query("SELECT m FROM MenuItems m " +
                "Where m.status <> 0 AND (Lower(m.name) Like Concat('%', ?1, '%') " +
                                            "OR Lower(m.description) Like Concat('%', ?1, '%'))")
    Page<MenuItems> findItem(String keyword, Pageable pageable);
}
