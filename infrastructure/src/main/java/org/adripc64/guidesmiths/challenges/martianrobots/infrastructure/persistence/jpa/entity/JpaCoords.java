package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JpaCoords {

    private int x;

    private int y;

    public static JpaCoords from(Coords coords) {
        return new JpaCoords(coords.getX(), coords.getY());
    }

}
