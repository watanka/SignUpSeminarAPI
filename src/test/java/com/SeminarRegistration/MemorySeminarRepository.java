package com.SeminarRegistration;

import com.SeminarRegistration.entity.Seminar;
import com.SeminarRegistration.repository.SeminarRepository;
import com.SeminarRegistration.service.RegisterPolicy;
import com.SeminarRegistration.service.RegistrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class MemorySeminarRepository implements SeminarRepository {
    private final Map<Long, Seminar> seminarTable = new ConcurrentHashMap<>();

    @Override
    public Seminar save(Seminar seminar) {
        seminarTable.put(seminar.getId(), seminar);
        return seminar;
    }

    @Override
    public Optional<Seminar> findById(long seminarId) {
        return Optional.ofNullable(seminarTable.get(seminarId));
    }

}
