package temperature;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class Config implements IConfig {

	private int			framerate					= 60;
	private int			AmountOfBalls				= 400;
	private int			LevelOfCorrectness			= 0;												// the higher
																										// the slower
	private int			speed						= 1;												// the faster
																										// the less
																										// smooth
	private int			MinVerVel					= 1;
	private double		RestartCondition			= 0.01;												// 0.0000001*AmountOfBalls;
	private double		AirDrag						= 0.01;
	private double		LossOfEnergyAtTheWall		= 0.99;
	private double		LossOfEnergyAtTheBottom		= 0.99;
	private double		LossOfEnergyAtTheWallByDrag	= 0.99;
	private double		GravitationalConstant		= 0.08;
	private boolean		restart						= true;
	private double		SquareRootCorrectness		= 0.00000001;
	private double		ratio						= 2;
	private int			LeftWall;
	private Dimension	screenSize					= Toolkit.getDefaultToolkit().getScreenSize();
	private int			RightWall					= (int) screenSize.getWidth();
	private int			Bottom						= (int) (screenSize.getHeight());
	private int			MinRad						= (int) (1.5 * (getRightWall() / AmountOfBalls));
	private int			MaxRad						= (int) (ratio * MinRad);
	private int			Energy						= 70;

	private static IConfig instance;

	private Config() {
		instance = this;
	}

	public static IConfig getInstance() {
		if (instance == null) {
			return new Config();
		}
		return instance;
	}

	@Override
	public double getAirDrag() {
		return AirDrag;
	}

	@Override
	public int getBottom() {
		return Bottom;
	}

	@Override
	public double getGravitationalConstant() {
		return GravitationalConstant;
	}

	@Override
	public int getLeftWall() {
		return LeftWall;
	}

	@Override
	public double getLossOfEnergyAtTheWall() {
		return LossOfEnergyAtTheWall;
	}

	@Override
	public double getLossOfEnergyAtTheWallByDrag() {
		return LossOfEnergyAtTheWallByDrag;
	}

	@Override
	public int getMinVerVel() {
		return MinVerVel;
	}

	@Override
	public int getRightWall() {
		return RightWall;
	}

	@Override
	public void setAirDrag(double airDrag) {
		AirDrag = airDrag;
	}

	@Override
	public void setBottom(int bottom) {
		Bottom = bottom;
	}

	@Override
	public void setGravitationalConstant(double gravitationalConstant) {
		GravitationalConstant = gravitationalConstant;
	}

	@Override
	public void setLeftWall(int leftWall) {
		LeftWall = leftWall;
	}

	@Override
	public void setLossOfEnergyAtTheWall(double lossOfEnergyAtTheWall) {
		LossOfEnergyAtTheWall = lossOfEnergyAtTheWall;
	}

	@Override
	public void setLossOfEnergyAtTheWallByDrag(double lossOfEnergyAtTheWallByDrag) {
		LossOfEnergyAtTheWallByDrag = lossOfEnergyAtTheWallByDrag;
	}

	@Override
	public void setMinVerVel(int minVerVel) {
		MinVerVel = minVerVel;
	}

	@Override
	public void setRightWall(int rightWall) {
		RightWall = rightWall;
	}

	/**
	 * @return the framerate
	 */
	@Override
	public int getFramerate() {
		return framerate;
	}

	/**
	 * @param framerate the framerate to set
	 */
	@Override
	public void setFramerate(int framerate) {
		this.framerate = framerate;
	}

	/**
	 * @return the amountOfBalls
	 */
	@Override
	public int getAmountOfBalls() {
		return AmountOfBalls;
	}

	/**
	 * @param amountOfBalls the
	 *                      amountOfBalls to
	 *                      set
	 */
	@Override
	public void setAmountOfBalls(int amountOfBalls) {
		AmountOfBalls = amountOfBalls;
	}

	/**
	 * @return the levelOfCorrectness
	 */
	@Override
	public int getLevelOfCorrectness() {
		return LevelOfCorrectness;
	}

	/**
	 * @param levelOfCorrectness the
	 *                           levelOfCorrectness
	 *                           to set
	 */
	@Override
	public void setLevelOfCorrectness(int levelOfCorrectness) {
		LevelOfCorrectness = levelOfCorrectness;
	}

	/**
	 * @return the speed
	 */
	@Override
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the restartCondition
	 */
	@Override
	public double getRestartCondition() {
		return RestartCondition;
	}

	/**
	 * @param restartCondition the
	 *                         restartCondition
	 *                         to set
	 */
	@Override
	public void setRestartCondition(double restartCondition) {
		RestartCondition = restartCondition;
	}

	/**
	 * @return the lossOfEnergyAtTheBottom
	 */
	@Override
	public double getLossOfEnergyAtTheBottom() {
		return LossOfEnergyAtTheBottom;
	}

	/**
	 * @param lossOfEnergyAtTheBottom the
	 *                                lossOfEnergyAtTheBottom
	 *                                to set
	 */
	@Override
	public void setLossOfEnergyAtTheBottom(double lossOfEnergyAtTheBottom) {
		LossOfEnergyAtTheBottom = lossOfEnergyAtTheBottom;
	}

	/**
	 * @return the restart
	 */
	@Override
	public boolean isRestart() {
		return restart;
	}

	/**
	 * @param restart the restart to set
	 */
	@Override
	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	/**
	 * @return the squareRootCorrectness
	 */
	@Override
	public double getSquareRootCorrectness() {
		return SquareRootCorrectness;
	}

	/**
	 * @param squareRootCorrectness the
	 *                              squareRootCorrectness
	 *                              to set
	 */
	@Override
	public void setSquareRootCorrectness(double squareRootCorrectness) {
		SquareRootCorrectness = squareRootCorrectness;
	}

	/**
	 * @return the ratio
	 */
	@Override
	public double getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	@Override
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the screenSize
	 */
	@Override
	public Dimension getScreenSize() {
		return screenSize;
	}

	/**
	 * @param screenSize the screenSize to
	 *                   set
	 */
	@Override
	public void setScreenSize(Dimension screenSize) {
		this.screenSize = screenSize;
	}

	/**
	 * @return the minRad
	 */
	@Override
	public int getMinRad() {
		return MinRad;
	}

	/**
	 * @param minRad the minRad to set
	 */
	@Override
	public void setMinRad(int minRad) {
		MinRad = minRad;
	}

	/**
	 * @return the maxRad
	 */
	@Override
	public int getMaxRad() {
		return MaxRad;
	}

	/**
	 * @param maxRad the maxRad to set
	 */
	@Override
	public void setMaxRad(int maxRad) {
		MaxRad = maxRad;
	}

	/**
	 * @return the energy
	 */
	@Override
	public int getEnergy() {
		return Energy;
	}

	/**
	 * @param energy the energy to set
	 */
	@Override
	public void setEnergy(int energy) {
		Energy = energy;
	}

}