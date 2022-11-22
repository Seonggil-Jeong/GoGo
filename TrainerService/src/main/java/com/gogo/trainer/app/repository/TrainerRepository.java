package com.gogo.trainer.app.repository;

import com.gogo.trainer.app.repository.entitiy.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, String> {
}
