package com.cloudbeds.entrypoint.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private Long id;

    @NotNull(message = "The field address is required")
    @NotBlank(message = "The field address cannot be empty")
    @JsonProperty("first_address")
    private String firstAddress;

    @JsonProperty("second_address")
    private String secondAddress;

    @NotNull(message = "The field city is required")
    @NotBlank(message = "The field city cannot be empty")
    private String city;

    @NotNull(message = "The field state is required")
    @NotBlank(message = "The field state cannot be empty")
    private String state;

    @NotNull(message = "The field zip is required")
    @NotBlank(message = "The field zip cannot be empty")
    private String zip;

    @NotNull(message = "The field country is required")
    @NotBlank(message = "The field country cannot be empty")
    private String country;
}
