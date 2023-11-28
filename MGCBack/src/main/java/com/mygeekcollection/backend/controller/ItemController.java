package com.mygeekcollection.backend.controller;

import com.mygeekcollection.backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/collections")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

}
