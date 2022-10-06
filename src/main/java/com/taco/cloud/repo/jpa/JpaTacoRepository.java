package com.taco.cloud.repo.jpa;

import com.taco.cloud.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface JpaTacoRepository extends CrudRepository<Taco, Long> {
}
