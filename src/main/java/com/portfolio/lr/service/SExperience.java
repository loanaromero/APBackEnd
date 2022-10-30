
package com.portfolio.lr.service;

import com.portfolio.lr.entity.experience;
import com.portfolio.lr.repository.RExperience;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperience {
    @Autowired
    RExperience rExperience;
    
    public List<experience> list(){
        return rExperience.findAll();
    }
    
    public Optional<experience> getOne(int id) {
        return rExperience.findById(id);
    }
    
    public Optional<experience> getByNombreE(String nombreE){
        return rExperience.findByNombreE(nombreE);
    }
    
    public void save(experience expe){
        rExperience.save(expe);
    }
    
    public void delete(int id){
        rExperience.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rExperience.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rExperience.existsByNombreE(nombreE);
    }
}
