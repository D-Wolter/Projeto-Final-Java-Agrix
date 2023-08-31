package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * FarmController class.
 */
@RestController
@RequestMapping(value = "/farms")
public class FarmController {
  @Autowired
  FarmService farmService;


  @Autowired
  public FarmController(FarmService service) {
    this.farmService = service;
  }


  @Autowired
  private CropService cropService;

  /**
   * Farm rota POST.
   */
  @PostMapping()
  public ResponseEntity<Farm> createMyFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmDto.toFarm();

    Farm create = this.farmService.save(newFarm);

    return ResponseEntity.status(HttpStatus.CREATED).body(create);
  }

  /**
   * Farm rota GET.
   */
  @Secured({"USER", "MANAGER", "ADMIN"})
  @GetMapping()
  public List<FarmDto> getAll() {
    List<Farm> allFarms = farmService.getAll();
    return allFarms.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * Farm rota GetById.
   */
  @GetMapping("/{id}")
  public ResponseEntity<?> findFarmById(@PathVariable Long id) {
    Optional<Farm> farm = farmService.getFarmById(id);

    if (farm.isPresent()) {
      return ResponseEntity.ok().body(farm);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }


  /**
   * Farm POSt rota {farmId}/crops.
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> createCrop(
      @PathVariable Long farmId,
      @RequestBody Crop crop) {
    Optional<Farm> farm = farmService.getFarmById(farmId);

    if (farm.isPresent()) {
      Crop createdCrop = cropService.createCrop(crop, farm.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(CropDto.cropEntityToDto(createdCrop));
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }

  /**
   * Farm GetByFarmId rota farms/{farmId}/crops.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getCropsByFarmId(farmId);

    List<CropDto> cropsSetDto = crops.stream()
        .map(CropDto::cropEntityToDto)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(cropsSetDto);
  }

}