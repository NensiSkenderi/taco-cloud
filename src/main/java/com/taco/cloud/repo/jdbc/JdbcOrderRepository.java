package com.taco.cloud.repo.jdbc;

import com.taco.cloud.model.Order;

public interface JdbcOrderRepository {

    Order save(Order order);
}
