package com.mygeekcollection.backend.controller;


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
        return videogameService.getUsers();
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
}
