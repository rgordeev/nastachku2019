package ru.multicon.serviceb.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.multicon.serviceb.entities.Good;
import ru.multicon.serviceb.services.GoodsService;

import java.util.List;

@Slf4j
@RestController
public class ServiceBController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    GoodsService goodsService;

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<String> hello() {

        return ResponseEntity.ok("Hello, ServiceB!");
    }

    @GetMapping("/askfora")
    @ResponseBody
    public ResponseEntity<String> chaining() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://servicea:8080/hello", String.class);
        return ResponseEntity.ok("A answer: " + response.getBody());
    }

    @GetMapping("/goods")
    @ResponseBody
    public ResponseEntity<List<Good>> goods() {
        return ResponseEntity.ok(goodsService.list());
    }

}
