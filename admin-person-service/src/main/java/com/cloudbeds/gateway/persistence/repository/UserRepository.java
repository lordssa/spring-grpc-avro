package com.cloudbeds.gateway.persistence.repository;

import com.cloudbeds.gateway.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT u FROM UserEntity u JOIN FETCH u.addresses a WHERE u.id = :id")
    Optional<UserEntity> fetchUserById(@Param("id") Long id);

    @Query(value = "SELECT DISTINCT u FROM UserEntity u JOIN FETCH u.addresses a WHERE a.country = :country")
    List<UserEntity> fetchUserByCountry(@Param("country") String countryName);
}
