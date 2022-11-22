package com.gogo.trainer.app.repository;

import com.gogo.trainer.app.repository.entitiy.DietCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietCommentRepository extends JpaRepository<DietCommentEntity, Long> {
}
