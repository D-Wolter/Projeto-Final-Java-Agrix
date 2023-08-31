package com.betrybe.agrix.models.repository;

import com.betrybe.agrix.models.entities.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Person Repository.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

  Optional<Person> findByUsername(String username);


  UserDetails findUserDetailsByUsername(String username);
}
