package com.taco.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {


    @GetMapping
    public String orderFrom(Model model){
        model.addAttribute("order", new Order());
        return "order-form"
    }
}
