package com.SeminarRegistration.repository;

import com.SeminarRegistration.entity.Seminar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class SeminarRepositoryImpl implements SeminarRepository {

    @PersistenceContext
    private final EntityManager em;

    public SeminarRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Seminar save(Seminar seminar) {
        em.persist(seminar);
        return seminar;
    }

    @Override
    public Optional<Seminar> findById(long seminarId) {
        return Optional.ofNullable(em.createQuery("SELECT s FROM Seminar s WHERE s.id == :seminarId", Seminar.class)
                .setParameter("seminarId", seminarId)
                .setParameter("seminarId", seminarId)
                .getSingleResult());
    }

    @Override
    public List<Seminar> findAll() {
        return em.createQuery("SELECT s FROM Seminar s;", Seminar.class)
                .getResultList();
    }


}

