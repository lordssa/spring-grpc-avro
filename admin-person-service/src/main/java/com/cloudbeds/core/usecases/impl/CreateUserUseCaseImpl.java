package com.cloudbeds.core.usecases.impl;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.gateway.UserDataGateway;
import com.cloudbeds.core.usecases.CreateUserUseCase;
import org.springframework.stereotype.Service;

@Service
class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserDataGateway userDataGateway;

    public CreateUserUseCaseImpl(UserDataGateway userDataGateway) {
        this.userDataGateway = userDataGateway;
    }

    @Override
    public User execute(User user) throws EntityNotCreatedException {
        return userDataGateway.create(user);
    }
}
