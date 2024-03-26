package com.SeminarRegistration.registration;

import java.util.List;
import java.util.Optional;

public class MemoryUserList implements UserList{

    private final List<String> table = new List<String>();

    @Override
    public void save(String userId) {
        table.add(userId);
    }

    @Override
    public Optional<String> findById(String userId) {
        return table.stream()
                .filter(id -> id.equals(userId))
                .findAny();
    }

    @Override
    public List<String> getAll() {
        return table;
    }
}
