package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Models.Projection.ProdZamProjection;
import com.example.pzespolowe.Models.Status;
import com.example.pzespolowe.Models.Zamowienie;
import com.example.pzespolowe.Repositories.ProduktyZamowienieRepository;
import com.example.pzespolowe.Repositories.ZamowienieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/panel/admin/proces")
public class ProcesController {

    private final Logger logger = LoggerFactory.getLogger(ProcesController.class);
    private final ZamowienieRepository zamowienieRepository;
    private final ProduktyZamowienieRepository produktyZamowienieRepository;

    public ProcesController(ZamowienieRepository zamowienieRepository, ProduktyZamowienieRepository produktyZamowienieRepository) {
        this.zamowienieRepository = zamowienieRepository;
        this.produktyZamowienieRepository = produktyZamowienieRepository;
    }

    @GetMapping("/select")
    public ModelAndView selectOrder() {
        ModelAndView mav = new ModelAndView("/admin/proces/select_order");
        List<Zamowienie> zamList = zamowienieRepository.findZamowienieByStatus("IN_PROGRESS");
        mav.addObject("zamowienia", zamList);
        return mav;
    }
    @GetMapping("/prepare")
    public ModelAndView prepareProduct() {
        ModelAndView mav = new ModelAndView("/admin/proces/prepare_product");
        List<ProdZamProjection> prodList = produktyZamowienieRepository.testQ();
        mav.addObject("produktyZamowienia", prodList);
        return mav;
    }
    @GetMapping("/pack")
    public ModelAndView packProduct() {
        ModelAndView mav = new ModelAndView("/admin/proces/pack_product");
        List<Zamowienie> zamList = zamowienieRepository.findZamowienieByStatus("PREPARING");
        mav.addObject("zamowienia", zamList);
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

    @PostMapping("/toggle")
    public String testowanie(
            @ModelAttribute("zamowienia") Zamowienie zamowienie,
            @RequestParam(value = "select_order", required = false) Integer[] id,
            BindingResult bindingResult, Model model) {

        List<Integer> idki = new ArrayList<>(Arrays.asList(id));
        zamowienieRepository.findAll()
                .stream()
                .filter(zamowienie1 -> idki.contains(zamowienie1.getId()))
                .forEach(zamowienie1 -> {
                    zamowienie1.setStatus(Status.PREPARING);
                    zamowienieRepository.save(zamowienie1);
                    logger.info("Statuses update has been successful");
                });
        return "redirect:/panel/admin/proces/prepare";
    }

    @GetMapping("/finish/complete")
    public String finishOrders() {
        zamowienieRepository.findAll()
                .stream()
                .filter(zamowienie -> zamowienie.getStatus().equals(Status.PREPARING))
                .forEach(zamowienie -> {
                    zamowienie.setStatus(Status.COMPLETED);
                    Status oldStatus = zamowienie.getStatus();
                    zamowienieRepository.save(zamowienie);
                    logger.info("Statuses update has been successful[from " + oldStatus + " to " +
                            zamowienie.getStatus() + "]");
                });

        return "redirect:/panel/admin/proces";
    }
}
