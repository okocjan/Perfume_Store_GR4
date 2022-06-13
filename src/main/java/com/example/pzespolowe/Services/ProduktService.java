package com.example.pzespolowe.Services;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Repositories.MagazynRepository;
import com.example.pzespolowe.Repositories.ProduktRepository;
import com.example.pzespolowe.Repositories.ZdjeciaProdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduktService {
    private final ProduktRepository produktRepository;
    private final MagazynRepository magazynRepository;
    private final ZdjeciaProdRepository zdjeciaProdRepository;

    public ProduktService(ProduktRepository produktRepository,
                          MagazynRepository magazynRepository,
                          ZdjeciaProdRepository zdjeciaProdRepository) {
        this.produktRepository = produktRepository;
        this.magazynRepository = magazynRepository;
        this.zdjeciaProdRepository = zdjeciaProdRepository;
    }

    public List<Produkt> getAllProdukty() {
        List<Produkt> result = produktRepository.findAll();
        return result;
    }

    public Optional<Produkt> getById(final int id) {
        if (produktRepository.findById(id).isPresent()) {
            Optional<Produkt> result = produktRepository.findById(id);
            return result;
        }
        return null;
    }
}
