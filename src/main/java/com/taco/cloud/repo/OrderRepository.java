package com.taco.cloud.repo;

import com.taco.cloud.model.Order;

public interface OrderRepository {

    Order save(Order order);
}
