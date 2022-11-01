package com.portfolio.lr.controller;

import com.portfolio.lr.Dto.dtoExperience;
import com.portfolio.lr.entity.experience;
import com.portfolio.lr.security.controller.Mensaje;
import com.portfolio.lr.service.SExperience;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = {"https://frontendlr-a8647.web.app", "http://localhost:4200"})
public class CExperience {

    @Autowired
    SExperience sExperience;

    @GetMapping("/list")
    public ResponseEntity<List<experience>> list() {
        List<experience> list = sExperience.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<experience> getById(@PathVariable("id") int id) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }

        experience exp = sExperience.getOne(id).get();
        return new ResponseEntity(exp, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        sExperience.delete(id);
        return new ResponseEntity(new Mensaje("Eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoexp) {
        if (StringUtils.isBlank(dtoexp.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sExperience.existsByNombreE(dtoexp.getNombreE())) {
            return new ResponseEntity(new Mensaje("La experiencia ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        experience exp = new experience(dtoexp.getNombreE(), dtoexp.getDescripE());
        sExperience.save(exp);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoexp) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sExperience.existsByNombreE(dtoexp.getNombreE()) && sExperience.getByNombreE(dtoexp.getNombreE()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("La experiencia ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoexp.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoexp.getDescripE())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        experience exp = sExperience.getOne(id).get();
        exp.setNombreE(dtoexp.getNombreE());
        exp.setDescripE(dtoexp.getDescripE());

        sExperience.save(exp);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }

}
