package com.mygeekcollection.backend.service;

import com.mygeekcollection.backend.entity.Item;
import com.mygeekcollection.backend.entity.User;
import com.mygeekcollection.backend.entity.Videogame;
import com.mygeekcollection.backend.repository.ItemRepository;
import com.mygeekcollection.backend.repository.UserRepository;
import com.mygeekcollection.backend.repository.VideogameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private PasswordEncoder encrypter;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void saveOrUpdateUser (User user){
        user.setPassword(encrypter.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public User addItemToUser(Integer userId, Item newItem, Integer vgId) {
        // Obtener el usuario existente
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el videojueg existente
        Videogame videogame = videogameRepository.findById(vgId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Establecer la relación bidireccional
        newItem.setUser(user);
        newItem.setVideogame(videogame);

        user.getCollection().add(newItem);

        // Guardar los cambios en la base de datos
        userRepository.save(user);

        return user;
    }

    public List<Item> getAllItemsForUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return user.getCollection();
    }

    public Optional<User> login(String usernameOrEmail, String password) throws UsernameNotFoundException{
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + usernameOrEmail));
        if (encrypter.matches(password, user.getPassword())) {
            // Contraseña correcta
            return Optional.of(user);
        } else {
            // Contraseña incorrecta
            throw new UsernameNotFoundException("Credenciales incorrectas para el usuario: " + usernameOrEmail);
        }
    }

}
