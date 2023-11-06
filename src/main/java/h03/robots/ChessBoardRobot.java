package h03.robots;

import fopbot.RobotFamily;

import static fopbot.RobotFamily.*;

/**
 * A field entity that represents a robot on a graphical user interface, which has two families ordered
 * depending on the parity of the sum of its coordinates.
 */
public class ChessBoardRobot extends MultiFamilyRobot {

    /**
     * Constructs and initializes a robot at the specified {@code (x,y)} location with the viewing
     * direction {@code UP} and 0 coins. The family of this robot depends on the parity of the sum
     * of its coordinates.
     *
     * @param x           the X coordinate of the newly constructed robot
     * @param y           the Y coordinate of the newly constructed robot
     * @param initialEven the initial even
     * @param initialOdd  the initial odd
     */
    public ChessBoardRobot(int x, int y, RobotFamily initialEven, RobotFamily initialOdd) {
        // assume x + y is even, then the order of the families is even, odd
        super(x, y, new RobotFamily[]{initialEven, initialOdd});
        // if x + y is odd, then the order of the families is odd, even
        if ((x + y) % 2 != 0) {
            setFamilies(new RobotFamily[]{initialOdd, initialEven});
        }
    }

    /**
     * Instantiates a new chess board robot, with the initial families SQUARE_BLACK and SQUARE_WHITE.
     *
     * @param x the x
     * @param y the y
     */
    public ChessBoardRobot(int x, int y) {
        this(x, y, SQUARE_BLACK, SQUARE_WHITE);
    }
}
