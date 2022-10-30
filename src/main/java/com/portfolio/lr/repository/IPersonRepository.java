
package com.portfolio.lr.repository;

import com.portfolio.lr.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository <Person, Long>{
    
}
