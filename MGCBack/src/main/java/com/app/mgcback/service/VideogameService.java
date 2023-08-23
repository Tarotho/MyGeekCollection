package com.app.mgcback.service;


import com.app.mgcback.entity.Videogame;
import com.app.mgcback.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideogameService {

    @Autowired
    VideogameRepository videogameRepository;

    public List<Videogame> getAllVideogames() {
        return videogameRepository.findAll();
    }

    public Optional<Videogame> getById(int id) {
        return videogameRepository.findById(id);
    }

    public Optional<Videogame> getByName(String name) {
        return videogameRepository.findByName(name);
    }

    public void save(Videogame videogame) {
        videogameRepository.save(videogame);
    }

    public void delete(int id) {
        videogameRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return videogameRepository.existsById(id);
    }

    public boolean existByName(String name) {
        return videogameRepository.existsByName(name);
    }

}
