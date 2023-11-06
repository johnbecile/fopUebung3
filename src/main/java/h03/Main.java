package h03;

import fopbot.*;
import h03.robots.ChessBoardRobot;
import h03.robots.MultiFamilyRobot;
import h03.robots.RGBRobot;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.random;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        int numberOfRows = 10;
        int numberOfColumns = 10;

        World.setSize(numberOfColumns, numberOfRows);
        World.getGlobalWorld().setDrawTurnedOffRobots(false);
        World.setVisible(true);
        World.setDelay(100);

        // Multi-Family Robot Test
        int familyNumber = 3;
        RobotFamily[] families = new RobotFamily[familyNumber];
        for (int j = 0; j < families.length; j++) {
            int randomFamily = ThreadLocalRandom.current().nextInt(0, RobotFamily.values().length);
            boolean isDuplicate = false;
            do {
                families[j] = RobotFamily.values()[randomFamily];
                for (int k = 0; k < j; k++) {
                    if (families[k] == families[j]) {
                        isDuplicate = true;
                        break;
                    }
                }
            } while (isDuplicate);
        }

        Robot multiFamilyRobot = new MultiFamilyRobot(ThreadLocalRandom.current().nextInt(0, World.getWidth()),
            ThreadLocalRandom.current().nextInt(0, World.getHeight()), families);
        for (int j = 0; j < families.length + 5; j++) {
            if (multiFamilyRobot.isFrontClear()) {
                multiFamilyRobot.move();
            } else {
                multiFamilyRobot.turnLeft();
            }
        }


        // RGB Robots Test
        Robot rgbRobot = new RGBRobot(ThreadLocalRandom.current().nextInt(0, World.getWidth()),
            ThreadLocalRandom.current().nextInt(0, World.getHeight()), false);
        Robot rgbRobotInverted = new RGBRobot(ThreadLocalRandom.current().nextInt(0, World.getWidth()),
            ThreadLocalRandom.current().nextInt(0, World.getHeight()), true);
        Robot[] rgbRobots = new Robot[]{rgbRobot, rgbRobotInverted};
        for (int i = 0; i < 5; i++) {
            if (rgbRobot.isFrontClear()) {
                rgbRobot.move();
            } else {
                rgbRobot.turnLeft();
            }
            if (rgbRobotInverted.isFrontClear()) {
                rgbRobotInverted.move();
            } else {
                rgbRobotInverted.turnLeft();
            }
        }

        // Chess Board Robot Test
        Robot[] chessBoardRobots = new Robot[4];
        int leftX = ThreadLocalRandom.current().nextInt(1, World.getWidth());
        int leftY = ThreadLocalRandom.current().nextInt(0, World.getHeight() - 2);
        chessBoardRobots[0] = new ChessBoardRobot(leftX, leftY);
        chessBoardRobots[1] = new ChessBoardRobot(leftX + 1, leftY);
        chessBoardRobots[2] = new ChessBoardRobot(leftX, leftY - 1);
        chessBoardRobots[3] = new ChessBoardRobot(leftX + 1, leftY - 1);
        for (Robot chessBoardRobot : chessBoardRobots) {
            chessBoardRobot.move();
        }
        for (Robot chessBoardRobot : chessBoardRobots) {
            chessBoardRobot.move();
        }

        // sync test
        RobotSynchronizer syncMultiFamily = new RobotSynchronizer(new Robot[]{multiFamilyRobot});
        RobotSynchronizer syncRGB = new RobotSynchronizer(rgbRobots);
        RobotSynchronizer syncChess = new RobotSynchronizer(chessBoardRobots);
        syncMultiFamily.setX(5);
        syncMultiFamily.setY(5);
        syncMultiFamily.setDirection(Direction.RIGHT);
        syncMultiFamily.sync();
        syncRGB.setX(5);
        syncRGB.setY(5);
        syncRGB.setDirection(Direction.RIGHT);
        syncRGB.sync();
        syncChess.setX(5);
        syncChess.setY(5);
        syncChess.setDirection(Direction.RIGHT);
        syncChess.sync();
    }
}


