package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Repositories.KlientRepository;
import com.example.pzespolowe.Models.Klient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/klient")
public class KlientController {
    private final KlientRepository klientRepository;

    public KlientController(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Klient>> getKlientById(@PathVariable int id) {
        if (klientRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(klientRepository.findById(id));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<List<Klient>> getAllKlients() {
        return ResponseEntity.ok(klientRepository.findAll());
    }

    @PostMapping(path="/add")
    @ResponseBody
    public ResponseEntity<Klient> addNewKlient (@RequestBody Klient klient) {
        Klient result = klientRepository.save(klient);
        return ResponseEntity.created(URI.create("/" + klient.getId())).body(result);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteKlient (@PathVariable int id) {
        if (klientRepository.findById(id).isPresent()) {
            klientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
