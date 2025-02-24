package com.training.JavaTrainingPhase2.repository;

import com.training.JavaTrainingPhase2.model.MenuItems;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<MenuItems, Long> {

}
