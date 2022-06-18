package com.example.pzespolowe.Services;


import com.example.pzespolowe.Dto.BasketProductDto;
import com.example.pzespolowe.Models.*;
import com.example.pzespolowe.Models.Projection.BasketProjection;
import com.example.pzespolowe.Models.Projection.BestsellersProjection;
import com.example.pzespolowe.Models.Projection.ProdAndZdjModel;
import com.example.pzespolowe.Repositories.*;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MainPageService {
    private final ProduktRepository produktRepository;
    private final ZdjeciaProdRepository zdjeciaProdRepository;
    private final ProduktyZamowienieRepository produktyZamowienieRepository;
    private final KlientRepository klientRepository;
    private final KoszykRepository koszykRepository;
    private final ZamowienieRepository zamowienieRepository;
    private final WysylkiRepository wysylkiRepository;
    private final MagazynRepository magazynRepository;
    public MainPageService(ProduktRepository produktRepository, ZdjeciaProdRepository zdjeciaProdRepository,
                           ProduktyZamowienieRepository produktyZamowienieRepository, KlientRepository klientRepository,
                           KoszykRepository koszykRepository, ZamowienieRepository zamowienieRepository,
                           WysylkiRepository wysylkiRepository, MagazynRepository magazynRepository) {
        this.produktRepository = produktRepository;
        this.zdjeciaProdRepository = zdjeciaProdRepository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
        this.klientRepository = klientRepository;
        this.koszykRepository = koszykRepository;
        this.zamowienieRepository = zamowienieRepository;
        this.wysylkiRepository = wysylkiRepository;
        this.magazynRepository = magazynRepository;
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

    public BasketProductDto getBasketItems() {
        BasketProductDto basketProductDto = new BasketProductDto();
        List<BasketProjection> toRet = koszykRepository.getBasket();

        toRet.stream()
                .forEach(item -> {
                    basketProductDto.getBasketItems().add(item);
                    basketProductDto.setFinalPrice(basketProductDto.getFinalPrice() + item.getPrice());
                });
        basketProductDto.setFinalPrice(Double.parseDouble((new DecimalFormat("##.##")
                .format(basketProductDto.getFinalPrice())).replace(",", ".")));

        return basketProductDto;
    }

    public void addProductToBasket(Integer id) {
        Klient klient = klientRepository.getById(1);
        Produkt produkt = produktRepository.getById(id);

        koszykRepository.save(new Koszyk(klient, produkt, 0));
    }

    public void removeProductFromBasket(Integer id) {
        koszykRepository.deleteById(id);
    }

    public void finalizeOrder(Integer idKl, List<Integer> productsIds, Double finalPrice) {
        idKl = 1;
        Optional<Klient> klient = klientRepository.findById(idKl);
        List<Produkt> produkts = new ArrayList<>();

        for (Integer i: productsIds) {
            if (i != null) produkts.add(produktRepository.findById(i).get());
        }

        koszykRepository.deleteAll();

        Zamowienie zamowienie = zamowienieRepository.save(new Zamowienie(klient.get().getAdres(),
                null, klient.get(),
                null, produkts,
                null,
                Status.IN_PROGRESS));

        for (Produkt p : zamowienie.getProdukts()) {
            ProduktyZamowienie prod = new ProduktyZamowienie(zamowienie, p);
            produktyZamowienieRepository.save(prod);
        }

        String trackNr = UUID.randomUUID().toString().replace("-", "");
        Wysylki wysylka = new Wysylki(zamowienie, "DPD", 0, trackNr, finalPrice);
        wysylkiRepository.save(wysylka);

        for (Produkt p: produkts) {
            Produkt prod = produktRepository.findById(p.getId()).get();
            Magazyn magItem = magazynRepository.findByProduktId(prod.getId());
            magItem.setIlosc(magItem.getIlosc() - 1);
            magazynRepository.save(magItem);
        }
    }
}