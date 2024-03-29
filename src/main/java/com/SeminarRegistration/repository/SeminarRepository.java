package com.SeminarRegistration.repository;

import com.SeminarRegistration.entity.Seminar;

import java.util.List;
import java.util.Optional;

public interface SeminarRepository {
    public Seminar save(Seminar seminar);

    public Optional<Seminar> findById(long seminarId);

    public List<Seminar> findAll();

}
