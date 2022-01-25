package com.cloudbeds.entrypoint.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;

    @NotNull(message = "The field first name is required")
    @NotBlank(message = "The field first name cannot be empty")
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "The field email is required")
    @NotBlank(message = "The field email cannot be empty")
    private String email;

    @NotNull(message = "The field password is required")
    @NotBlank(message = "The field password cannot be empty")
    private String password;

    @JsonProperty("addresses")
    @NotNull(message = "The field address is required")
    @Size(min=1, message = "The address must be provided")
    private List<AddressDto> addressList;
}
