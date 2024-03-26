package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Seminar;
import com.SeminarRegistration.domain.AppUser;
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
    public AppUser save(long seminarId, String userId) {
        Optional<Seminar> seminarOptional = findSeminar(seminarId);
        if (seminarOptional.isPresent()){
            Seminar seminar = seminarOptional.get();
            AppUser appUser = new AppUser(userId);
            seminar.getAppUsers().add(appUser);
            appUser.setSeminar(seminar);
            return appUser;
        }
        return null;
    }

    @Override
    public List<AppUser> getAllUsers(long seminarId) {
        return en.createQuery("SELECT u FROM User u WHERE u.seminar.id = :seminarId", AppUser.class)
                .setParameter("seminarId", seminarId)
                .getResultList();
    }

    @Override
    public Optional<AppUser> findUserById(long seminarId, String userId) {
        try{
            AppUser appUser = en.createQuery("SELECT u FROM User u WHERE u.seminar.id = :seminarId AND u.id = :userId", AppUser.class)
                    .setParameter("seminarId", seminarId)
                    .setParameter("userId", userId)
                    .getSingleResult();
            return Optional.of(appUser);
        }catch(NoResultException e){
            return Optional.empty();
        }
    }
}
