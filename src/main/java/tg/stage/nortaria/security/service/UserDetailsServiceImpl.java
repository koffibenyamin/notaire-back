package tg.stage.nortaria.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import tg.stage.nortaria.model.User;
import tg.stage.nortaria.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Il n'y a pas d'utilisateur correspondant au nom " + username));

        return UserDetailsImpl.build(user);
    }

    public long count() {
        return userRepository.count();
    }

    public List<User> getUserByRole(){
        return userRepository.findByRoles();
    }

}
