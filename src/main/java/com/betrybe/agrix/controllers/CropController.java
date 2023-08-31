package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerResponseDto;
import com.betrybe.agrix.exception.CropNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop Controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * GetAll Crops.
   */
  @Secured({"MANAGER", "ADMIN"})
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();

    List<CropDto> cropsSetDto = crops.stream()
        .map(CropDto::cropEntityToDto)
        .toList();

    return ResponseEntity.ok(cropsSetDto);
  }

  /**
   * GetById Crop.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Optional<Crop> newCrop = cropService.getCropById(id);

    if (newCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Crop crop = newCrop.get();
    CropDto cropResponseDto = CropDto.cropEntityToDto(crop);

    return ResponseEntity.ok(cropResponseDto);
  }

  /**
   * Crop Search.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> cropsByHarvestDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> allCrops = cropService.cropsByHarvestDate(start, end);
    List<CropDto> cropsDtoList = allCrops.stream()
        .map(CropDto::cropEntityToDto)
        .toList();
    return ResponseEntity.ok(cropsDtoList);
  }

  /**
   * Post /{cropId}/fertilizers/{fertilizerId}.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> assignFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId) {
    cropService.assignFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * Post /{cropId}/fertilizers/{fertilizerId}.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerResponseDto>> getAllFertilizers(
      @PathVariable Long cropId) {
    List<Fertilizer> fertilizers = cropService.getAllFertilizers(cropId);
    List<FertilizerResponseDto> fertilizerDto = fertilizers.stream()
        .map(FertilizerResponseDto::fromEntity).toList();
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerDto);
  }
}