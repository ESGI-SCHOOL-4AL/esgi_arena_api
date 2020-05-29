package com.esgi.projetannuel.api;

import com.esgi.projetannuel.model.Person;
import com.esgi.projetannuel.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@NotBlank @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "/{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    //delete
    @DeleteMapping(path = "/{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    //update
    @PutMapping(path = "/{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @NotBlank @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
