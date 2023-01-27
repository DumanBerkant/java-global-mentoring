package com.reactive.reactive.controller;

import com.reactive.reactive.model.Sport;
import com.reactive.reactive.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sport/")
public class SportController {

    @Autowired
    private SportService sportService;


    @GetMapping(path = "{name}")
    public Mono<Sport> getSportByName(@PathVariable String name) {
        return this.sportService.getSportByName(name);
    }

    @PostMapping(path = "{name}")
    public Mono<Sport> createSport(@PathVariable String name) {
        return this.sportService.createSportByName(name);
    }
}
