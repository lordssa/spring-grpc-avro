package com.cloudbeds.core.usecases;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotFoundException;

@FunctionalInterface
public interface GetUserUseCase {
    User execute(Long id) throws EntityNotFoundException;
}
