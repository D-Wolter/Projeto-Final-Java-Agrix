package com.betrybe.agrix.evaluation;


import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.models.repository.PersonRepository;
import com.betrybe.agrix.services.PersonService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.eq;
@SpringBootTest
//@ActiveProfiles("test")
public class PersonServiceTest {
  @Autowired
  PersonService myPersonService;

  @MockBean
  PersonRepository myPersonRepository;

  @Test
  @DisplayName("Teste da função getPersonById (caso sucesso)")
  public void testGetPersonById() {
    Person myPerson = new Person();
    myPerson.setId(1L);
    myPerson.setUsername("Dwolter");
    myPerson.setPassword("tryber");

    Mockito.when(myPersonRepository.findById(eq(1L)))
        .thenReturn(Optional.of(myPerson));

    Person returnedMyPerson = myPersonService.getPersonById(1L);

    Mockito.verify(myPersonRepository).findById(eq(1L));

    assertEquals(returnedMyPerson.getId(), myPerson.getId());
    assertEquals(returnedMyPerson.getUsername(), myPerson.getUsername());
    assertEquals(returnedMyPerson.getPassword(), myPerson.getPassword());
  }

  @Test
  @DisplayName("Teste da função getPersonById (caso erro)")
  public void testGetPersonByIdException() {
    when(myPersonRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> {
      myPersonService.getPersonById(1L);
    });

    verify(myPersonRepository).findById(1L);
  }

  @Test
  @DisplayName("Teste da função getPersonByName (caso sucesso)")
  public void testGetPersonByUsername() {
    Person person = new Person();
    when(myPersonRepository.findByUsername(any(String.class)))
        .thenReturn(Optional.of(person));

    Person resultPerson = myPersonService.getPersonByUsername("Dwolter");

    verify(myPersonRepository).findByUsername("Dwolter");
    assertEquals(person, resultPerson);
  }

  @Test
  @DisplayName("Teste da função getPersonByName (caso erro)")
  public void testGetPersonByUsernameException() {
    when(myPersonRepository.findByUsername("Dwolter")).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> {
      myPersonService.getPersonByUsername("Dwolter");
    });

    verify(myPersonRepository).findByUsername("Dwolter");
  }
}