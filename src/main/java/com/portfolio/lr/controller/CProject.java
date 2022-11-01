package com.portfolio.lr.controller;

import com.portfolio.lr.Dto.dtoProject;
import com.portfolio.lr.entity.project;
import com.portfolio.lr.security.controller.Mensaje;
import com.portfolio.lr.service.SProject;
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
@RequestMapping("/project")
@CrossOrigin(origins = {"https://frontendlr-a8647.web.app", "http://localhost:4200"})
public class CProject {

    @Autowired
    SProject sProject;

    @GetMapping("/list")
    public ResponseEntity<List<project>> list() {
        List<project> list = sProject.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<project> getById(@PathVariable("id") int id) {
        if (!sProject.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }

        project pro = sProject.getOne(id).get();
        return new ResponseEntity(pro, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProject.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        sProject.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProject dtopro) {
        if (StringUtils.isBlank(dtopro.getNombreP())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sProject.existsByNombreP(dtopro.getNombreP())) {
            return new ResponseEntity(new Mensaje("El proyecto ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }

        project pro = new project(dtopro.getNombreP(), dtopro.getDescripP(), dtopro.getLinkP(), dtopro.getImgP());
        sProject.save(pro);

        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProject dtopro) {
        if (!sProject.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sProject.existsByNombreP(dtopro.getNombreP()) && sProject.getByNombreP(dtopro.getNombreP()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El proyecto ingresado ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopro.getNombreP())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopro.getDescripP())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        project pro = sProject.getOne(id).get();
        pro.setNombreP(dtopro.getNombreP());
        pro.setDescripP(dtopro.getDescripP());
        pro.setLinkP(dtopro.getLinkP());
        pro.setImgP(dtopro.getImgP());

        sProject.save(pro);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
}
