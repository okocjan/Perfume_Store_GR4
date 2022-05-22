package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.ProduktyZamowienie;
import com.example.pzespolowe.Models.Zamowienie;
import com.example.pzespolowe.Repositories.ProduktyZamowienieRepository;
import com.example.pzespolowe.Repositories.ZamowienieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/panel/admin/proces")
public class ProcesController {

    private final ZamowienieRepository zamowienieRepository;
    private final ProduktyZamowienieRepository produktyZamowienieRepository;

    public ProcesController(ZamowienieRepository zamowienieRepository, ProduktyZamowienieRepository produktyZamowienieRepository) {
        this.zamowienieRepository = zamowienieRepository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
    }

    @GetMapping("/select")
    public ModelAndView selectOrder() {
        ModelAndView mav = new ModelAndView("/admin/proces/select_order");
        List<Zamowienie> zamList = zamowienieRepository.findAll();
        mav.addObject("zamowienia", zamList);
        return mav;
    }
    @GetMapping("/prepare")
    public ModelAndView prepareProduct() {
        ModelAndView mav = new ModelAndView("/admin/proces/prepare_product");
        List<ProduktyZamowienie> prodList = produktyZamowienieRepository.findProduktyZamowienieByStatus();
        List<Integer> list = prodList.stream().map(ProduktyZamowienie::getId).toList();
        Map<ProduktyZamowienie, Integer> testMap = new HashMap<>();

        for(ProduktyZamowienie r : prodList) {
            if(testMap.containsKey(r)) {
                testMap.put(r, testMap.get(r) + 1);
            } else {
                testMap.put(r, 1);
            }
        }

        System.out.println(testMap);
        mav.addObject("produktyZamowienia", testMap);
        return mav;
    }
    @GetMapping("/pack")
    public ModelAndView packProduct() {
        ModelAndView mav = new ModelAndView("/admin/proces/pack_product");
        return mav;
    }
    @GetMapping("/postman")
    public ModelAndView orderPostman() {
        ModelAndView mav = new ModelAndView("/admin/proces/order_postman");
        return mav;
    }
    @GetMapping("/finish")
    public ModelAndView finishProcess() {
        ModelAndView mav = new ModelAndView("/admin/proces/finish_process");
        return mav;
    }
}
