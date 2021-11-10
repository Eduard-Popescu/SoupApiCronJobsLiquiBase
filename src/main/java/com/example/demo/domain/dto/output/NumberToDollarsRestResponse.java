package com.example.demo.domain.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NumberToDollarsRestResponse {

    @NotEmpty(message = "The numberToDollarsResult cannot be empty or null.")
    private String numberToDollarsResult;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberToDollarsRestResponse that = (NumberToDollarsRestResponse) o;
        return Objects.equals(numberToDollarsResult, that.numberToDollarsResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberToDollarsResult);
    }
}
