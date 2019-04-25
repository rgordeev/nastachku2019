package ru.multicon.servicea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.multicon.servicea.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
