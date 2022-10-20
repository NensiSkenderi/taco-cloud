package com.taco.cloud.config.data;

import com.taco.cloud.model.TacoIngredient;
import com.taco.cloud.repo.jpa.JpaTacoIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class LoadInitialData {

    @Autowired
    private JpaTacoIngredientRepository tacoIngredientRepository;

    @PostConstruct
    private void postConstruct() {
        TacoIngredient ingredient1 = new TacoIngredient("1", "Feta Tortilla", TacoIngredient.Type.CHEESE);
        TacoIngredient ingredient2 = new TacoIngredient("2", "Corn Tortilla", TacoIngredient.Type.VEGGIES);
        TacoIngredient ingredient3 = new TacoIngredient("3", "Beans Tortilla", TacoIngredient.Type.VEGGIES);
        TacoIngredient ingredient4 = new TacoIngredient("4", "Lettuce Tortilla", TacoIngredient.Type.VEGGIES);
        TacoIngredient ingredient5 = new TacoIngredient("5", "Flour Tortilla", TacoIngredient.Type.WRAP);
        TacoIngredient ingredient6 = new TacoIngredient("6", "Tofu Tortilla", TacoIngredient.Type.PROTEIN);

        tacoIngredientRepository.saveAll(Arrays.asList(ingredient1, ingredient2, ingredient3, ingredient4,
                ingredient5, ingredient6));
    }
}
