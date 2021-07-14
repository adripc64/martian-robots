package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class JpaRobot {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="coords.x", column=@Column(name="init_x")),
            @AttributeOverride(name="coords.y", column=@Column(name="init_y")),
            @AttributeOverride(name="orientation", column=@Column(name="init_orientation"))
    })
    private JpaPosition initPosition;

    @Embedded
    private JpaPosition position;

    private String instructions;

    private boolean lost;

    public static JpaRobot from(Robot robot) {
        JpaPosition initPosition = JpaPosition.from(robot.getInitPosition());
        JpaPosition position = JpaPosition.from(robot.getPosition());
        return new JpaRobot(initPosition, position, robot.getInstructions(), robot.isLost());
    }

}
