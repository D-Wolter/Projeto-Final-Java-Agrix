package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;

/**
 * CropDTO Record.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,

    Long farmId
) {

  /**
   * CropDto.
   */
  public static CropDto cropEntityToDto(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );
  }


}