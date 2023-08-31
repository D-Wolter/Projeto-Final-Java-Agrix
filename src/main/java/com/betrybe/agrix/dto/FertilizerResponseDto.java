package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * FertilizerResponseDto Record.
 */
public record FertilizerResponseDto(Long id, String name, String brand,
                                    String composition) {
  /**
   * Constructor.
   */
  public static FertilizerResponseDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerResponseDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition());
  }
}