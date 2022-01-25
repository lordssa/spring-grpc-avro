package com.cloudbeds.core.gateway;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.exception.EntityNotFoundException;

import java.util.List;

public interface UserDataGateway {
    User create(User user) throws EntityNotCreatedException;
    User findById(Long id) throws EntityNotFoundException;
    List<User> findByCountry(String country);
}
