package com.taco.cloud.repo.jpa;

import com.taco.cloud.model.Order;
import com.taco.cloud.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface JpaOrderRepository extends CrudRepository<Order, Long> {

}
