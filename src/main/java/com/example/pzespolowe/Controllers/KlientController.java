package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.Klient;
import com.example.pzespolowe.Services.KlientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/klient")
public class KlientController {
    private final KlientService service;

    public KlientController(KlientService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Klient>> getKlientById(@PathVariable int id) {
        Optional<Klient> result = service.getKlientById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<List<Klient>> getAllKlients() {
        return ResponseEntity.ok(service.getAllKlients());
    }

    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity<Klient> addNewKlient (@RequestBody Klient klient) {
        Klient result = service.addNewKlient(klient);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteKlient (@PathVariable int id) {
        boolean flag = service.deleteKlient(id);
        if (flag) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
