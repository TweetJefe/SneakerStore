package service;

import dto.UserDTO;
import model.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
