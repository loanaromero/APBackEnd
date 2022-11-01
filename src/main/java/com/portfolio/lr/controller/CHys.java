package com.portfolio.lr.controller;

import com.portfolio.lr.Dto.dtoHys;
import com.portfolio.lr.entity.hys;
import com.portfolio.lr.security.controller.Mensaje;
import com.portfolio.lr.service.SHys;
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
@RequestMapping("/skill")
@CrossOrigin(origins = {"https://frontendlr-a8647.web.app", "http://localhost:4200"})
public class CHys {

    @Autowired
    SHys sHys;

    @GetMapping("/list")
    public ResponseEntity<List<hys>> list() {
        List<hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }

        hys Hys = sHys.getOne(id).get();
        return new ResponseEntity(Hys, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        sHys.delete(id);
        return new ResponseEntity(new Mensaje("Eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sHys.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("La skill ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        hys Hys = new hys(dtohys.getNombre(), dtohys.getPorcentaje());
        sHys.save(Hys);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sHys.existsByNombre(dtohys.getNombre()) && sHys.getByNombre(dtohys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("La skill ingresada ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        
        hys Hys = sHys.getOne(id).get();
        Hys.setNombre(dtohys.getNombre());
        Hys.setPorcentaje(dtohys.getPorcentaje());

        sHys.save(Hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }
}
