package com.mygeekcollection.backend.controller;


import com.mygeekcollection.backend.entity.Item;
import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.entity.Videogame;
import com.mygeekcollection.backend.service.VideogameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/vg")
@RequiredArgsConstructor
public class VideogameController {

    private final VideogameService videogameService;

    @GetMapping
    public List<Videogame> getAll() {
        return videogameService.getVideogames();
    }

    @GetMapping(path = "/{id}")
    public Optional<Videogame> getById(@PathVariable("id") Integer id) {
        return videogameService.getUserById(id);
    }

    @PostMapping
    public void save(@RequestBody Videogame videogame) {
        videogameService.saveOrUpdate(videogame);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        videogameService.delete(id);
    }

    @PostMapping("/{id}")
    public User addItemToUser(@RequestHeader("Authorization") String token, @RequestBody Item item, @PathVariable("id")Integer id) {
        return videogameService.addItemToUser(token, item,id);
    }
}
