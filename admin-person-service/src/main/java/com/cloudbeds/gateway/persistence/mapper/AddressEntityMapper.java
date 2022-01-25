package com.cloudbeds.gateway.persistence.mapper;

import com.cloudbeds.core.entities.User;
import com.cloudbeds.gateway.persistence.model.AddressEntity;
import com.cloudbeds.gateway.persistence.model.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AddressEntityMapper {

    public static List<AddressEntity> toEntity(final User user, final UserEntity userEntity) {
        return user.getAddressList()
                .stream()
                .map(address -> AddressEntity
                        .builder()
                        .city(address.getCity())
                        .firstAddress(address.getFirstAddress())
                        .secondAddress(address.getSecondAddress())
                        .country(address.getCountry())
                        .zip(address.getZip())
                        .state(address.getState())
                        .userEntity(userEntity)
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }
}
