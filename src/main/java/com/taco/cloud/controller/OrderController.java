package com.taco.cloud.controller;

import com.taco.cloud.model.Order;
import com.taco.cloud.repo.jdbc.JdbcOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private JdbcOrderRepository orderRepository;

    public OrderController(JdbcOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderFrom(){
        return "order-form";
    }

    @PostMapping
    public String processOrder(@Valid  Order order, Errors errors, SessionStatus sessionStatus){
        if(errors.hasErrors()){
            return "order-form";
        }
        System.out.println("Order submitted " + order);
        orderRepository.save(order);
        sessionStatus.setComplete(); //Once the order is saved, we dont need it in a session anymore.
        return "redirect:/";
    }
}
