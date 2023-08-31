package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fertilizer Controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Constructor.
   */
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Post Fertilizer.
   */
  @PostMapping()
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerService.create(fertilizer);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
   * GetFertilizerId.
   */
  @Secured("ADMIN")
  @GetMapping()
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.getAll();
    List<FertilizerDto> fertilizerDtoList = allFertilizers.stream()
        .map(FertilizerDto::fertilizerEntityToDto)
        .toList();

    return ResponseEntity.ok(fertilizerDtoList);
  }

  /**
   * GetFertilizerById.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long id) {
    Fertilizer fertilizer = fertilizerService.getById(id);
    FertilizerDto fertilizerDto = FertilizerDto.fertilizerEntityToDto(fertilizer);
    return ResponseEntity.ok(fertilizerDto);
  }

}