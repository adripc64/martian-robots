package org.adripc64.guidesmiths.challenges.martianrobots.simulator;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class MartianRobotsSimulatorTest {

    @Test
    public void testSimulator() throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();

        InputStream input = classLoader.getResourceAsStream("sample-input.txt");
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        MartianRobotsSimulator.simulate(input, output);

        String expectedOutput = new String(classLoader.getResourceAsStream("sample-output.txt").readAllBytes(), StandardCharsets.UTF_8);
        assertEquals(expectedOutput, output.toString());

    }

}
