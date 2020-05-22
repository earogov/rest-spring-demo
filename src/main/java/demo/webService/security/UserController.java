package demo.webService.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @NonNull
    protected final UserRepository userRepository;

    @GetMapping(value = "/users/all", produces = "application/json")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}