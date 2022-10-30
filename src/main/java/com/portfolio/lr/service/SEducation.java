
package com.portfolio.lr.service;

import com.portfolio.lr.entity.education;
import com.portfolio.lr.repository.REducation;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SEducation {
    @Autowired
    REducation rEducation;
    
        public List<education> list(){
        return rEducation.findAll();
    }
    
    public Optional<education> getOne(int id) {
        return rEducation.findById(id);
    }
    
    public Optional<education> getByNombreEd(String nombreEd){
        return rEducation.findByNombreEd(nombreEd);
    }
    
    public void save(education edu){
        rEducation.save(edu);
    }
    
    public void delete(int id){
        rEducation.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rEducation.existsById(id);
    }
    
    public boolean existsByNombreEd(String nombreEd){
        return rEducation.existsByNombreEd(nombreEd);
    }
}
