package com.assist.openspacemanagement.utils.userDetails;
import com.assist.openspacemanagement.user.User;
import com.assist.openspacemanagement.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        CustomUserDetails customUserDetails;
        if(user != null) {
            customUserDetails = new CustomUserDetails();
            customUserDetails.setUserEntity(user);
        } else {
            throw new UsernameNotFoundException("User with username " + username + " does not exist.");
        }
        return customUserDetails;
    }
}
