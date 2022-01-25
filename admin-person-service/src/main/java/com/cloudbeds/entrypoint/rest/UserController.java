package com.cloudbeds.entrypoint.rest;

import com.cloudbeds.core.exception.EntityNotCreatedException;
import com.cloudbeds.core.usecases.CreateUserUseCase;
import com.cloudbeds.core.usecases.GetUserByCountryUseCase;
import com.cloudbeds.entrypoint.rest.dto.UserDto;
import com.cloudbeds.entrypoint.rest.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByCountryUseCase getUserByCountryUseCase;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userRequest){
        return  ofNullable(userRequest)
                .map(UserDtoMapper::toCore)
                .map(user -> {
                    try {
                        final var userCreated = createUserUseCase.execute(user);
                        final var userDto = UserDtoMapper.toDto(userCreated);
                        return new ResponseEntity(userDto, HttpStatus.CREATED);
                    }catch (EntityNotCreatedException entityNotCreatedException){
                        return new ResponseEntity(entityNotCreatedException.getMessage(),
                                HttpStatus.UNPROCESSABLE_ENTITY);
                    }
                })
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/country/{country_name}")
    public ResponseEntity<List<UserDto>> createUser(@PathVariable("country_name") String countryName){
        return  ofNullable(countryName)
                .map(getUserByCountryUseCase::execute)
                .map(users -> users
                        .stream()
                        .map(UserDtoMapper::toDto)
                        .collect(Collectors.toUnmodifiableList()))
                .map(userDtoList -> new ResponseEntity(userDtoList, HttpStatus.OK))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}
