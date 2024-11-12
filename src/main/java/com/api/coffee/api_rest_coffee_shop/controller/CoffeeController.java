package com.api.coffee.api_rest_coffee_shop.controller;


import com.api.coffee.api_rest_coffee_shop.models.Coffee;
import com.api.coffee.api_rest_coffee_shop.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll();
    }

    @PostMapping(value = "/criarCoffee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Coffee criarNewCoffee(@RequestBody Coffee coffee) {
        Coffee criarCoffee = new Coffee();

        criarCoffee.setNome(coffee.getNome());
        criarCoffee.setPreco(coffee.getPreco());

        return coffeeRepository.save(criarCoffee);
    }

    @PutMapping(value = "updateCoffee", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Coffee updateCoffee(@RequestBody Coffee coffee) {
        Coffee getCoffee = coffeeRepository.findById(coffee.getId()).orElseThrow();

        Coffee updateCoffee = new Coffee();

        updateCoffee.setId(coffee.getId());
        updateCoffee.setNome(coffee.getNome());
        updateCoffee.setPreco(coffee.getPreco());

    return coffeeRepository.save(updateCoffee);
    }

    @DeleteMapping (value = "/deleteCoffee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Coffee deleteCoffee(@PathVariable Long id){
        Coffee getCoffee= coffeeRepository.findById(id).orElseThrow();
    coffeeRepository.delete(getCoffee);
    return getCoffee;
    }
}