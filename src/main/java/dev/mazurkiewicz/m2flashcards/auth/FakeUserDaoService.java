package dev.mazurkiewicz.m2flashcards.auth;

import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static dev.mazurkiewicz.m2flashcards.security.user.UserRole.*;

@Repository("fake")
public class FakeUserDaoService implements UserDao {

    private final PasswordEncoder passwordEncoder;

    public FakeUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return getUsers().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }

    private List<User> getUsers() {
        List<User> users = Lists.newArrayList(
                new User("walaszek",
                        passwordEncoder.encode("1qaZXsw2"),
                        ADMIN.getGrantedAuthorities(),
                        true, true, true, true)     ,
                new User("vega",
                        passwordEncoder.encode("1qaZXsw2"),
                        USER.getGrantedAuthorities(),
                        true, true, true, true)
        );
        return users;
    }
}
