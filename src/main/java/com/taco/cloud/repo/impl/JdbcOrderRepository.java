package com.taco.cloud.repo.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taco.cloud.model.Order;
import com.taco.cloud.model.Taco;
import com.taco.cloud.repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) { //JdbcTemplate jdbcTemplate injected thru constructor
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setCreatedAt(new Date());
        long orderId = saveOrder(order);
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        for(Taco taco: tacos){
            saveTacoToOrder(taco, orderId);
        }
        return order;
    }

    private long saveOrder(Order order) {
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("createdAt", order.getCreatedAt());

        /*
        Order has several properties, and those properties all share the
        same name with the columns that they’re going into.
        Because of that, in saveOrderDetails(), I’ve decided to
        use Jackson’s ObjectMapper and its convertValue() method
        to convert an Order into a Map.[1] Once the Map is created,
        you’ll set the placedAt entry to the value of the Order
        object’s placedAt property. This is necessary because
        ObjectMapper would otherwise convert the Date property into a long,
        which is incompatible with the placedAt field in the Taco_Order table.
         */


        return orderInserter.executeAndReturnKey(values).longValue();
    }

    /*
    execute() and executeAndReturnKey().
    Both accept a Map<String, Object>, where the map keys
    correspond to the column names in the table the data is inserted into.
    The map values are inserted into those columns.
     */

    private void saveTacoToOrder(Taco taco, long orderId){
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values); //execute() does the insert
    }
}
