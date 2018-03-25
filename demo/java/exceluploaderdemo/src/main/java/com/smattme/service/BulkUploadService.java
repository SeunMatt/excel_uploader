package com.smattme.service;

import com.smattme.dao.PostRepository;
import com.smattme.dao.UserRepository;
import com.smattme.models.Post;
import com.smattme.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Seun Matt on 24-Mar-18
 */
@Service
public class BulkUploadService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    public BulkUploadService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<List<String>> uploadUserData(Map columnMap, List<List<String>> data) {

        List<List<String>> errorArray = new ArrayList<>();

        data.forEach(d -> {
            boolean res = doUserUpload(columnMap, d);
            if(!res) {
                //something went wrong while saving this data
                //so it's actually save. Let's add it to error Array
                errorArray.add(d);
            }
        });

        //so we are done, let's return the error array
        return errorArray;

    }


    private boolean doUserUpload(Map columnMap, List<String> data) {

        try {

            //extract the index of the columns we're expecting from the map
            int nameIndex = Integer.parseInt(columnMap.get("name").toString());
            int emailIndex = Integer.parseInt(columnMap.get("email").toString());
            int phoneIndex = Integer.parseInt(columnMap.get("phone_number").toString());

            //save a user
            User user = new User();
            user.setName(data.get(nameIndex));
            user.setEmail(data.get(emailIndex));
            user.setPhone(data.get(phoneIndex));
            user.setPassword("newHashedPassword");
            User savedUser = userRepository.save(user);

            //create a random post for the user
            Post post = new Post();
            post.setAuthor(savedUser);
            post.setTitle("Sample Post for user " + savedUser.getName());
            post.setPost("Set yourself on fire and the world will watch " + savedUser.toString());
            postRepository.save(post);

            //you can do all other sort of operations here
            //like send an email or do some other default things

            return  true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
    }

}
