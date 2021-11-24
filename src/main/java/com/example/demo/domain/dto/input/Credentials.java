package com.example.demo.domain.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Credentials {

    @NotEmpty(message = "The email cannot be null or empty.")
    @Email(message = "The email should have a valid format.")
    private String email;
}
