package org.hms.userms.jwt;

import org.hms.userms.dto.UserDTO;
import org.hms.userms.exception.HmsException;
import org.hms.userms.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            UserDTO dto = userService.getUser(email);
            return new CustomUserDetails(dto.getId(), dto.getEmail(),  dto.getPassword(),dto.getRole(),dto.getEmail(), dto.getName(),dto.getProfileId(),null);
        }
        catch(HmsException e){
            e.printStackTrace();
        }
        return null;
    }
}
