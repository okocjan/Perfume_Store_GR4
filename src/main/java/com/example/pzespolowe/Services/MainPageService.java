package com.example.pzespolowe.Services;


import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.Projection.BestsellersProjection;
import com.example.pzespolowe.Models.Projection.ProdAndZdjModel;
import com.example.pzespolowe.Models.ZdjeciaProd;
import com.example.pzespolowe.Repositories.ProduktRepository;
import com.example.pzespolowe.Repositories.ProduktyZamowienieRepository;
import com.example.pzespolowe.Repositories.ZdjeciaProdRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MainPageService {
    private final ProduktRepository produktRepository;
    private final ZdjeciaProdRepository zdjeciaProdRepository;
    private final ProduktyZamowienieRepository produktyZamowienieRepository;

    public MainPageService(ProduktRepository produktRepository, ZdjeciaProdRepository zdjeciaProdRepository, ProduktyZamowienieRepository produktyZamowienieRepository) {
        this.produktRepository = produktRepository;
        this.zdjeciaProdRepository = zdjeciaProdRepository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
    }

    public List<List<ProdAndZdjModel>> getProdukts() {
        List<Produkt> produktList = produktRepository.findAll();
        List<ZdjeciaProd> zdjeciaProdList = zdjeciaProdRepository.findAll();
        List<List<ProdAndZdjModel>> toReturn = new ArrayList<>();

        List<ProdAndZdjModel> damskie = produktList
                .stream()
                .filter(produkt -> produkt.getRodzaj().equals("D"))
                .map(produkt -> {
                    ProdAndZdjModel ret = new ProdAndZdjModel();
                    ret.setNazwa(produkt.getNazwaProd());
                    ret.setPojemnosc(produkt.getPojemnosc());
                    ret.setCena(produkt.getCena());
                    ret.setRodzaj(produkt.getRodzaj());
                    ret.setId(produkt.getId());
                    String path = "";
                    for (ZdjeciaProd z : zdjeciaProdList) {
                        if (z.getId() == produkt.getId()) path = z.getSrc();
                    }
                    ret.setImagePath(path);
                    return ret;
                }).collect(Collectors.toList());

        List<ProdAndZdjModel> meskie = produktList
                .stream()
                .filter(produkt -> produkt.getRodzaj().equals("M"))
                .map(produkt2 -> {
                    ProdAndZdjModel ret = new ProdAndZdjModel();
                    ret.setNazwa(produkt2.getNazwaProd());
                    ret.setPojemnosc(produkt2.getPojemnosc());
                    ret.setCena(produkt2.getCena());
                    ret.setRodzaj(produkt2.getRodzaj());
                    ret.setId(produkt2.getId());
                    String path = "";
                    for (ZdjeciaProd z : zdjeciaProdList) {
                        if (z.getId() == produkt2.getId()) path = z.getSrc();
                    }
                    ret.setImagePath(path);
                    return ret;
                }).collect(Collectors.toList());

        toReturn.add(damskie);
        toReturn.add(meskie);

        return toReturn;
    }

    public ProdAndZdjModel getProdukt(Integer id) {
        Optional<Produkt> prod = produktRepository.findById(id);
        if (prod.isEmpty()) {
            return null;
        }
        List<String> zdj = zdjeciaProdRepository.findAll()
                .stream()
                .filter(zdjeciaProd -> zdjeciaProd.getId() == id)
                .map(ZdjeciaProd::getSrc).toList();

        ProdAndZdjModel toRet = new ProdAndZdjModel();
        toRet.setNazwa(prod.get().getNazwaProd());
        toRet.setImagePath(zdj.get(0));
        toRet.setCena(prod.get().getCena());
        toRet.setPojemnosc(prod.get().getPojemnosc());
        toRet.setId(prod.get().getId());
        toRet.setOpis(prod.get().getOpis());
        toRet.setRodzaj(prod.get().getRodzaj());

        return toRet;
    }

    public List<BestsellersProjection> getBestsellers() {
        List<BestsellersProjection> lista = produktyZamowienieRepository.getBestsellers();

        List<BestsellersProjection> toRet = new ArrayList<>();
        toRet.add(lista.get(0));
        toRet.add(lista.get(1));
        toRet.add(lista.get(2));

        return toRet;
    }

}
