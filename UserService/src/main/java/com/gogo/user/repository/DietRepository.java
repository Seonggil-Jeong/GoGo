package com.gogo.user.repository;

import com.gogo.user.repository.entity.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DietRepository extends JpaRepository<DietEntity, UUID> {
}
