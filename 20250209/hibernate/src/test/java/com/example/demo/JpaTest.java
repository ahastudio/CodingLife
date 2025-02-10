package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.DateRange;
import com.example.demo.models.Employment;
import com.example.demo.models.Money;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JpaTest {
    @Test
    @DisplayName("Persist and find an entity")
    void persistAndFind() {
        Long employmentId = null;

        DateRange period = new DateRange(
                LocalDate.parse("2025-01-01"),
                LocalDate.parse("2025-01-31")
        );

        Money salary = new Money(new BigDecimal("10203.45"), "USD");

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("demo");
        EntityManager entityManager =
                entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            Employment employment = new Employment(period, salary);

            entityManager.persist(employment);

            entityManager.getTransaction().commit();

            employmentId = employment.id();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }

        assertThat(employmentId).isNotNull();

        {
            Employment employment = entityManager.find(
                    Employment.class, employmentId);

            assertThat(employment.period()).isEqualTo(period);
            assertThat(employment.salary()).isEqualTo(salary);
        }

        entityManager.close();
        entityManagerFactory.close();
    }
}
