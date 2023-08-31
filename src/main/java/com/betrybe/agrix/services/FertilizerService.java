package com.betrybe.agrix.services;


import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FertilizerService.
 */
@Service
public class FertilizerService {

  @Autowired
  private final FertilizerRepository fertilizerRepository;

  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * create.
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * getAll.
   */
  public List<Fertilizer> getAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * getById.
   */
  public Fertilizer getById(Long id) {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    return optionalFertilizer.get();
  }
}