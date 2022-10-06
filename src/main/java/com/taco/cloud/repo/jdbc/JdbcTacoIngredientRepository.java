package com.taco.cloud.repo.jdbc;

import com.taco.cloud.model.TacoIngredient;

public interface JdbcTacoIngredientRepository {

    Iterable<TacoIngredient> findAll();
    TacoIngredient findOne(String id);
    TacoIngredient save(TacoIngredient tacoIngredient);
}
