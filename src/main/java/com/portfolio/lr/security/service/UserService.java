
package com.portfolio.lr.security.service;

import com.portfolio.lr.security.entity.user;
import com.portfolio.lr.security.repository.IUserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    IUserRepository IuserRepository;
    
    public Optional<user> getByNombreUsuario(String nombreUsuario){
        return IuserRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return IuserRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email){
        return IuserRepository.existsByEmail(email);
    }
    
    public void save(user user){
        IuserRepository.save(user);
    }
}
