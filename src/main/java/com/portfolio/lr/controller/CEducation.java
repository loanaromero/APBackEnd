package com.portfolio.lr.controller;

import com.portfolio.lr.Dto.dtoEducation;
import com.portfolio.lr.entity.education;
import com.portfolio.lr.security.controller.Mensaje;
import com.portfolio.lr.service.SEducation;
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
@RequestMapping("/education")
@CrossOrigin(origins = {"https://frontendlr-a8647.web.app", "http://localhost:4200"})
public class CEducation {

    @Autowired
    SEducation sEducation;

    @GetMapping("/list")
    public ResponseEntity<List<education>> list() {
        List<education> list = sEducation.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<education> getById(@PathVariable("id") int id) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        education edu = sEducation.getOne(id).get();
        return new ResponseEntity(edu, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducation.delete(id);
        return new ResponseEntity(new Mensaje("Eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEducation.existsByNombreEd(dtoedu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("La educación ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        education edu = new education(dtoedu.getNombreEd(), dtoedu.getDescripEd());
        sEducation.save(edu);

        return new ResponseEntity(new Mensaje("Educación agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoedu) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }

        if (sEducation.existsByNombreEd(dtoedu.getNombreEd()) && sEducation.getByNombreEd(dtoedu.getNombreEd()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("La educación ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoedu.getNombreEd())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoedu.getDescripEd())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        education edu = sEducation.getOne(id).get();
        edu.setNombreEd(dtoedu.getNombreEd());
        edu.setDescripEd(dtoedu.getDescripEd());

        sEducation.save(edu);
        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);
    }

}
