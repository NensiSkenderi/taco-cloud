package com.taco.cloud.repo;

import com.taco.cloud.model.TacoIngredient;

public interface TacoIngredientRepository {

    Iterable<TacoIngredient> findAll();
    TacoIngredient findOne(String id);
    TacoIngredient save(TacoIngredient tacoIngredient);
}
