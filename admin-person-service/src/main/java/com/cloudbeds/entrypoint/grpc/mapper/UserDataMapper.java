package com.cloudbeds.entrypoint.grpc.mapper;

import com.cloudbeds.avro.AddressData;
import com.cloudbeds.avro.Response;
import com.cloudbeds.avro.Status;
import com.cloudbeds.avro.UserData;
import com.cloudbeds.core.entities.Address;
import com.cloudbeds.core.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDataMapper {

    public static Response toClient(final User user){
        final var addresses = user.getAddressList()
                .stream()
                .map(address -> AddressData
                        .newBuilder()
                        .setId(address.getId())
                        .setCity(address.getCity())
                        .setFirstAddress(address.getFirstAddress())
                        .setSecondAddress(address.getSecondAddress())
                        .setCountry(address.getCountry())
                        .setZip(address.getZip())
                        .setState(address.getState())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());

        return Response
                .newBuilder()
                .setStatus(Status.OK)
                .setMessage(null)
                .setBody(getBody(user, addresses))
                .build();
    }

    private static UserData getBody(User user, List<AddressData> addresses) {
        return UserData
                .newBuilder()
                .setId(getValue(user.getId()))
                .setEmail(getValue(user.getEmail()))
                .setFirstName(getValue(user.getFirstName()))
                .setLastName(getValue(user.getLastName()))
                .setPassword(getValue(user.getPassword()))
                .setAddresses(addresses)
                .build();
    }

    private static String getValue(String input) {
        return Objects.isNull(input) ? "" : input;
    }

    private static Long getValue(Long input) {
        return Objects.isNull(input) ? 0L : input;
    }

    public static User toCore(final UserData userData){
        final var addresses = userData.getAddresses()
                .stream()
                .map(addressData -> Address
                        .builder()
                        .city(addressData.getCity())
                        .firstAddress(addressData.getFirstAddress())
                        .secondAddress(addressData.getSecondAddress())
                        .country(addressData.getCountry())
                        .zip(addressData.getZip())
                        .state(addressData.getState())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());

        return User
                .builder()
                .email(userData.getEmail())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .password(userData.getPassword())
                .addressList(addresses)
                .build();
    }
}
