package com.example.demo.application;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CreateItemServiceTest {
    private CreateItemService createItemService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        createItemService = new CreateItemService(personRepository);
    }

    @Test
    void createItem() {
        createItemService.createItem("견우", "Z", "...");

        Person person = personRepository.findByName("견우").get();

        assertThat(person.items()).hasSize(3);

        person.removeItem("Z");

        assertThat(person.items()).hasSize(2);
    }
}
