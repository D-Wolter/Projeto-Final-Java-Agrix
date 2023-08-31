package com.betrybe.agrix.services;

import com.betrybe.agrix.exception.PersonNotFoundException;
import com.betrybe.agrix.models.entities.Person;
import com.betrybe.agrix.models.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Person Service.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Get By Person ID.
   */
  public Person getPersonById(Long id) {
    Optional<Person> person = personRepository.findById(id);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Get By Person Username.
   */
  public Person getPersonByUsername(String username) {
    Optional<Person> person = personRepository.findByUsername(username);

    if (person.isEmpty()) {
      throw new PersonNotFoundException();
    }

    return person.get();
  }

  /**
   * Create Person.
   */
  public Person create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findUserDetailsByUsername(username);
  }
}
