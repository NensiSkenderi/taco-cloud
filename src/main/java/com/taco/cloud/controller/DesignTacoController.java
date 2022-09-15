package com.taco.cloud.controller;

import com.taco.cloud.model.Taco;
import com.taco.cloud.model.TacoIngredient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<TacoIngredient> tacoIngredientList = Arrays.asList(
          new TacoIngredient(1, "taco 1", TacoIngredient.Type.VEGGIE),
          new TacoIngredient(2, "taco 2", TacoIngredient.Type.WRAP),
          new TacoIngredient(3, "taco 3", TacoIngredient.Type.CHEESE),
          new TacoIngredient(4, "taco 4", TacoIngredient.Type.VEGGIE),
          new TacoIngredient(5, "taco 5", TacoIngredient.Type.PROTEIN),
          new TacoIngredient(6, "taco 6", TacoIngredient.Type.PROTEIN),
          new TacoIngredient(7, "taco 7", TacoIngredient.Type.VEGGIE),
          new TacoIngredient(8, "taco 8", TacoIngredient.Type.WRAP)
        );

        TacoIngredient.Type[] types = TacoIngredient.Type.values();

        for(TacoIngredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(tacoIngredientList, type));
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
