package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class JpaPlanet {

    @Id
    private String id;

    private int width;

    private int height;

    @ElementCollection
    private List<JpaCoords> scents = new ArrayList<>();

    @ElementCollection
    private List<JpaRobot> robots = new ArrayList<>();

}
