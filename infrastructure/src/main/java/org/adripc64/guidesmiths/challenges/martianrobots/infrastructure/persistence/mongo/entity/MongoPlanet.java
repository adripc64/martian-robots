package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class MongoPlanet {

    @Id
    private String id;

    private int width;

    private int height;

    private List<MongoCoords> scents = new ArrayList<>();

    private List<MongoRobot> robots = new ArrayList<>();

}
