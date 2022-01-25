package com.cloudbeds.gateway.persistence.repository;

import com.cloudbeds.gateway.persistence.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
