package com.taco.cloud.controller;

import com.taco.cloud.model.Order;
import com.taco.cloud.model.Taco;
import com.taco.cloud.model.TacoIngredient;
import com.taco.cloud.repo.jdbc.JdbcTacoIngredientRepository;
import com.taco.cloud.repo.jdbc.JdbcTacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order") //specifies any model objects like the order attribute that should be kept in session and available across multiple requests.
public class DesignTacoController {

    private final JdbcTacoIngredientRepository tacoIngredientRepository;

    private final JdbcTacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(JdbcTacoIngredientRepository tacoIngredientRepository, JdbcTacoRepository tacoRepository) {
        this.tacoIngredientRepository = tacoIngredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "designnensi")
    public Taco taco() {
        return new Taco();
    }

//    @ModelAttribute(name = "designnensi")
//    public TacoIngredient tacoIngredient() {
//        return new TacoIngredient();
//    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<TacoIngredient> ingredients = new ArrayList<>();
        tacoIngredientRepository.findAll().forEach(ingredients::add);

        TacoIngredient.Type[] types = TacoIngredient.Type.values();
        for (TacoIngredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), ingredients.stream()
                    .filter(it -> it.getType().name().equals(type.name()))
                    .collect(Collectors.toList()));
        }

        model.addAttribute("designnensi", new Taco());

        return "design-taco";
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order){
        System.out.println("Processing the taco " + taco);

        if(errors.hasErrors()){
            return "design-taco";
        }

        Taco savedTaco = tacoRepository.save(taco);
        order.addTaco(savedTaco); // order is kept in the session
        return "redirect:/orders/current";
    }
}
