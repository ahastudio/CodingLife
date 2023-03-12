package com.example.demo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.models.Gender;
import com.example.demo.models.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JpaTest {
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("demo");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
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

            Person person = new Person("Mr.Big", 35, Gender.male());
            entityManager.persist(person);

            Person found = entityManager.find(Person.class, "Mr.Big");
            assertEquals(person, found);

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");
            assertEquals("Mr.Big", person.name());

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");
            person.changeAge(30);

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");
            assertEquals(30, person.age());

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "Mr.Big");
            entityManager.remove(person);

            Person found = entityManager.find(Person.class, "Mr.Big");
            assertNull(found);

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
    void queryAll() {
        String jpql = "SELECT person FROM Person person";

        List<Person> people = entityManager
                .createQuery(jpql, Person.class)
                .getResultList();

        assertEquals(2, people.size());
    }

    @Test
    void addAndRemoveItem() {
        EntityTransaction transaction = entityManager.getTransaction();

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "견우");

            person.addItem("테스트", "이건 테스트다");

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "견우");

            assertEquals(3, person.items().size());

            transaction.commit();
        }

        {
            transaction.begin();

            Person person = entityManager.find(Person.class, "견우");

            person.removeItem("테스트");

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
