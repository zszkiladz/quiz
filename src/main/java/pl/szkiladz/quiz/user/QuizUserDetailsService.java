package pl.szkiladz.quiz.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class QuizUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(QuizUserDetailsService.class);

    private final UserRepository userRepository;

    public QuizUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        log.trace("Searching for '{}'", username);
        final var userOptional = userRepository.findByEmail(username);
        log.debug("Found {} when searching by '{}'", userOptional, username);

        final var user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found. User: " + username));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}

