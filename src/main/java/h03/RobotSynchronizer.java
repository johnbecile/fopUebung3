package h03;

import fopbot.Direction;
import fopbot.Robot;
import fopbot.World;

/**
 * A synchronizer for a group of robots.
 */
public class RobotSynchronizer {

    private Robot[] robots;
    private int x = -1;
    private int y = -1;
    private Direction direction = null;

    /**
     * Instantiates a new robot synchronizer.
     *
     * @param robots the robots to be synchronized
     */
    public RobotSynchronizer(Robot[] robots) {
        this.robots = robots;
    }

    /**
     * Sets x if the given x is valid.
     *
     * @param x the x
     */
    public void setX(int x) {
        if (x >= 0 && x < World.getWidth()) {
            this.x = x;
        }
    }

    /**
     * Sets y if the given y is valid.
     *
     * @param y the y
     */
    public void setY(int y) {
        if (y >= 0 && y < World.getHeight()) {
            this.y = y;
        }
    }

    /**
     * Sets direction if the given direction is valid.
     *
     * @param direction the direction
     */
    public void setDirection(Direction direction) {
        if (direction != null) {
            this.direction = direction;
        }
    }

    /**
     * Sync the robots to the target position and direction.
     */
    public void sync() {
        if (x != -1 && y != -1 && direction != null) {
            for (Robot robot : robots) {
                turnAndMove(robot);
            }
        }
    }

    private void turnAndMove(Robot robot) {
        int nowX = robot.getX();
        int nowY = robot.getY();
        Direction nowDirection = robot.getDirection();
        // the robot will move until it is at the target position and direction
        while (nowX != x || nowY != y || nowDirection != direction) {
            // the robot is at left-down to the target, turn the robot to up or right and move a step
            if (nowX < x && nowY < y) {
                if (nowDirection == Direction.DOWN || nowDirection == Direction.LEFT) {
                    robot.turnLeft();
                    robot.turnLeft();
                }
                robot.move();
            }
            // the robot is at right-down to the target, turn the robot to left or up and move a step
            if (nowX > x && nowY < y) {
                if (nowDirection == Direction.RIGHT || nowDirection == Direction.DOWN) {
                    robot.turnLeft();
                    robot.turnLeft();
                }
                robot.move();
            }
            // the robot is at right-up to the target, turn the robot to down or left and move a step
            if (nowX > x && nowY > y) {
                if (nowDirection == Direction.UP || nowDirection == Direction.RIGHT) {
                    robot.turnLeft();
                    robot.turnLeft();
                }
                robot.move();
            }
            // the robot is at left-up to the target, turn the robot to right or down and move a step
            if (nowX < x && nowY > y) {
                if (nowDirection == Direction.LEFT || nowDirection == Direction.UP) {
                    robot.turnLeft();
                    robot.turnLeft();
                }
                robot.move();
            }
            // the robot is at left to the target, turn the robot to right, if already to right, move a step
            if (nowX < x && nowY == y) {
                if (nowDirection != Direction.RIGHT) {
                    robot.turnLeft();
                } else {
                    robot.move();
                }
            }
            // the robot is at right to the target, turn the robot to left, if already to left, move a step
            if (nowX > x && nowY == y) {
                if (nowDirection != Direction.LEFT) {
                    robot.turnLeft();
                } else {
                    robot.move();
                }
            }
            // the robot is at down to the target, turn the robot to up, if already to up, move a step
            if (nowX == x && nowY < y) {
                if (nowDirection != Direction.UP) {
                    robot.turnLeft();
                } else {
                    robot.move();
                }
            }
            // the robot is at up to the target, turn the robot to down, if already to down, move a step
            if (nowX == x && nowY > y) {
                if (nowDirection != Direction.DOWN) {
                    robot.turnLeft();
                } else {
                    robot.move();
                }
            }
            // the robot is at the position of the target, change to the required direction
            if (nowX == x && nowY == y) {
                if (nowDirection != direction) {
                    robot.turnLeft();
                }
            }
            nowX = robot.getX();
            nowY = robot.getY();
            nowDirection = robot.getDirection();
        }
    }
}
