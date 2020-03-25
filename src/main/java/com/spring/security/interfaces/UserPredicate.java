package com.spring.security.interfaces;

import com.spring.security.domain.User;

public interface UserPredicate {
    boolean test(User user);
}
