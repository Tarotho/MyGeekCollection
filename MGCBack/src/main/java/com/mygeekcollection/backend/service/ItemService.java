package com.mygeekcollection.backend.service;

import com.mygeekcollection.backend.entity.Item;
import com.mygeekcollection.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getCollection() {
        return itemRepository.findAll();
    }

    public Optional<Item> getCollectionById(Integer id) {
        return itemRepository.findById(id);
    }

    public void saveOrUpdate (Item item){
        itemRepository.save(item);
    }

    public void delete(Integer id){
        itemRepository.deleteById(id);
    }}
