package com.cloudbeds.core.usecases.impl;

import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotFoundException;
import com.cloudbeds.core.gateway.UserDataGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Mock
    private UserDataGateway userDataGateway;

    @Test
    void testGetUserById() throws EntityNotFoundException {
        var user = User
                .builder()
                .id(1L)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addressList(List.of(Address.builder().build()))
                .build();

        Mockito.when(userDataGateway.findById(anyLong())).thenReturn(user);

        var response = getUserUseCase.execute(1L);

        assertNotNull(response);
        assertEquals(1, response.getAddressList().size());
        assertEquals(1L, response.getId());
    }
}