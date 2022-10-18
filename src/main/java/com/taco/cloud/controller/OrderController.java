package com.taco.cloud.controller;

import com.taco.cloud.config.data.OrderConfigProperties;
import com.taco.cloud.model.Order;
import com.taco.cloud.model.User;
import com.taco.cloud.repo.jdbc.JdbcOrderRepository;
import com.taco.cloud.repo.jpa.JpaOrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private JpaOrderRepository orderRepository;

    private OrderConfigProperties orderConfigProperties;

    public OrderController(JpaOrderRepository orderRepository, OrderConfigProperties orderConfigProperties) {
        this.orderRepository = orderRepository;
        this.orderConfigProperties = orderConfigProperties;
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

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model){
        Pageable p = PageRequest.of(0,orderConfigProperties.getPageSize());
        model.addAttribute("orders-nensi",
                orderRepository.findByUserOrderByCreatedAtDesc(user, p));

        return "order-list";
    }
}
