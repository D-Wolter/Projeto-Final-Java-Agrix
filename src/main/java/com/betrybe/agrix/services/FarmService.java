package com.betrybe.agrix.services;


import com.betrybe.agrix.exception.FarmNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FarmService class.
 */
@Service
public class FarmService {

  FarmRepository myFarmRepository;


  @Autowired
  public FarmService(FarmRepository repository) {
    this.myFarmRepository = repository;
  }

  /**
   * Farm POST.
   */
  public Farm save(Farm myfarm) {
    Farm saved = this.myFarmRepository.save(myfarm);
    return saved;

  }

  public List<Farm> getAll() {
    List<Farm> getAllFarms = this.myFarmRepository.findAll();
    return getAllFarms;
  }

  public Optional<Farm> getFarmById(Long id) {
    return myFarmRepository.findById(id);
  }

  /**
   * GetByFarmId rota farms/{farmId}/crops.
   */
  public List<Crop> getCropsByFarmId(Long farmId) {
    Optional<Farm> optionalFarm = myFarmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = optionalFarm.get();

    return farm.getCrops();
  }
}