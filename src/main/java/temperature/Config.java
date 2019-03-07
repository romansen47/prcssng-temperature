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
	private static int RightWall = (int) Config.screenSize.getWidth();
	private static int Bottom = (int) (Config.screenSize.getHeight());
	static int MinRad = (int) (1.5 * (Config.getRightWall()) / (Config.AmountOfBalls));
	static int MaxRad = (int) (Config.ratio * Config.MinRad);
	static int Energy = 70;

	public static double getAirDrag() {
		return Config.AirDrag;
	}

	public static int getBottom() {
		return Config.Bottom;
	}

	public static double getGravitationalConstant() {
		return Config.GravitationalConstant;
	}

	public static int getLeftWall() {
		return Config.LeftWall;
	}

	public static double getLossOfEnergyAtTheWall() {
		return Config.LossOfEnergyAtTheWall;
	}

	public static double getLossOfEnergyAtTheWallByDrag() {
		return Config.LossOfEnergyAtTheWallByDrag;
	}

	public static int getMinVerVel() {
		return Config.MinVerVel;
	}

	public static int getRightWall() {
		return Config.RightWall;
	}

	public static void setAirDrag(double airDrag) {
		Config.AirDrag = airDrag;
	}

	public static void setBottom(int bottom) {
		Config.Bottom = bottom;
	}

	public static void setGravitationalConstant(double gravitationalConstant) {
		Config.GravitationalConstant = gravitationalConstant;
	}

	public static void setLeftWall(int leftWall) {
		Config.LeftWall = leftWall;
	}

	public static void setLossOfEnergyAtTheWall(double lossOfEnergyAtTheWall) {
		Config.LossOfEnergyAtTheWall = lossOfEnergyAtTheWall;
	}

	public static void setLossOfEnergyAtTheWallByDrag(double lossOfEnergyAtTheWallByDrag) {
		Config.LossOfEnergyAtTheWallByDrag = lossOfEnergyAtTheWallByDrag;
	}

	public static void setMinVerVel(int minVerVel) {
		Config.MinVerVel = minVerVel;
	}

	public static void setRightWall(int rightWall) {
		Config.RightWall = rightWall;
	}

}