package com.taco.cloud.controller;

import com.taco.cloud.model.Order;
import com.taco.cloud.model.Taco;
import com.taco.cloud.model.TacoIngredient;
import com.taco.cloud.repo.TacoIngredientRepository;
import com.taco.cloud.repo.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order") //pecifies any model objects like the order attribute that should be kept in session and available across multiple requests.
public class DesignTacoController {

    private final TacoIngredientRepository tacoIngredientRepository;

    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(TacoIngredientRepository tacoIngredientRepository, TacoRepository tacoRepository) {
        this.tacoIngredientRepository = tacoIngredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "designnensi")
    public TacoIngredient designnensi() {
        return new TacoIngredient();
    }

    @GetMapping
    public String showDesignForm(Model model){


        List<TacoIngredient> ingredients = new ArrayList<>();
        tacoIngredientRepository.findAll().forEach(ingredients::add);

        TacoIngredient.Type[] types = TacoIngredient.Type.values();

        for(TacoIngredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("designnensi", designnensi());
        return "design-taco";
    }

    private List<TacoIngredient> filterByType(List<TacoIngredient> tacoIngredientList, TacoIngredient.Type type){
        return tacoIngredientList
                .stream()
                .filter(nensi -> nensi.getType().equals(type))
                //filter returns a stream consisting of the elements of this stream that match the given predicate.
                .collect(Collectors.toList());
    }


    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order){
        System.out.println("Processing the taco " + taco);

        if(errors.hasErrors()){
            return "design-taco";
        }

        Taco savedTaco = tacoRepository.save(taco);
        order.addDesign(savedTaco); // order is kept in the session
        return "redirect:/orders/current";
    }
}
