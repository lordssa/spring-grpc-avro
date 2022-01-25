package com.cloudbeds.core.usecases.impl;

import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.gateway.UserDataGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Mock
    private UserDataGateway userDataGateway;

    @Test
    void testCreateUser() throws EntityNotCreatedException {
        var user = User
                .builder()
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addressList(List.of(Address.builder().build()))
                .build();

        var userCreated = User
                .builder()
                .id(1L)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addressList(List.of(Address.builder().id(1L).build()))
                .build();

        Mockito.when(userDataGateway.create(any(User.class))).thenReturn(userCreated);

        var response = createUserUseCase.execute(user);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getAddressList().get(0).getId());
    }
}