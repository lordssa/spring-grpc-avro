package com.cloudbeds.core.usecases.impl;

import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotFoundException;
import com.cloudbeds.core.gateway.UserDataGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class GetUserByCountryUseCaseImplTest {

    @InjectMocks
    private GetUserByCountryUseCaseImpl getUserByCountryUseCase;

    @Mock
    private UserDataGateway userDataGateway;

    @Test
    void testGetUserByCountry() {
        var user = User
                .builder()
                .id(1L)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addressList(List.of(Address.builder().build()))
                .build();

        Mockito.when(userDataGateway.findByCountry(anyString())).thenReturn(List.of(user));

        var response = getUserByCountryUseCase.execute("Brazil");

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId());
    }
}