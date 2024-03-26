package com.SeminarRegistration.registration;

public interface UserList {
    public void save(String userId);
    public Optional<String> findById(String userId);
    public List<String> getAll();
}
