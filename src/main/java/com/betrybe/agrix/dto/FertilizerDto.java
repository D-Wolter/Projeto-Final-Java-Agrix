package com.betrybe.agrix.dto;


import com.betrybe.agrix.models.entities.Fertilizer;

/**
 * FertilizerDTO Record.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Constructor.
   */
  public static FertilizerDto fertilizerEntityToDto(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(), fertilizer.getName(),
        fertilizer.getBrand(), fertilizer.getComposition()
    );
  }
}