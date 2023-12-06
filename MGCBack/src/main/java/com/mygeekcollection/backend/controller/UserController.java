package com.mygeekcollection.backend.controller;

import com.mygeekcollection.backend.entity.Item;
import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.security.jwt.JwtService;
import com.mygeekcollection.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}")
    public User getById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(path = "/profile")
    public User getProfile(@RequestHeader("Authorization") String token){
        return userService.getUserById(Integer.valueOf(jwtService.getIdFromToken(token.substring(7))));
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return userService.Update(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @GetMapping(path = "/{id}/collection")
    public List<Item> getCollectionByUser(@PathVariable("id") Integer id) {
        return userService.getAllItemsForUser(id);
    }

}
