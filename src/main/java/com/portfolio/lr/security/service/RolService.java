
package com.portfolio.lr.security.service;

import com.portfolio.lr.security.entity.Rol;
import com.portfolio.lr.security.enums.RolName;
import com.portfolio.lr.security.repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    IRolRepository IrolRepository;
    
    public Optional<Rol> getByRolName(RolName rolName){
        return IrolRepository.findByRolName(rolName);
    }
    
    public void save(Rol rol){
        IrolRepository.save(rol);
    }
}
