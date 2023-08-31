package com.betrybe.agrix.services;



import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.exception.FertilizerNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repository.CropRepository;
import com.betrybe.agrix.models.repository.FertilizerRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CropService.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * create Crop.
   */
  public Crop createCrop(Crop crop, Farm farm) {
    crop.setFarm(farm);
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Optional<Crop> getCropById(Long id) {
    return cropRepository.findById(id);
  }

  public List<Crop> cropsByHarvestDate(
      LocalDate start,
      LocalDate end
  ) {
    return cropRepository.findByHarvestDate(start, end);
  }

  /**
   * Crop With Fertilizer.
   */
  public void cropWithFertilizer(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * assignFertilizer.
   */
  @Transactional
  public void assignFertilizer(Long cropId, Long fertilizerId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }

  /**
   * getAllFertilizers.
   */
  @Transactional
  public List<Fertilizer> getAllFertilizers(Long cropId) {
    Crop crop = cropRepository.findById(cropId)
        .orElseThrow(CropNotFoundException::new);
    Hibernate.initialize(crop.getFertilizers());
    return crop.getFertilizers();
  }

}