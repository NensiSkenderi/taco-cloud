package com.taco.cloud.controller;

import com.taco.cloud.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {


    @GetMapping("/current")
    public String orderFrom(Model model){
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping
    public String processOrder(@Valid  Order order, Errors errors){
        if(errors.hasErrors()){
            return "order-form";
        }
        System.out.println("Order submitted " + order);
        return "redirect:";
    }
}
