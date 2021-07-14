package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoCoords {

    private int x;

    private int y;

    public static MongoCoords from(Coords coords) {
        return new MongoCoords(coords.getX(), coords.getY());
    }

}
