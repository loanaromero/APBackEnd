
package com.portfolio.lr.repository;

import com.portfolio.lr.entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository <Person, Integer>{
    public Optional<Person> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
