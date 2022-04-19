package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.Zamowienie;
import com.example.pzespolowe.Repositories.ProduktyZamowienieRepository;
import com.example.pzespolowe.Repositories.ZamowienieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/zamowienia")
public class ZamowienieController {
    private final ProduktyZamowienieRepository repository;
    private final ZamowienieRepository zamowienieRepository;

    public ZamowienieController(ProduktyZamowienieRepository repository, ZamowienieRepository zamowienieRepository) {
        this.repository = repository;
        this.zamowienieRepository = zamowienieRepository;
    }

    @GetMapping
    ResponseEntity<List<Zamowienie>> getAll() {
        List<Zamowienie> result = zamowienieRepository.findAll();
        return ResponseEntity.ok(result);
    }


}
