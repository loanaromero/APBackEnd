
package com.portfolio.lr.service;

import com.portfolio.lr.entity.project;
import com.portfolio.lr.repository.RProject;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProject {
    @Autowired
    RProject rProject;
    
    public List<project> list (){
        return rProject.findAll();
    }
    
    public Optional<project> getOne(int id){
        return rProject.findById(id);
    }
    
    public Optional<project> getByNombreP(String nombreP){
        return rProject.findByNombreP(nombreP);
    }
    
    public void save(project pro){
        rProject.save(pro);
    }
    
    public void delete(int id){
        rProject.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rProject.existsById(id);
    }
    
    public boolean existsByNombreP(String nombreP){
        return rProject.existsByNombreP(nombreP);
    }
}
