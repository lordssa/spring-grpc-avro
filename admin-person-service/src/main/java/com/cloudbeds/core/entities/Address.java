package com.cloudbeds.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id;
    private String firstAddress;
    private String secondAddress;
    private String city;
    private String state;
    private String zip;
    private String country;
}
