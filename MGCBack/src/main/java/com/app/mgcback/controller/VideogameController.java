package com.app.mgcback.controller;


import com.app.mgcback.dto.Mensaje;
import com.app.mgcback.dto.VideogameDto;
import com.app.mgcback.entity.Videogame;
import com.app.mgcback.service.VideogameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videogames")
@CrossOrigin(origins = "http://localhost:4200")
public class VideogameController {

    @Autowired
    VideogameService videogameService;

    @GetMapping("/all")
    public ResponseEntity<List<Videogame>> list() {
        List<Videogame> list = videogameService.getAllVideogames();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Videogame> getById(@PathVariable("id") int id) {
        if (!videogameService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe un videojuego por ese id"), HttpStatus.NOT_FOUND);
        Videogame videogame = videogameService.getById(id).get();
        return new ResponseEntity(videogame, HttpStatus.OK);
    }

    @GetMapping("/detailname/{name}")
    public ResponseEntity<Videogame> getByName(@PathVariable("name") String name) {
        if (!videogameService.existByName(name))
            return new ResponseEntity(new Mensaje("no existe un videojuego por ese nombre"), HttpStatus.NOT_FOUND);
        Videogame videogame = videogameService.getByName(name).get();
        return new ResponseEntity(videogame, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody VideogameDto videogameDto) {
        if (StringUtils.isBlank(videogameDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatiorio"), HttpStatus.BAD_REQUEST);
        if (videogameService.existByName(videogameDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);
        Videogame videogame = new Videogame(
                videogameDto.getName(),
                videogameDto.getAdditionalInfo(),
                videogameDto.getAmountExact(),
                videogameDto.getAmountProducedEstimate(),
                videogameDto.getBrand(),
                videogameDto.getBundle(),
                videogameDto.getColor(),
                videogameDto.getLimitedEdition(),
                videogameDto.getPlatform(),
                videogameDto.getRegionCode(),
                videogameDto.getReleaseType(),
                videogameDto.getReleases(),
                videogameDto.getUniqueVariation());
        videogameService.save(videogame);
        return new ResponseEntity(new Mensaje("Producto creado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody VideogameDto videogameDto) {
        if (!videogameService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if (videogameService.existByName(videogameDto.getName()) && videogameService.getByName(videogameDto.getName()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("el nombre ya existe"), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(videogameDto.getName()))
            return new ResponseEntity<>(new Mensaje("el nombre es obligatiorio"), HttpStatus.BAD_REQUEST);
        Videogame videogame = videogameService.getById(id).get();
        videogame.setName(videogameDto.getName());
        videogame.setBrand(videogameDto.getBrand());
        videogame.setBundle(videogameDto.getBundle());
        videogame.setColor(videogameDto.getColor());
        videogame.setAmountExact(videogameDto.getAmountExact());
        videogame.setPlatform(videogameDto.getPlatform());
        videogame.setReleases(videogameDto.getReleases());
        videogame.setAdditionalInfo(videogameDto.getAdditionalInfo());
        videogame.setLimitedEdition(videogameDto.getLimitedEdition());
        videogame.setRegionCode(videogameDto.getRegionCode());
        videogame.setAmountProducedEstimate(videogameDto.getAmountProducedEstimate());
        videogame.setUniqueVariation(videogameDto.getUniqueVariation());
        videogame.setReleaseType(videogameDto.getReleaseType());

        videogameService.save(videogame);
        return new ResponseEntity(new Mensaje("Producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!videogameService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        videogameService.delete(id);
        return new ResponseEntity(new Mensaje("Producto eliminado"), HttpStatus.OK);
    }
}
