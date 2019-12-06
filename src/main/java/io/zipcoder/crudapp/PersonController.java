package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getAllPersons() {                       // READ
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);     // Response 200 OK
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {                // READ
        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);   // Response 200 OK
    }

    @PostMapping(("/people"))
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {              // CREATE
        return new ResponseEntity<>(personRepository.save(person), HttpStatus.CREATED);   // Response 201 CREATED
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person newPersonData) { // UPDATE
        if (newPersonData.getId() != null)
            return new ResponseEntity<>(personRepository.save(newPersonData), HttpStatus.OK);
        else
            return createPerson(newPersonData);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Long id) { // DELETE
        personRepository.delete(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);  // Response 204 NO CONTENT
    }
}