# Martian Robots Simulator

The Martian Robots Simulator is a simple Java program that reads from stdin and outputs results to stdout.

## Overview

### Input

The first line of input is the upper-right coordinates of the rectangular world, the lower-left coordinates are assumed to be 0, 0.

The remaining input consists of a sequence of robot positions and instructions (two lines per robot). A position consists of two integers specifying the initial coordinates of the robot and an orientation (N, S, E, W), all separated by whitespace on one line. A robot instruction is a string of the letters "L", "R", and "F" on one line.

Each robot is processed sequentially, i.e., finishes executing the robot instructions before the next robot begins execution.

**Sample**

```indicates
5 3
1 1 E
RFRFRFRF
3 2 N
FRRFLLFFRRFLL
0 3 W
LLFFFLFLFL
```

### Output

For each robot position/instruction in the input, the output indicates the final grid position and orientation of the robot. If a robot falls off the edge of the grid the word "LOST" is printed after the position and orientation.

**Sample**

```
1 1 E
3 3 N LOST
2 3 S
```

## Usage

The simulator can be executed (from the root directory) with Gradle, like:

``` 
./gradlew -q :simulator:run < input.txt
```

Or executing the built JAR:

``` 
java -jar simulator/build/libs/simulator-{version}-all.jar < input.txt
```

**Example:**

``` 
java -jar simulator/build/libs/simulator-1.0.0-all.jar < input.txt 
1 1 E
3 3 N LOST
2 3 S
```

## Download

You can download the released artifacts from GitHub Packages:

https://github.com/adripc64/martian-robots/packages/898575
