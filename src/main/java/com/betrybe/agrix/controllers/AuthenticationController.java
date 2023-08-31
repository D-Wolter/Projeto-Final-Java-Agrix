package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.AuthDto;
import com.betrybe.agrix.dto.TokenDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login ENDPOINT.
   */
  @PostMapping("/login")
  public ResponseEntity<TokenDto> login(
      @RequestBody AuthDto authDto) {

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(authDto.username(),
            authDto.password());
    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();

    String token = tokenService.generateToken(person);
    TokenDto tokenDto = new TokenDto(token);

    return ResponseEntity.status(HttpStatus.OK).body(tokenDto);
  }
}