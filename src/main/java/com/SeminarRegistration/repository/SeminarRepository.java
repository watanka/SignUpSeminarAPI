package com.SeminarRegistration.repository;

import com.SeminarRegistration.domain.Seminar;

import java.util.Optional;

public interface SeminarRepository {
    public Seminar save(Seminar seminar);

    public Optional<Seminar> findById(long seminarId);

}
