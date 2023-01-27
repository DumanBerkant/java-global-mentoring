package com.reactive.reactive.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Value
@Table("sports")
public class Sport {

    @Id
    private Long id;

    private String name;

}
