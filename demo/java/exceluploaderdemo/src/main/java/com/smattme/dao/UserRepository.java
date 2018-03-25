package com.smattme.dao;

import com.smattme.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Seun Matt on 24-Mar-18
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
