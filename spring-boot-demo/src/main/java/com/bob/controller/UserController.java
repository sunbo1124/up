package com.bob.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bob.dao.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
        LOG.info("view param id={}", id);
        User user = new User();
        user.setId(id);
        user.setName("bob");
        return user;
    }

}
