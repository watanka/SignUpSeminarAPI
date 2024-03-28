package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Registration;
import com.SeminarRegistration.domain.Seminar;
import com.SeminarRegistration.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaRegistrationRepository implements RegistrationRepository{

    @PersistenceContext
    private final EntityManager en;


    public JpaRegistrationRepository(EntityManager en) {
        this.en = en;
    }

    public Optional<Seminar> findSeminar(long seminarId){
        Seminar seminar = en.find(Seminar.class, seminarId);
        return Optional.ofNullable(seminar);
    }

    @Override
    public Registration save(long seminarId, String userId) {
        Optional<Seminar> seminarOptional = findSeminar(seminarId);
        if (seminarOptional.isPresent()){
            Registration registration = new Registration(seminarId, userId);
            en.persist(registration);
            return registration;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers(long seminarId) {

        String jpql = "SELECT u FROM User u " +
                "         JOIN Registration r ON u.userId = r.userId" +
                "         WHERE r.seminarId = :seminarId";

        return en.createQuery(jpql, User.class)
                        .setParameter("seminarId", seminarId)
                        .getResultList();
    }

    @Override
    public Optional<User> findUserById(long seminarId, String userId) {
        try{
            User user = en.createQuery("SELECT u FROM User u WHERE u.seminar.id = :seminarId AND u.id = :userId", User.class)
                    .setParameter("seminarId", seminarId)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return Optional.of(user);
        }catch(NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public long getMaxEnrollmentNum() {
        //TODO
        return 30L;
    }

    @Override
    public long getCurrentEnrollmentCount(long seminarId) {
        return en.createQuery("SELECT s.maxEnrollmentNum FROM Seminar WHERE s.id == :seminarId", Seminar.class)
                .setParameter("seminarId", seminarId)
                .getSingleResult();
    }
}
