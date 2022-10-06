package com.taco.cloud.repo.jdbc;

import com.taco.cloud.model.Taco;

public interface JdbcTacoRepository {

    Taco save(Taco taco);
}
