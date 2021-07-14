package org.adripc64.guidesmiths.challenges.martianrobots.infrastructure.persistence.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;

import javax.persistence.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoRobot {

    private MongoPosition initPosition;

    @Embedded
    private MongoPosition position;

    private String instructions;

    private boolean lost;

    public static MongoRobot from(Robot robot) {
        return new MongoRobot(MongoPosition.from(robot.getInitPosition()), MongoPosition.from(robot.getPosition()), robot.getInstructions(), robot.isLost());
    }

}
