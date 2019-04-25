package ru.multicon.servicea.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.multicon.servicea.entities.Person;
import ru.multicon.servicea.services.PersonService;

import java.util.List;

@Slf4j
@RestController
public class ServiceAController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PersonService personService;

    @GetMapping(value = "/hello")
    @ResponseBody
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello!");
    }

    @GetMapping("/askforb")
    @ResponseBody
    public ResponseEntity<String> chaining() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://serviceb:8080/hello", String.class);
        return ResponseEntity.ok("B answer: " + response.getBody());
    }

    @GetMapping(value = "/persons", produces = "application/json")
    public ResponseEntity<List<Person>> persons() {
        return ResponseEntity.ok(personService.list());
    }

    @GetMapping(value = "/goods", produces = "application/json")
    public ResponseEntity<?> goods() {

        ResponseEntity<?> response = restTemplate.getForEntity("http://serviceb:8080/goods", Object.class);

        return ResponseEntity.ok(response.getBody());
    }

}
