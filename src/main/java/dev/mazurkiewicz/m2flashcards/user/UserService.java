package dev.mazurkiewicz.m2flashcards.user;

import dev.mazurkiewicz.m2flashcards.auth.Authority;
import dev.mazurkiewicz.m2flashcards.auth.AuthorityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final JpaUserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;

    public UserService(JpaUserRepository userRepository, UserMapper userMapper, AuthorityService authorityService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authorityService = authorityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.selectUserByUsername(username)
                .map(M2UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public UserResponse registerUser(NewUserRequest userRequest) {
        User toRegister = userMapper.mapRequestToEntity(userRequest);
        Set<Authority> authorities = toRegister.getAuthorities().stream()
                .map(authorityService::findInDatabaseOrSave)
                .collect(Collectors.toSet());
        toRegister.setAuthorities(authorities);

        User registeredUser = userRepository.save(toRegister);
        return userMapper.mapEntityToResponse(registeredUser);
    }
}
