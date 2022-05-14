package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Repositories.ProduktRepository;
import com.example.pzespolowe.Services.ProduktService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class TestController {
    private final ProduktService service;
    private final ProduktRepository repository;

    public TestController(ProduktService service, ProduktRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/test/show")
    public ModelAndView showProdukt() {
        ModelAndView mav = new ModelAndView("list-produkt");
//        List<Klient> list = repository.findAll();
//        mav.addObject("employees", list);
        List<Produkt> list = repository.findAll();
        mav.addObject("produkty", list);
        return mav;
    }

    @GetMapping("/test/login")
    public ModelAndView showLogin() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @GetMapping("/test/str-prod")
    public ModelAndView showProductPage(@RequestParam int id) {
        System.out.println(id);
        ModelAndView mav = new ModelAndView("perfume-page");
        Optional<Produkt> object = repository.findById(id);
        Produkt produkt = object.get();
        mav.addObject("product", produkt);
        return mav;
    }
}
