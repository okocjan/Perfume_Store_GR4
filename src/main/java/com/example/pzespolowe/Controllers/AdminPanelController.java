package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Dto.SaveProductDto;
import com.example.pzespolowe.Models.*;
import com.example.pzespolowe.Models.Projection.MagazynProjection;
import com.example.pzespolowe.Repositories.*;
import com.example.pzespolowe.Services.ProduktService;
import com.example.pzespolowe.Util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/panel/admin")
public class AdminPanelController {
    private final ProduktService service;
    private final ProduktRepository repository;
    private final ZamowienieRepository zamowienieRepository;

    private final MagazynRepository magazynRepository;
    private final ZdjeciaProdRepository zdjeciaProdRepository;

    private final ProduktyZamowienieRepository produktyZamowienieRepository;

    public AdminPanelController(ProduktService service, ProduktRepository repository, ZamowienieRepository zamowienieRepository, MagazynRepository magazynRepository, ZdjeciaProdRepository zdjeciaProdRepository, ProduktyZamowienieRepository produktyZamowienieRepository) {
        this.service = service;
        this.repository = repository;
        this.zamowienieRepository = zamowienieRepository;
        this.magazynRepository = magazynRepository;
        this.zdjeciaProdRepository = zdjeciaProdRepository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
    }

    @GetMapping({"/dashboard", "/", ""})
    public ModelAndView showAdminPage() {
        ModelAndView mav = new ModelAndView("/admin/dashboard");
        return mav;
    }

    @GetMapping("/warehouse")
    public ModelAndView getMagazyn() {
        ModelAndView mav = new ModelAndView("/admin/warehouse");
        List<MagazynProjection> listaMag = magazynRepository.getMagazynQuantity();

        mav.addObject("magazyn", listaMag);

        return mav;
    }

    @GetMapping("/404")
    public ModelAndView showErrorPage() {
        ModelAndView mav = new ModelAndView("/admin/error");
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
    public String addProductToRepo(@ModelAttribute SaveProductDto saveProductDto,
                                   @RequestParam(value = "imagePath", required = false) MultipartFile multipartFile) throws IOException {
        System.out.println(saveProductDto);
        Produkt produkt = new Produkt();
        produkt.setCena(saveProductDto.getPrice());
        produkt.setNazwaProd(saveProductDto.getName());
        produkt.setOpis(saveProductDto.getDescription());
        produkt.setPojemnosc(saveProductDto.getCapacity());
        produkt.setRodzaj(saveProductDto.getType());

        Produkt savedProd = repository.save(produkt);
        System.out.println(savedProd.getId());

        zdjeciaProdRepository.save(new ZdjeciaProd(savedProd, "../assets/img/" + savedProd.getId() + "/" +
                saveProductDto.getMultipartFile().getOriginalFilename()));
        magazynRepository.save(new Magazyn(savedProd, saveProductDto.getQuantity()));

        String fileName = StringUtils.cleanPath(saveProductDto.getMultipartFile().getOriginalFilename());

        String uploadDir = "src/main/resources/assets/img/" + savedProd.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, saveProductDto.getMultipartFile());
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
