package h03.robots;

import fopbot.Robot;
import fopbot.RobotFamily;

/**
 * A field entity that represents a multifamily robot on a graphical user interface.
 */
public class MultiFamilyRobot extends Robot {

    private RobotFamily[] families;
    private int currentFamilyIndex;

    /**
     * Constructs and initializes a robot at the specified {@code (x,y)} location with the viewing
     * direction {@code UP} and 0 coins. The family of this robot is the first family in the given
     * list of families.
     *
     * @param x        the X coordinate of the newly constructed robot
     * @param y        the Y coordinate of the newly constructed robot
     * @param families the multifamily of this robot
     */
    public MultiFamilyRobot(int x, int y, RobotFamily[] families) {
        super(x, y, families[0]);
        this.families = families;
    }

    /**
     * Exchanges the family of this robot with the next family in the list of families. If this robot
     * is currently in the last family of the list, select the first family of the list.
     */
    public void exchange() {
        currentFamilyIndex = (currentFamilyIndex + 1) % families.length;
        setRobotFamily(families[currentFamilyIndex]);
    }

    /**
     * Moves the robot one field forward and exchanges the family of this robot.
     */
    public void move() {
        super.move();
        exchange();
    }

    public void setFamilies(RobotFamily[] families) {
        this.families = families;
    }
}
