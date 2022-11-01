package com.portfolio.lr.controller;

import com.portfolio.lr.Dto.dtoPerson;
import com.portfolio.lr.entity.Person;
import com.portfolio.lr.security.controller.Mensaje;
import com.portfolio.lr.service.ImpPersonService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = {"https://frontendlr-a8647.web.app", "http://localhost:4200"})
public class PersonController {

    @Autowired
    ImpPersonService sPerson;

    @GetMapping("/list")
    public ResponseEntity<List<Person>> list() {
        List<Person> list = sPerson.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") int id) {
        if (!sPerson.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }

        Person pers = sPerson.getOne(id).get();
        return new ResponseEntity(pers, HttpStatus.OK);
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sPerson.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        sPerson.delete(id);
        return new ResponseEntity(new Mensaje("Eliminada"), HttpStatus.OK);
    }*/

 /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPerson dtopers) {
        if (StringUtils.isBlank(dtopers.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sPerson.existsByNombreE(dtopers.getNombre())) {
            return new ResponseEntity(new Mensaje("La perseriencia ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        Person pers = new Person(dtopers.getNombre(), dtopers.getDescripE());
        sPerson.save(pers);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPerson dtopers) {
        if (!sPerson.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sPerson.existsByNombre(dtopers.getNombre()) && sPerson.getByNombre(dtopers.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("La persona ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopers.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopers.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopers.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Person pers = sPerson.getOne(id).get();
        pers.setNombre(dtopers.getNombre());
        pers.setApellido(dtopers.getApellido());
        pers.setDescripcion(dtopers.getDescripcion());
        pers.setImg(dtopers.getImg());

        sPerson.save(pers);
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }
}
