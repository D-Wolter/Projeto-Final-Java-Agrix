package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Fertilizer Repository.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}