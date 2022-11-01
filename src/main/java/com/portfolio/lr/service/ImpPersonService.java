
package com.portfolio.lr.service;

import com.portfolio.lr.entity.Person;
import com.portfolio.lr.repository.IPersonRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonService{
    @Autowired IPersonRepository ipersonRepository;
    
    public List<Person> list(){
        return ipersonRepository.findAll();
    }
    
    public Optional<Person> getOne(int id) {
        return ipersonRepository.findById(id);
    }
    
    public Optional<Person> getByNombre(String nombre){
        return ipersonRepository.findByNombre(nombre);
    }
    
    public void save(Person pers){
        ipersonRepository.save(pers);
    }
    
    public void delete(int id){
        ipersonRepository.deleteById(id);
    }
    
    public boolean existsById(int id){
        return ipersonRepository.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return ipersonRepository.existsByNombre(nombre);
    }
}
