package com.cloudbeds.core.usecases;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotCreatedException;

@FunctionalInterface
public interface CreateUserUseCase {
    User execute(User user) throws EntityNotCreatedException;
}
