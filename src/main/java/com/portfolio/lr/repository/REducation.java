
package com.portfolio.lr.repository;

import com.portfolio.lr.entity.education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REducation extends JpaRepository<education, Integer>{
    public Optional<education> findByNombreEd(String nombreEd);
    public boolean existsByNombreEd(String nombreEd);
}
