package com.example.demo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.models.Age;
import com.example.demo.models.Gender;
import com.example.demo.models.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JpaTest {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    static void createEntityManagerFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("demo");
    }

    @AfterAll
    static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    @BeforeEach
    void setUp() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
    }

    @Test
    void query() {
        Person person = entityManager.find(Person.class, "견우");

        System.out.println("*".repeat(80));
        System.out.println(person);
        System.out.println("*".repeat(80));
    }

    @Test
    void crud() {
        EntityTransaction transaction = entityManager.getTransaction();

        {
            transaction.begin();

            Person person = new Person("Mr.Big", new Age(35), Gender.male());

            entityManager.persist(person);

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");

            assertNotNull(person);
            assertEquals(Gender.male(), person.gender());

            person.changeAge(new Age(100));

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");

            assertEquals(new Age(100), person.age());

            entityManager.remove(person);

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");

            assertNull(person);

            transaction.commit();
        }
    }

    @Test
    void queryPeople() {
        String jpql = "SELECT person FROM Person person";

        List<Person> people = entityManager
                .createQuery(jpql, Person.class)
                .getResultList();

        assertEquals(2, people.size());
    }

    @Test
    void createAndRemoveItem() {
        EntityTransaction transaction = entityManager.getTransaction();

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "견우");

            person.addItem("Z", "이건 끝이야");

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "견우");

            assertEquals(3, person.items().size());

            person.removeItem("Z");

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "견우");

            assertEquals(2, person.items().size());

            transaction.commit();
        }
    }
}
