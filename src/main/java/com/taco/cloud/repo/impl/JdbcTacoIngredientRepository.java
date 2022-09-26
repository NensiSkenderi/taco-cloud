package com.taco.cloud.repo.impl;

import com.taco.cloud.model.TacoIngredient;
import com.taco.cloud.repo.TacoIngredientRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcTacoIngredientRepository implements TacoIngredientRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTacoIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<TacoIngredient> findAll() {
        return jdbcTemplate.query("select id, name, type from TacoIngredient",
                this::mapRow);
    }

    @Override
    public TacoIngredient findOne(String id) {
        return jdbcTemplate.queryForObject("select id, name, type from TacoIngredient where id =?",
                this::mapRow, id);
    }

    @Override
    public TacoIngredient save(TacoIngredient tacoIngredient) {
        jdbcTemplate.update("insert into TacoIngredient (id, name, type) values (?, ?, ?)",
        tacoIngredient.getId(),
        tacoIngredient.getName(),
        tacoIngredient.getType().toString());

        return  tacoIngredient;
    }

    private TacoIngredient mapRow(ResultSet rs, int rows) throws SQLException {
        return new TacoIngredient(
                rs.getInt("id"),
                rs.getString("name"),
                TacoIngredient.Type.valueOf(rs.getString("type"))
        );
    }
}
