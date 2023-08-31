package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.security.Role;

/**
 * ResponsePersonDTO.
 */
public record ResponsePersonDto(Long id, String username, Role role) {

  /**
   * ResponsePersonDTO toDto.
   */
  public static ResponsePersonDto toDto(Person person) {
    return new ResponsePersonDto(person.getId(), person.getUsername(), person.getRole());
  }
}