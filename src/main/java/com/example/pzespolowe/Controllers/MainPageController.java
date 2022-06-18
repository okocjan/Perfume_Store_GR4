package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Dto.BasketProductDto;
import com.example.pzespolowe.Models.Projection.BasketProjection;
import com.example.pzespolowe.Models.Projection.BestsellersProjection;
import com.example.pzespolowe.Models.Projection.ProdAndZdjModel;
import com.example.pzespolowe.Services.MainPageService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainPageController implements ErrorController {
    private final MainPageService service;

    public MainPageController(MainPageService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView getMainPage(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("damskie", service.getProdukts().get(0));
        mav.addObject("meskie", service.getProdukts().get(1));
        return mav;
    }

    @GetMapping("/product/{id}")
    public ModelAndView getProdPage(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView("product_page");
        ProdAndZdjModel toRet = service.getProdukt(id);

        if (toRet != null) {
            mav.addObject("produkt", toRet);
            return mav;
        } else {
            return new ModelAndView("/admin/error");
        }
    }

    @GetMapping("/bestsellers")
    public ModelAndView getBestsellersPage() {
        ModelAndView mav = new ModelAndView("bestsellers");
        List<BestsellersProjection> bestsellers = service.getBestsellers();

        mav.addObject("bestsellers", bestsellers);

        return mav;
    }

    @GetMapping("/perfume/female")
    public ModelAndView getFemalePerfume() {
        ModelAndView mav = new ModelAndView("female-perfume-page");
        mav.addObject("damskie", service.getProdukts().get(0));

        return mav;
    }
    @GetMapping("/perfume/male")
    public ModelAndView getMalePerfume() {
        ModelAndView mav = new ModelAndView("male-perfume-page");
        mav.addObject("meskie", service.getProdukts().get(1));

        return mav;
    }

    @GetMapping("/basket")
    public ModelAndView getBasket(){
        ModelAndView mav = new ModelAndView("basket");

        BasketProductDto basket = service.getBasketItems();
        List<BasketProjection> lista = basket.getBasketItems();
        double finalPrice = basket.getFinalPrice();

        mav.addObject("basketItems", lista);
        mav.addObject("finPrice", finalPrice);

        return mav;
    }

    @PostMapping("/add-to-basket")
    public String addToBasket(@RequestParam(value = "prodId") int id) {
        service.addProductToBasket(id);
        return "redirect:/";
    }

    @PostMapping("/remove-from-basket")
    public String removeFromBasket(@RequestParam(value = "itemId") int id) {
        service.removeProductFromBasket(id);
        return "redirect:/basket";
    }

    @PostMapping("/basket/finalize")
    public String finalizeOrder(@RequestParam(value = "pr_id") Integer[] ids,
                                @RequestParam(value = "final_price") Double finalPrice) {

        List<Integer> prodIds = Arrays.asList(ids);
        service.finalizeOrder(1, prodIds, finalPrice);

        return "redirect:/basket";
    }

    @RequestMapping("/error")
    public ModelAndView handleError() {
        ModelAndView mav = new ModelAndView("error");

        return mav;
    }
}
