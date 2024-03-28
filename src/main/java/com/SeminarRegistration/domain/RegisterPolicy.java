package com.SeminarRegistration.domain;

import java.util.List;
import java.util.Optional;

public interface RegisterPolicy {

    boolean validate(String userId, long currentEnrollmentCount, long maxEnrollment, Optional<User> registerList) throws IllegalStateException;



}
