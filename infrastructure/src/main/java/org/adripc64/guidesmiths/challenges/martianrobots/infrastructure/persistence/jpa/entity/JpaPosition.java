package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Orientation;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JpaPosition {

    @Embedded
    private JpaCoords coords;

    @Enumerated(value = EnumType.STRING)
    private Orientation orientation;

    public static JpaPosition from(Position position) {
        return new JpaPosition(JpaCoords.from(position.getCoords()), position.getOrientation());
    }

}
