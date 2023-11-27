package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class Game {
    private int id;
    private String name;
    private Date releaseDate;
    private int rating;
    private float cost;
    private String description;
}
