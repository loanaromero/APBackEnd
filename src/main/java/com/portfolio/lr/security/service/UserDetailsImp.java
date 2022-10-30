
package com.portfolio.lr.security.service;

import com.portfolio.lr.security.entity.mainuser;
import com.portfolio.lr.security.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImp implements UserDetailsService{
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        user user = userService.getByNombreUsuario(nombreUsuario).get();
        return mainuser.build(user);
    }
    
}
