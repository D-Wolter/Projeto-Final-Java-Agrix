package com.betrybe.agrix.dto;

import com.betrybe.agrix.security.Role;

/**
 * RequestPersonDto.
 */
public record RequestPersonDto(Long id, String username, Role role) {

}
