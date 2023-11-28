package com.mygeekcollection.backend.repository;

import com.mygeekcollection.backend.entity.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame,Integer> {
}
