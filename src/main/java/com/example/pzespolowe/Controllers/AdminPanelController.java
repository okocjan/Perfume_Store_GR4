package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Dto.SaveProductDto;
import com.example.pzespolowe.Models.Magazyn;
import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.ProduktyZamowienie;
import com.example.pzespolowe.Models.Zamowienie;
import com.example.pzespolowe.Repositories.ProduktRepository;
import com.example.pzespolowe.Repositories.ProduktyZamowienieRepository;
import com.example.pzespolowe.Repositories.ZamowienieRepository;
import com.example.pzespolowe.Services.ProduktService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/panel/admin")
public class AdminPanelController {
    private final ProduktService service;
    private final ProduktRepository repository;
    private final ZamowienieRepository zamowienieRepository;

    private final ProduktyZamowienieRepository produktyZamowienieRepository;
    public AdminPanelController(ProduktService service, ProduktRepository repository, ZamowienieRepository zamowienieRepository, ProduktyZamowienieRepository produktyZamowienieRepository) {
        this.service = service;
        this.repository = repository;
        this.zamowienieRepository = zamowienieRepository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
    }

    @GetMapping({"/dashboard", "/"})
    public ModelAndView showAdminPage() {
        ModelAndView mav = new ModelAndView("/admin/dashboard");
        return mav;
    }

    @GetMapping("/404")
    public ModelAndView showErrorPage() {
        ModelAndView mav = new ModelAndView("/admin/404");
        return mav;
    }

    @GetMapping("/basic-table")
    public ModelAndView showTablePage() {
        ModelAndView mav = new ModelAndView("/admin/basic-table");
        List<Zamowienie> zamowienia = zamowienieRepository.findZamowienieByStatus("COMPLETED");
        mav.addObject("zamowienia", zamowienia);
        return mav;
    }

    @GetMapping("/add_product")
    public ModelAndView showBlankPage() {
        ModelAndView mav = new ModelAndView("/admin/add_product");
        mav.addObject("addDto", new SaveProductDto());
        return mav;
    }

    @PostMapping("/add_product/add")
    public String addProductToRepo(@ModelAttribute SaveProductDto saveProductDto, Model model) {
        System.out.println(saveProductDto);
//        repository.save(new Produkt(saveProductDto.getName(), saveProductDto.getQuantity(),
//                saveProductDto.getPrice(), saveProductDto.getType(),
//                new Magazyn(saveProductDto.getQuantity())));
        return "redirect:/panel/admin/add_product";
    }

    @GetMapping("/fontawesome")
    public ModelAndView showFontPage() {
        ModelAndView mav = new ModelAndView("/admin/fontawesome");
        return mav;
    }

    @GetMapping("/proces")
    public ModelAndView showProcesPage() {
        ModelAndView mav = new ModelAndView("/admin/proces");
        List<Zamowienie> zamList = zamowienieRepository.findAll();
        List<ProduktyZamowienie> prodZam = produktyZamowienieRepository.findProduktyZamowienieByStatus();
        mav.addObject("zamowienia", zamList);
        mav.addObject("produktyZamowienia", prodZam);
        return mav;
    }
}
