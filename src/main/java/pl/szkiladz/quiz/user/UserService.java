package pl.szkiladz.quiz.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szkiladz.quiz.user.exception.UserExistsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user.toEntity());
        log.debug("Saving quiz in the database. {}", user);
    }

    public Optional<UserDto> findById(String email) {
        log.debug("Searching for quiz by email {}", email);
        return repository.findByEmail(email).map(UserDto::from);
    }


    public List<UserDto> getAll() {
        log.debug("Finding all users");
        return repository.findAll().stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    public Optional<UserDto> findByEmail(String email) {
        return repository.findByEmail(email).map(UserDto::from);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void register(UserDto user) {
        Optional<UserDto> userOptional = findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserExistsException(user.getEmail());
        }
        log.info("Registering: {}", user);
        save(user);
    }
}
