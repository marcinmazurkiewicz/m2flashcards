package dev.mazurkiewicz.m2flashcards.auth;

import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Set;

@Data
public class M2UserDetails  implements UserDetails {

    private String username;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private Set<? extends GrantedAuthority> authorities;

    public M2UserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.isAccountNonExpired = !user.isAccountExpired();
        this.isAccountNonLocked = !user.isAccountLocked();
        this.isCredentialsNonExpired = !user.isAccountLocked();
        this.isEnabled = user.isEnabled();
        this.authorities = user.getAuthorities();
    }

}
