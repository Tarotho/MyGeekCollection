package com.mygeekcollection.backend.service;

import com.mygeekcollection.backend.entity.Videogame;
import com.mygeekcollection.backend.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideogameService {

    @Autowired
    VideogameRepository videogameRepository;

    public List<Videogame> getUsers() {
        return videogameRepository.findAll();
    }

    public Optional<Videogame> getUserById(Integer id) {
        return videogameRepository.findById(id);
    }

    public void saveOrUpdate(Videogame videogame) {
        videogameRepository.save(videogame);
    }

    public void delete(Integer id) {
        videogameRepository.deleteById(id);
    }
}
