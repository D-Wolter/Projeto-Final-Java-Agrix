package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.dto.RequestPersonDto;
import com.betrybe.agrix.dto.ResponsePersonDto;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * PersonController.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Creates a new Person.
   */
  @PostMapping()
  public ResponseEntity<RequestPersonDto> createPerson(@RequestBody PersonDto newPersonDto) {
    Person newPerson = newPersonDto.toPerson();
    Person createdPerson = personService.create(newPerson);
    RequestPersonDto createdPersonDto = new RequestPersonDto(createdPerson.getId(),
        createdPerson.getUsername(), createdPerson.getRole());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdPersonDto);
  }
}