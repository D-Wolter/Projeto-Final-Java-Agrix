package com.betrybe.agrix.models.repository;


import com.betrybe.agrix.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FarmRepository Interface.
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}