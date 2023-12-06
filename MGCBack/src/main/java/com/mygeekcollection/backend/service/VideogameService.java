package com.mygeekcollection.backend.service;

import com.mygeekcollection.backend.entity.Item;
import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.entity.Videogame;
import com.mygeekcollection.backend.repository.UserRepository;
import com.mygeekcollection.backend.repository.VideogameRepository;
import com.mygeekcollection.backend.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideogameService {

    private final VideogameRepository videogameRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public List<Videogame> getVideogames() {
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

    public User addItemToUser(String token, Item newItem, Integer vgId) {
        User user = userRepository.findById(Integer.valueOf(jwtService.getIdFromToken(token.substring(7)))).orElseThrow();
        Videogame videogame = videogameRepository.findById(vgId)
                .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));

        newItem.setUser(user);
        newItem.setVideogame(videogame);

        user.getCollection().add(newItem);

        userRepository.save(user);

        return user;
    }
}
