package com.example.demo.domain.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @NotNull(message = "The userId cannot be null")
    private Long userId;

    @NotNull(message = "The id cannot be null")
    private Long id;

    @NotEmpty(message = "The title cannot be empty or null.")
    private String title;

    @NotEmpty(message = "The body cannot be empty or null.")
    private String body;
}
