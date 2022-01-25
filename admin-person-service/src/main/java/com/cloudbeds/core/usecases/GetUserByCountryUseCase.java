package com.cloudbeds.core.usecases;

import com.cloudbeds.core.entities.User;

import java.util.List;

@FunctionalInterface
public interface GetUserByCountryUseCase {
    List<User> execute(String country);
}
