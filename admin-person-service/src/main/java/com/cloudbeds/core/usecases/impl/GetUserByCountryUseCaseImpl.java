package com.cloudbeds.core.usecases.impl;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.gateway.UserDataGateway;
import com.cloudbeds.core.usecases.GetUserByCountryUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class GetUserByCountryUseCaseImpl implements GetUserByCountryUseCase {

    private final UserDataGateway userDataGateway;

    public GetUserByCountryUseCaseImpl(UserDataGateway userDataGateway) {
        this.userDataGateway = userDataGateway;
    }

    @Override
    public List<User> execute(String country) {
        return userDataGateway.findByCountry(country);
    }
}
