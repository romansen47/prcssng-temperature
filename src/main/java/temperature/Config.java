package temperature;

import java.awt.Dimension;
import java.awt.Toolkit;

final public class Config {

	static int framerate = 60;
	static int AmountOfBalls = 400;
	static int LevelOfCorrectness = 0; // the higher the slower
	static int speed = 1; // the faster the less smooth
	private static int MinVerVel = 1;
	static double RestartCondition = 0.01; // 0.0000001*AmountOfBalls;
	private static double AirDrag = 0.01;
	private static double LossOfEnergyAtTheWall = 0.99;
	static double LossOfEnergyAtTheBottom = 0.99;
	private static double LossOfEnergyAtTheWallByDrag = 0.99;
	private static double GravitationalConstant = 0.08;
	static boolean restart = true;
	static double SquareRootCorrectness = 0.00000001;
	static double ratio = 2;
	private static int LeftWall;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int RightWall = (int) screenSize.getWidth();
	private static int Bottom = (int) (screenSize.getHeight());
	static int MinRad = (int) (1.5 * (getRightWall()) / (AmountOfBalls));
	static int MaxRad = (int) (ratio * MinRad);
	static int Energy = 70;

	public static double getAirDrag() {
		return AirDrag;
	}

	public static void setAirDrag(double airDrag) {
		AirDrag = airDrag;
	}

	public static int getRightWall() {
		return RightWall;
	}

	public static void setRightWall(int rightWall) {
		RightWall = rightWall;
	}

	public static double getLossOfEnergyAtTheWall() {
		return LossOfEnergyAtTheWall;
	}

	public static void setLossOfEnergyAtTheWall(double lossOfEnergyAtTheWall) {
		LossOfEnergyAtTheWall = lossOfEnergyAtTheWall;
	}

	public static int getLeftWall() {
		return LeftWall;
	}

	public static void setLeftWall(int leftWall) {
		LeftWall = leftWall;
	}

	public static int getBottom() {
		return Bottom;
	}

	public static void setBottom(int bottom) {
		Bottom = bottom;
	}

	public static int getMinVerVel() {
		return MinVerVel;
	}

	public static void setMinVerVel(int minVerVel) {
		MinVerVel = minVerVel;
	}

	public static double getLossOfEnergyAtTheWallByDrag() {
		return LossOfEnergyAtTheWallByDrag;
	}

	public static void setLossOfEnergyAtTheWallByDrag(double lossOfEnergyAtTheWallByDrag) {
		LossOfEnergyAtTheWallByDrag = lossOfEnergyAtTheWallByDrag;
	}

	public static double getGravitationalConstant() {
		return GravitationalConstant;
	}

	public static void setGravitationalConstant(double gravitationalConstant) {
		GravitationalConstant = gravitationalConstant;
	}

}