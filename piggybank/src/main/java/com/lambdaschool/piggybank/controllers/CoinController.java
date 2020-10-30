package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {
    @Autowired
    CoinRepository coinRepository;

    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> getTotal(){
        List<Coin> coinList = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(coinList::add);

        double total = 0.0;
        for(Coin c : coinList){
            total += c.getQuantity() * c.getValue();
            System.out.println(c.getQuantity() + " " + (c.getQuantity() > 1 ? c.getNameplural() : c.getName()));
        }

        System.out.println("The total amount in your piggybank is " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
