package dto;

import enums.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record UserDTO(
        Long id,
        String username,
        String name,
        String surname,
        String email,
        String password,
        String phone,
        LocalDate birthdate,
        List<String> roles,
        Set<Long> usersFavourites,
        Gender gender
) {
}
