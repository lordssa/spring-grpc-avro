package com.cloudbeds.gateway.persistence;

import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.exception.EntityNotFoundException;
import com.cloudbeds.core.gateway.UserDataGateway;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.gateway.persistence.mapper.AddressEntityMapper;
import com.cloudbeds.gateway.persistence.mapper.UserEntityMapper;
import com.cloudbeds.gateway.persistence.repository.AddressRepository;
import com.cloudbeds.gateway.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.of;

@Component
@RequiredArgsConstructor
class UserDataGatewayImpl implements UserDataGateway {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public User create(User user) throws EntityNotCreatedException {
        final var userEntity = of(UserEntityMapper.toEntity(user))
                .map(userRepository::save);

        final var addressEntities= AddressEntityMapper
                .toEntity(user, userEntity.get())
                .stream()
                .map(addressRepository::save)
                .collect(Collectors.toSet());

                return userEntity
                        .map(fullUserEntity -> fullUserEntity
                                        .toBuilder()
                                        .addresses(addressEntities)
                                        .build())
                .map(UserEntityMapper::toCore)
                .orElseThrow(() -> new EntityNotCreatedException("Saving was not executed successfully"));
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return userRepository.fetchUserById(id)
                .map(UserEntityMapper::toCore)
                .orElseThrow(() -> new EntityNotFoundException("No entity was found."));
    }

    @Override
    public List<User> findByCountry(String country) {
        return userRepository.fetchUserByCountry(country)
                .stream()
                .map(UserEntityMapper::toCore)
                .collect(Collectors.toUnmodifiableList());
    }
}
