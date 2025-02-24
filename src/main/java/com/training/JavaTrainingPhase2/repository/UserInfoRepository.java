package com.training.JavaTrainingPhase2.repository;

import com.training.JavaTrainingPhase2.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> getByEmail(String email);
}
