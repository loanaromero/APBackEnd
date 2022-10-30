
package com.portfolio.lr.service;

import com.portfolio.lr.entity.hys;
import com.portfolio.lr.repository.RHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHys {
    @Autowired
    RHys rHys;
    
    public List<hys> list (){
        return rHys.findAll();
    }
    
    public Optional<hys> getOne(int id){
        return rHys.findById(id);
    }
    
    public Optional<hys> getByNombre(String nombre){
        return rHys.findByNombre(nombre);
    }
    
    public void save(hys skill){
        rHys.save(skill);
    }
    
    public void delete(int id){
        rHys.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHys.existsByNombre(nombre);
    }
}
