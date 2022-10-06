package com.taco.cloud.repo.jdbc.impl;

import com.taco.cloud.model.TacoIngredient;
import com.taco.cloud.repo.jdbc.JdbcTacoIngredientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTacoIngredientRepositoryImpl implements JdbcTacoIngredientRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTacoIngredientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<TacoIngredient> findAll() {
        return jdbcTemplate.query("select id, name, type from Taco_Ingredient",
                this::mapRow);
    }

    @Override
    public TacoIngredient findOne(String id) {
        return jdbcTemplate.queryForObject("select id, name, type from Taco_Ingredient where id =?",
                this::mapRow, id);
    }

    @Override
    public TacoIngredient save(TacoIngredient tacoIngredient) {
        jdbcTemplate.update("insert into Taco_Ingredient (id, name, type) values (?, ?, ?)",
        tacoIngredient.getId(),
        tacoIngredient.getName(),
        tacoIngredient.getType().toString());

        return  tacoIngredient;
    }

    private TacoIngredient mapRow(ResultSet rs, int rows) throws SQLException {
        return new TacoIngredient(
                rs.getString("id"),
                rs.getString("name"),
                TacoIngredient.Type.valueOf(rs.getString("type"))
        );
    }
}
