package com.example.demo;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Age;
import com.example.demo.models.Gender;
import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class JpaTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void findAll() {
        List<Person> people = personRepository.findAll();

        assertThat(people).hasSize(2);
    }

    @Test
    void createAndDelete() {
        Person person = new Person("Mr.Big", new Age(35), Gender.male());

        personRepository.save(person);

        Optional<Person> found = personRepository.findByName("Mr.Big");

        personRepository.delete(found.get());
    }
}
