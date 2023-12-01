package com.mygeekcollection.backend.controller;

import com.mygeekcollection.backend.entity.Item;
import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> getById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        userService.Update(user);
        return user;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @GetMapping(path = "/{id}/collection")
    public List<Item> getCollectionByUser(@PathVariable("id")Integer id){
        return userService.getAllItemsForUser(id);
    }

}
