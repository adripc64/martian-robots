package org.adripc64.guidesmiths.challenges.martianrobots.simulator;

import org.adripc64.guidesmiths.challenges.martianrobots.domain.exception.RobotLostException;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.factory.PlanetFactory;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Coords;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Planet;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Position;
import org.adripc64.guidesmiths.challenges.martianrobots.domain.model.Robot;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MartianRobotsSimulator {

    public static void main(String[] args) {
        simulate(System.in, System.out);
    }


    public static void simulate(InputStream input, OutputStream output) {

        Scanner scanner = new Scanner(input);
        PrintStream printStream = new PrintStream(output);

        if (scanner.hasNextLine()) {
            String maxCoordinates = scanner.nextLine();
            Planet planet = PlanetFactory.createPlanetWithMaxCoordinates(Coords.parse(maxCoordinates));

            while (scanner.hasNextLine()) {
                String robotPosition = scanner.nextLine();
                String robotInstructions = scanner.nextLine();

                Robot robot = planet.getRobotFactory().createRobot(Position.parse(robotPosition), robotInstructions);
                try {
                    robot.execute();
                } catch (RobotLostException e) {
                    // do nothing
                } finally {
                    Position position = robot.getPosition();
                    String result = String.format("%d %d %s",
                            position.getCoords().getX(),
                            position.getCoords().getY(),
                            position.getOrientation().name()) + (robot.isLost() ? " LOST" : ""
                    );
                    printStream.println(result);
                }
            }

        }

    }

}
