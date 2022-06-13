package com.example.pzespolowe.Services;

import com.example.pzespolowe.Models.Klient;
import com.example.pzespolowe.Repositories.KlientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KlientService {
    private final KlientRepository repository;

    public KlientService(KlientRepository repository) {
        this.repository = repository;
    }

    public Optional<Klient> getKlientById(final int id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id);
        }
        return Optional.empty();
    }

    public List<Klient> getAllKlients() {
        List<Klient> result = repository.findAll();
        return result;
    }

    public Klient addNewKlient (Klient klient) {
        Klient result = repository.save(klient);
        return result;
    }

    public boolean deleteKlient (int id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
