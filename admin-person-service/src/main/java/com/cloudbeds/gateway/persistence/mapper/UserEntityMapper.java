package com.cloudbeds.gateway.persistence.mapper;

import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.gateway.persistence.model.UserEntity;

import java.util.stream.Collectors;

public class UserEntityMapper {

    public static UserEntity toEntity(final User user){

        final var userEntity = UserEntity
                .builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .build();

        return userEntity;
    }

    public static User toCore(final UserEntity userEntity){
        final var addressEntities = userEntity.getAddresses()
                .stream()
                .map(addressEntity -> Address
                        .builder()
                        .id(addressEntity.getId())
                        .city(addressEntity.getCity())
                        .firstAddress(addressEntity.getFirstAddress())
                        .secondAddress(addressEntity.getSecondAddress())
                        .country(addressEntity.getCountry())
                        .zip(addressEntity.getZip())
                        .state(addressEntity.getState())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());

        return User
                .builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword())
                .addressList(addressEntities)
                .build();
    }
}
