
package com.portfolio.lr.repository;

import com.portfolio.lr.entity.experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperience extends JpaRepository<experience, Integer>{
    public Optional<experience> findByNombreE(String nombreE);
    public boolean existsByNombreE(String nombreE);
}
