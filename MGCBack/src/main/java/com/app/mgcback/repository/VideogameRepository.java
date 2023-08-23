package com.app.mgcback.repository;

import com.app.mgcback.entity.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Integer> {
    Optional<Videogame> findByName(String name);

    boolean existsByName(String name);
}
