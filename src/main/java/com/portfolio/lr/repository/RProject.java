
package com.portfolio.lr.repository;

import com.portfolio.lr.entity.project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RProject extends JpaRepository<project, Integer>{
    public Optional<project> findByNombreP(String nombreP);
    public boolean existsByNombreP(String nombreP);
}
