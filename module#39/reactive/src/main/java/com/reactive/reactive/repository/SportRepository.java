package com.reactive.reactive.repository;

import com.reactive.reactive.model.Sport;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository  extends ReactiveCrudRepository<Sport, Long> {
    Mono<Sport> findByName(String name);

}
