package com.reactive.reactive.service;

import com.reactive.reactive.model.Sport;
import com.reactive.reactive.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class SportService {

    @Autowired
    private SportRepository sportRepository;

    public Mono<Sport> getSportByName(String name) {
        return this.sportRepository.findByName(name);
    }

    public Mono<Sport> createSport(Sport sport) {
        return this.sportRepository.save(sport);
    }

    public Mono<Sport> createSportByName(String name) {
        return this.sportRepository.findByName(name)
                .flatMap(__ -> Mono.<Sport>error(new IllegalArgumentException("Data already exist")))
                .onErrorResume(err -> Mono.just(Sport.builder().name( name + " : data already exist").build()))
                .switchIfEmpty(sportRepository.save(Sport.builder().name(name).build()));


    }
}
