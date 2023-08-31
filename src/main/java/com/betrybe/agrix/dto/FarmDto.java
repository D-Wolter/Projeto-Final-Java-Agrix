package com.betrybe.agrix.dto;


import com.betrybe.agrix.models.entities.Farm;

/**
 * FarmDTO Record.
 */
public record FarmDto(Long id, String name, Float size) {
  public Farm toFarm() {
    return new Farm(id, name, size);
  }
}