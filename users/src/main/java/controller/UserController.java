package controller;

import dto.UserDTO;
import dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(
        name = "Users", description = "API for work with users"
)
public class UserController {
    private final UserService userService;
    @PostMapping("/registration")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRegisterDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
