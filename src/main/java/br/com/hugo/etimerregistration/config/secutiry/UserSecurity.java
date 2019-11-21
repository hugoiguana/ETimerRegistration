package br.com.hugo.etimerregistration.config.secutiry;

import br.com.hugo.etimerregistration.domain.EProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class UserSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String pis;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSecurity(Long id, String pis, String password, List<EProfile> profileList) {
        super();
        this.id = id;
        this.pis = pis;
        this.password = password;
        this.authorities = profileList.stream().map(p -> new SimpleGrantedAuthority(p.getRole())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.pis;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
