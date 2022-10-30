package com.portfolio.lr.controller;

import com.portfolio.lr.Interface.IPersonService;
import com.portfolio.lr.entity.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://frontendlr-a8647.web.app")
public class PersonController {

    @Autowired
    IPersonService ipersonService;

    @GetMapping("/people/get")
    public List<Person> getPerson() {
        return ipersonService.getPerson();
    }

    @PostMapping("/people/create")
    public String createPerson(@RequestBody Person person) {
        ipersonService.savePerson(person);
        return "Persona creada con éxito";
    }

    @DeleteMapping("/people/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        ipersonService.deletePerson(id);
        return "Persona eliminada con éxito";
    }

    @PutMapping("/people/edit/{id}")
    public Person editPerson(@PathVariable Long id,
            @RequestParam("nombre") String newName,
            @RequestParam("apellido") String newLastName,
            @RequestParam("img") String newImage) {
        Person person = ipersonService.findPerson(id);

        person.setNombre(newName);
        person.setApellido(newLastName);
        person.setImg(newImage);

        ipersonService.savePerson(person);
        return person;
    }

    @GetMapping("/people/get/profile")
    public Person findPerson() {
        return ipersonService.findPerson((long) 1);
    }
}
