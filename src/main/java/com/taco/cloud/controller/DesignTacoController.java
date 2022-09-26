package com.taco.cloud.controller;

import com.taco.cloud.model.Taco;
import com.taco.cloud.model.TacoIngredient;
import com.taco.cloud.repo.TacoIngredientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final TacoIngredientRepository tacoIngredientRepository;

    public DesignTacoController(TacoIngredientRepository tacoIngredientRepository) {
        this.tacoIngredientRepository = tacoIngredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){
        List<TacoIngredient> ingredients = new ArrayList<>();
        tacoIngredientRepository.findAll().forEach( ingredient -> ingredients.add(ingredient));

        TacoIngredient.Type[] types = TacoIngredient.Type.values();

        for(TacoIngredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("designnensi", new TacoIngredient());
        return "design-taco";
    }


    /**
     * @param tacoIngredientList
     * @param type
     * @return
     */
    private List<TacoIngredient> filterByType(List<TacoIngredient> tacoIngredientList, TacoIngredient.Type type){
        return tacoIngredientList
                .stream()
                .filter(nensi -> nensi.getType().equals(type))
                //filter returns a stream consisting of the elements of this stream that match the given predicate.
                .collect(Collectors.toList());
    }


    @PostMapping
    public String processDesign(Taco taco){
        System.out.println("Processing the taco " + taco);
        return "redirect:/orders/current";
    }
}
