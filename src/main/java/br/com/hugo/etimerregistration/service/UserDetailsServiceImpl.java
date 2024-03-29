package br.com.hugo.etimerregistration.service;

import br.com.hugo.etimerregistration.config.secutiry.JWTUtil;
import br.com.hugo.etimerregistration.config.secutiry.UserSecurity;
import br.com.hugo.etimerregistration.domain.EProfile;
import br.com.hugo.etimerregistration.domain.Employee;
import br.com.hugo.etimerregistration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Arrays.asList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String pis) throws UsernameNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findByPis(pis);
        Employee employee = employeeOptional.orElseThrow(() -> new UsernameNotFoundException(pis));
        return new UserSecurity(employee.getId(), employee.getPis(), employee.getPassword(), asList(EProfile.toEnum(employee.getProfile())));
    }

    public UserSecurity authenticated() {
        try {
            return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtil.tokenValido(token)) {
            String userName = jwtUtil.getUserName(token);
            UserDetails user = loadUserByUsername(userName);
            return new UsernamePasswordAuthenticationToken(user, user.getAuthorities(), user.getAuthorities());
        }
        return null;
    }
}
