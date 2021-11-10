package com.example.demo.domain.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtractPerson {

    @NotNull(message = "The personId cannot be null")
    private Long personId;

    @NotEmpty(message = "The person name cannot be empty or null.")
    private String name;

    private Float sold;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtractPerson that = (ExtractPerson) o;
        return Objects.equals(personId, that.personId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, name);
    }
}
