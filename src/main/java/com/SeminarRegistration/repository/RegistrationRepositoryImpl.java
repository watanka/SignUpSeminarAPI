package com.SeminarRegistration.repository;

import com.SeminarRegistration.entity.Registration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository{

    @PersistenceContext
    private final EntityManager em;


    public RegistrationRepositoryImpl(EntityManager en) {
        this.em = en;
    }

    @Override
    public Registration save(String userId, long seminarId) {
        Registration registration = new Registration(userId, seminarId);
        em.persist(registration);
        return registration;
    }

    @Override
    public Optional<Registration> findByUserIdAndSeminarId(String userId, long seminarId) {

        return Optional.ofNullable(em.createQuery("SELECT r FROM Registration r WHERE r.userId == :userId AND r.seminarId == :seminarId", Registration.class)
                .setParameter("userId", userId)
                .setParameter("seminarId", seminarId)
                .getSingleResult()
                );
    }
}
