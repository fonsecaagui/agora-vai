package robozasso;
import robocode.*;
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Robozasso - a robot by (your name here)
 */
public class Robozasso extends AdvancedRobot
{
	/**s
	 * run: Robozasso's default behavior
	 */
	int turnDirection = 1;
	boolean stopWhenSeeRobot = false; // See goCorner()
	public void run() {
		// Set colors
		setBodyColor(Color.blue);
		setGunColor(Color.blue);
		setRadarColor(Color.black);
		setScanColor(Color.yellow);

		// Loop forever
		while (true) {
			// Tell the game that when we take move,
			// we'll also want to turn right... a lot.
			setTurnRight(10000);
			// Limit our speed to 5
			setMaxVelocity(5);
			// Start moving (and turning)
			ahead(10000);
			// Repeat.
			getOthers();
			
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
	}
	
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(3);
		}
		if (e.isMyFault()) {
			if (e.getBearing() >= 0) {
				turnDirection = 1;
			} else {
				turnDirection = -1;
			}	
		turnRight(e.getBearing());

		// Determine a shot that won't kill the robot...
		// We want to ram him instead for bonus points
			if (e.getEnergy() > 16) {
				fire(3);
			} else if (e.getEnergy() > 10) {
				fire(2);
			} else if (e.getEnergy() > 4) {
				fire(1);
			} else if (e.getEnergy() > 2) {
				fire(.5);
			} else if (e.getEnergy() > .4) {
				fire(.1);
			}
				ahead(40);
		} else if (getOthers() == 1) {
			if (e.getBearing() >= 0) {
				turnDirection = 1;
			} else {
				turnDirection = -1;
			}	
		turnRight(e.getBearing());
			if (e.getEnergy() > 16) {
				fire(3);
			} else if (e.getEnergy() > 10) {
				fire(2);
			} else if (e.getEnergy() > 4) {
				fire(1);
			} else if (e.getEnergy() > 2) {
				fire(.5);
			} else if (e.getEnergy() > .4) {
				fire(.1);
			}
				ahead(40);
		}
		

	}
}
