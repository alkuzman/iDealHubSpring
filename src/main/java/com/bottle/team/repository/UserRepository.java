package com.bottle.team.repository;

import com.bottle.team.model.authentication.User;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by PC on 09/10/2016.
 */
public interface UserRepository extends GraphRepository<User> {
}
