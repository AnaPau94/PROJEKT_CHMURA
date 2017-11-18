package pl.booksmanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.booksmanagement.model.User;
import pl.booksmanagement.repository.UserRepository;
import pl.booksmanagement.service.UserService;

import java.security.Principal;

@Service("userDetailsService")
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final java.util.Random rand = new java.util.Random();

    @Override
    public boolean isUserLoginAvailable(String login) {
        User user = userRepository.findByUsername(login);
        return user == null;
    }

    @Override
    public void createExampleUser() {
        User u = new User();
        u.setUsername(randomIdentifier());
        String password = randomIdentifier();
        log.info("Password={}", password);
        u.setPassword(passwordEncoder.encode(password));
        u.setEnabled(true);

        log.info("Save user {}", u);
        userRepository.save(u);
    }

    @Override
    public boolean createNewUser(User u) {
        if (StringUtils.isBlank(u.getUsername()) || StringUtils.isBlank(u.getPassword())) {
            log.warn("User creation failed. Username or password is empty. {}", u);
            return false;
        }
        if (!isUserLoginAvailable(u.getUsername())) {
            log.warn("Such username is already exists. {}", u);
            return false;
        }

        log.info("Create new user with password={}", u.getPassword());
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        u.setEnabled(true);

        userRepository.save(u);

        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }

        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id);
    }

    @Override
    public void saveUser(User u) {
        if (u == null) {
            return;
        }

        userRepository.save(u);
    }


    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public Long getAuthUserId(Principal principal) {
        principal.getName();
        return 0L;
    }
}
