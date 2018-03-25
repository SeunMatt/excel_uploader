package com.smattme.dao;

import com.smattme.models.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Seun Matt on 24-Mar-18
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
}
