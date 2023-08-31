package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * CropRepository.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  @Query("SELECT crop FROM Crop crop WHERE crop.harvestDate BETWEEN :start AND :end")
  List<Crop> findByHarvestDate(@Param("start") LocalDate start, @Param("end") LocalDate end);
}