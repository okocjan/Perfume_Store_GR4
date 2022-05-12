package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Services.ProduktService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produkty")
public class ProduktController {
    private final ProduktService service;

    public ProduktController(ProduktService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Produkt>> getAll() {
        return ResponseEntity.ok(service.getAllProdukty());
    }


    @GetMapping("/{id}")
    ResponseEntity<Optional<Produkt>> getById(@PathVariable int id) {
        Optional<Produkt> result = service.getById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}
