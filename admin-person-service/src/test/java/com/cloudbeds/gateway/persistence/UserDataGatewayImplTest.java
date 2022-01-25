package com.cloudbeds.gateway.persistence;

import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;
import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.exception.EntityNotFoundException;
import com.cloudbeds.gateway.persistence.model.AddressEntity;
import com.cloudbeds.gateway.persistence.model.UserEntity;
import com.cloudbeds.gateway.persistence.repository.AddressRepository;
import com.cloudbeds.gateway.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDataGatewayImplTest {

    @InjectMocks
    private UserDataGatewayImpl userDataGateway;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void testCreateUser() throws EntityNotCreatedException {
        var userEntityCreated = UserEntity
                .builder()
                .id(1L)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .build();

        var addressEntity = AddressEntity
                .builder()
                .id(1L)
                .firstAddress("dssdfsd")
                .secondAddress("adfhf")
                .city("dfsnfb")
                .country("fads")
                .state("asdfsdfs")
                .zip("fdfsdfs")
                .userEntity(userEntityCreated)
                .build();

        var address = Address
                .builder()
                .firstAddress("dssdfsd")
                .secondAddress("adfhf")
                .city("dfsnfb")
                .country("fads")
                .state("asdfsdfs")
                .zip("fdfsdfs")
                .build();

        var user = User
                .builder()
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addressList(List.of(address))
                .build();


        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityCreated);
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(addressEntity);

        var response = userDataGateway.create(user);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getAddressList().get(0).getId());
    }

    @Test
    void testFindById() throws EntityNotFoundException {
        var addressEntity = AddressEntity
                .builder()
                .id(1L)
                .firstAddress("dssdfsd")
                .secondAddress("adfhf")
                .city("dfsnfb")
                .country("fads")
                .state("asdfsdfs")
                .zip("fdfsdfs")
                .build();

        var userEntity = UserEntity
                .builder()
                .id(1L)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addresses(Set.of(addressEntity))
                .build();

        when(userRepository.fetchUserById(anyLong())).thenReturn(Optional.of(userEntity));
        var response = userDataGateway.findById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getAddressList().get(0).getId());
    }

    @Test
    void findByCountry() {
        var addressEntity = AddressEntity
                .builder()
                .id(1L)
                .firstAddress("dssdfsd")
                .secondAddress("adfhf")
                .city("dfsnfb")
                .country("fads")
                .state("asdfsdfs")
                .zip("fdfsdfs")
                .build();

        var userEntity = UserEntity
                .builder()
                .id(1L)
                .email("user@email.com")
                .firstName("firstname")
                .lastName("lastName")
                .addresses(Set.of(addressEntity))
                .build();

        when(userRepository.fetchUserByCountry(anyString())).thenReturn(List.of(userEntity));
        var response = userDataGateway.findByCountry("Brazil");

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId());
    }
}