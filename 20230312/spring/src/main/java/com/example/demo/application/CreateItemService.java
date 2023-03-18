package com.example.demo.application;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.PersonNotFound;
import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

@Service
@Transactional
public class CreateItemService {
    private PersonRepository personRepository;

    public CreateItemService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    void createItem(String personName, String name, String usage) {
        Person person = personRepository.findByName(personName)
                .orElseThrow(() -> new PersonNotFound(personName));

        person.addItem(name, usage);

        personRepository.save(person);
    }
}
