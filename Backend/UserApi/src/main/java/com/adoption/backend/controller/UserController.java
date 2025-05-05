package com.adoption.backend.controller;

import com.adoption.backend.model.User;
import com.adoption.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/all-users")
    public List<User> getAllUser()
    {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/users/{id}")
    public User getUser(@PathVariable int id)
    {
        return userService.getUser(id);
    }

    @RequestMapping(value="/add-user", method= RequestMethod.POST)
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @RequestMapping(value="/update-user", method=RequestMethod.PUT)
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }
    @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public void deleteUser(@PathVariable int id)
    {
        userService.deleteUser(id);
    }
}
