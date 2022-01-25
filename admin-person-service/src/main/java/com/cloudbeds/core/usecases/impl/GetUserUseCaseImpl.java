package com.cloudbeds.core.usecases.impl;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotFoundException;
import com.cloudbeds.core.gateway.UserDataGateway;
import com.cloudbeds.core.usecases.GetUserUseCase;
import org.springframework.stereotype.Service;

@Service
class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserDataGateway userDataGateway;

    public GetUserUseCaseImpl(UserDataGateway userDataGateway) {
        this.userDataGateway = userDataGateway;
    }

    @Override
    public User execute(Long id) throws EntityNotFoundException {
        return userDataGateway.findById(id);
    }
}
