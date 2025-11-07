package dto;

import jdk.jfr.Name;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserRegisterDTO {
    @NotBlank(message = "Username can not be empty")
    private String username;

    @Name
    private String name;

    @Surname
    private String surname;

    @NotNull(message = "Email is ___")
    @NotBlank(message = "Email can not be empty")
    private String email;

    @Password
    private String password;

    @BirthDate
    private LocalDate birthdate;

    @NotNull(message = "You have to choose a gender")
    private boolean isMale;


}
