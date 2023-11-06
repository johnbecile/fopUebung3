package h03.robots;

import fopbot.RobotFamily;

import static fopbot.RobotFamily.*;

/**
 * A field entity that represents a robot on a graphical user interface, which can change its color
 * with red, green and blue.
 */
public class RGBRobot extends MultiFamilyRobot {

    /**
     * Constructs and initializes a robot at the specified {@code (x,y)} location with the viewing
     * direction {@code UP} and 0 coins. The family of this robot is square red, square green and
     * square blue, the changing order depends on the boolean inverted.
     *
     * @param x        the X coordinate of the newly constructed robot
     * @param y        the Y coordinate of the newly constructed robot
     * @param inverted the changing order of the families
     */
    public RGBRobot(int x, int y, boolean inverted) {
        super(x, y, new RobotFamily[]{SQUARE_RED, SQUARE_GREEN, SQUARE_BLUE});
        if (inverted) {
            setRobotFamily(SQUARE_BLUE);
            setFamilies(new RobotFamily[]{SQUARE_BLUE, SQUARE_GREEN, SQUARE_RED});
        }
    }


    /**
     * Tests the robot by changing its color three times.
     */
    public void testRGB() {
        for (int i = 0; i < 3; i++) {
            exchange();
        }
    }
}
