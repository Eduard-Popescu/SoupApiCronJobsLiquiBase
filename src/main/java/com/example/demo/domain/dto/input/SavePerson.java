package com.example.demo.domain.dto.input;

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
public class SavePerson {

    @NotEmpty(message = "The person name cannot be empty or null.")
    private String name;

    private Float sold;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SavePerson that = (SavePerson) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
