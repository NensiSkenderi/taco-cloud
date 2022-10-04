package com.taco.cloud.repo;

import com.taco.cloud.model.Order;
import org.springframework.stereotype.Repository;

public interface OrderRepository {

    Order save(Order order);
}
