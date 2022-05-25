package com.example.pzespolowe.Services;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.Projection.ProdAndZdjModel;
import com.example.pzespolowe.Models.ZdjeciaProd;
import com.example.pzespolowe.Repositories.ProduktRepository;
import com.example.pzespolowe.Repositories.ZdjeciaProdRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPageService {

    private final ProduktRepository produktRepository;
    private final ZdjeciaProdRepository zdjeciaProdRepository;

    public MainPageService(ProduktRepository produktRepository, ZdjeciaProdRepository zdjeciaProdRepository) {
        this.produktRepository = produktRepository;
        this.zdjeciaProdRepository = zdjeciaProdRepository;
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
}
