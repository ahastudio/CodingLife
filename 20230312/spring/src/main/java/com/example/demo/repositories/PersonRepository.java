package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
    List<Person> findAll();

    Optional<Person> findByName(String name);

    Person save(Person person);

    void delete(Person person);
}
