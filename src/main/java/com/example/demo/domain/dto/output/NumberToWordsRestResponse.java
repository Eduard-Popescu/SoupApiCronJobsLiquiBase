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
public class NumberToWordsRestResponse {

    @NotEmpty(message = "The numberToWordsResult cannot be empty or null.")
    private String numberToWordsResult;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberToWordsRestResponse that = (NumberToWordsRestResponse) o;
        return Objects.equals(numberToWordsResult, that.numberToWordsResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberToWordsResult);
    }
}
