package com.example.pzespolowe.Controllers;

import com.example.pzespolowe.Services.MainPageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {
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
}
