package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Orientation;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoPosition {

    private MongoCoords coords;

    private Orientation orientation;

    public static MongoPosition from(Position position) {
        return new MongoPosition(MongoCoords.from(position.getCoords()), position.getOrientation());
    }

}
