package ru.sllite.springtodo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sllite.springtodo.enums.AuthorityType;
import ru.sllite.springtodo.model.User;
import ru.sllite.springtodo.model.UserDetail;
import ru.sllite.springtodo.repository.AuthorityRepository;
import ru.sllite.springtodo.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetail(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not present")));
    }

    public void createUser(User user, AuthorityType authorityType) throws RuntimeException {
        user.setAuthorities(authorityRepository.findAuthorityByName(authorityType)
                .orElseThrow(() -> new RuntimeException("Authority not found")));
        userRepository.save(user);
    }
}
