package com.cloudbeds.entrypoint.grpc.service;

import com.cloudbeds.avro.Response;
import com.cloudbeds.avro.Status;
import com.cloudbeds.avro.UserAddressService;
import com.cloudbeds.avro.UserData;
import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.exception.EntityNotFoundException;
import com.cloudbeds.core.usecases.CreateUserUseCase;
import com.cloudbeds.core.usecases.GetUserUseCase;
import com.cloudbeds.entrypoint.grpc.mapper.UserDataMapper;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;

    public UserAddressServiceImpl(GetUserUseCase getUserUseCase, CreateUserUseCase createUserUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public Response RetrieveInfo(long id) {
        try {
            final var userFetched = getUserUseCase.execute(id);
            return UserDataMapper.toClient(userFetched);
        }catch (EntityNotFoundException entityNotFoundException){
            return getErrorResponse(Status.NOT_FOUND, entityNotFoundException.getMessage());
        }
    }

    @Override
    public Response Create(UserData userData) {

            return ofNullable(userData)
                    .map(UserDataMapper::toCore)
                    .map(user -> {
                        try {
                            final var userCreated = createUserUseCase.execute(user);
                            return UserDataMapper.toClient(userCreated);
                        }catch (EntityNotCreatedException entityNotCreatedException){
                            return getErrorResponse(Status.NOT_PROCESSED,
                                    entityNotCreatedException.getMessage());
                        }
                    })
                    .orElse(Response
                            .newBuilder()
                            .setStatus(Status.UNKNOWN)
                            .setBody(null)
                            .setMessage(null)
                            .build());

    }

    private Response getErrorResponse(Status notFound, String message) {
        return Response
                .newBuilder()
                .setStatus(notFound)
                .setMessage(message)
                .setBody(null)
                .build();
    }
}
