package com.example.pzespolowe.Services;

import com.example.pzespolowe.Models.ProduktyZamowienie;
import com.example.pzespolowe.Models.Zamowienie;
import com.example.pzespolowe.Repositories.ProduktyZamowienieRepository;
import com.example.pzespolowe.Repositories.ZamowienieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZamownienieService {
    private final ZamowienieRepository repository;
    private final ProduktyZamowienieRepository produktyZamowienieRepository;

    public ZamownienieService(ZamowienieRepository repository, ProduktyZamowienieRepository produktyZamowienieRepository) {
        this.repository = repository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
    }

    public List<Zamowienie> getAllZamownienie () {
        List<Zamowienie> result = repository.findAll();
        return result;
    }

    public Optional<Zamowienie> getById(final int id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id);
        }
        return Optional.empty();
    }

    public List<ProduktyZamowienie> getProduktyFromZamownienie(final int id) {
        if (!produktyZamowienieRepository.findProduktyZamowienieByZamowienieId(id).isEmpty()) {
            List<ProduktyZamowienie> result = produktyZamowienieRepository.findProduktyZamowienieByZamowienieId(id);
            return result;
        }
        return null;
    }
}
