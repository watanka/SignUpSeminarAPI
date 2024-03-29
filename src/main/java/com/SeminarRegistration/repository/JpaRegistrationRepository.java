package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaRegistrationRepository implements RegistrationRepository{

    @PersistenceContext
    private final EntityManager en;


    public JpaRegistrationRepository(EntityManager en) {
        this.en = en;
    }

    @Override
    public Registration save(String userId, long seminarId) {
        Registration registration = new Registration(userId, seminarId);
        en.persist(registration);
        return registration;
    }

    @Override
    public Optional<Registration> findByUserIdAndSeminarId(String userId, long seminarId) {

        return Optional.ofNullable(en.createQuery("SELECT r FROM Registration WHERE r.userId == :userId AND r.seminarId == :seminarId", Registration.class)
                .setParameter("userId", userId)
                .setParameter("seminarId", seminarId)
                .getSingleResult()
                );
    }
}
