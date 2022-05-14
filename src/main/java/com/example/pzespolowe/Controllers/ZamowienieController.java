package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.ProduktyZamowienie;
import com.example.pzespolowe.Models.Zamowienie;
import com.example.pzespolowe.Services.ZamownienieService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/zamowienia")
public class ZamowienieController {
    private final ZamownienieService service;

    public ZamowienieController(ZamownienieService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Zamowienie>> getAll() {
        List<Zamowienie> result = service.getAllZamownienie();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Zamowienie>> getById(@PathVariable int id) {
        Optional<Zamowienie> result = service.getById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/produkty")
    ResponseEntity<List<ProduktyZamowienie>> getProduktyFromZamowienie(@PathVariable int id) {
        return ResponseEntity.ok(service.getProduktyFromZamownienie(id));
    }




}
