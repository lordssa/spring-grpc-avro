package com.cloudbeds.entrypoint.rest.mapper;

import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.entrypoint.rest.dto.AddressDto;
import com.cloudbeds.entrypoint.rest.dto.UserDto;

import java.util.stream.Collectors;

public class UserDtoMapper {

    public static UserDto toDto(final User user){
        final var addresses = user.getAddressList()
                .stream()
                .map(address -> AddressDto
                        .builder()
                        .id(address.getId())
                        .city(address.getCity())
                        .firstAddress(address.getFirstAddress())
                        .secondAddress(address.getSecondAddress())
                        .country(address.getCountry())
                        .zip(address.getZip())
                        .state(address.getState())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());

        return UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .addressList(addresses)
                .build();
    }

    public static User toCore(final UserDto userDto){
        final var addresses = userDto.getAddressList()
                .stream()
                .map(addressDto -> Address
                        .builder()
                        .city(addressDto.getCity())
                        .firstAddress(addressDto.getFirstAddress())
                        .secondAddress(addressDto.getSecondAddress())
                        .country(addressDto.getCountry())
                        .zip(addressDto.getZip())
                        .state(addressDto.getState())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());

        return User
                .builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .addressList(addresses)
                .build();
    }
}
