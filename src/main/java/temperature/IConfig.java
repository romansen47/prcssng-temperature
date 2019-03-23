package temperature;

import java.awt.Dimension;

interface IConfig {

	double getAirDrag();

	int getBottom();

	double getGravitationalConstant();

	int getLeftWall();

	double getLossOfEnergyAtTheWall();

	double getLossOfEnergyAtTheWallByDrag();

	int getMinVerVel();

	int getRightWall();

	void setAirDrag(double airDrag);

	/**
	 * @return the framerate
	 */
	int getFramerate();

	/**
	 * @param framerate the framerate to set
	 */
	void setFramerate(int framerate);

	/**
	 * @return the amountOfBalls
	 */
	int getAmountOfBalls();

	/**
	 * @param amountOfBalls the
	 *                      amountOfBalls to
	 *                      set
	 */
	void setAmountOfBalls(int amountOfBalls);

	/**
	 * @return the levelOfCorrectness
	 */
	int getLevelOfCorrectness();

	/**
	 * @param levelOfCorrectness the
	 *                           levelOfCorrectness
	 *                           to set
	 */
	void setLevelOfCorrectness(int levelOfCorrectness);

	/**
	 * @return the speed
	 */
	int getSpeed();

	/**
	 * @param speed the speed to set
	 */
	void setSpeed(int speed);

	/**
	 * @return the restartCondition
	 */
	double getRestartCondition();

	/**
	 * @param restartCondition the
	 *                         restartCondition
	 *                         to set
	 */
	void setRestartCondition(double restartCondition);

	/**
	 * @return the lossOfEnergyAtTheBottom
	 */
	double getLossOfEnergyAtTheBottom();

	/**
	 * @param lossOfEnergyAtTheBottom the
	 *                                lossOfEnergyAtTheBottom
	 *                                to set
	 */
	void setLossOfEnergyAtTheBottom(double lossOfEnergyAtTheBottom);

	/**
	 * @return the restart
	 */
	boolean isRestart();

	/**
	 * @param restart the restart to set
	 */
	void setRestart(boolean restart);

	/**
	 * @return the squareRootCorrectness
	 */
	double getSquareRootCorrectness();

	/**
	 * @param squareRootCorrectness the
	 *                              squareRootCorrectness
	 *                              to set
	 */
	void setSquareRootCorrectness(double squareRootCorrectness);

	/**
	 * @return the ratio
	 */
	double getRatio();

	/**
	 * @param ratio the ratio to set
	 */
	void setRatio(double ratio);

	/**
	 * @return the screenSize
	 */
	Dimension getScreenSize();

	/**
	 * @param screenSize the screenSize to
	 *                   set
	 */
	void setScreenSize(Dimension screenSize);

	/**
	 * @return the minRad
	 */
	int getMinRad();

	/**
	 * @param minRad the minRad to set
	 */
	void setMinRad(int minRad);

	/**
	 * @return the maxRad
	 */
	int getMaxRad();

	/**
	 * @param maxRad the maxRad to set
	 */
	void setMaxRad(int maxRad);

	/**
	 * @return the energy
	 */
	int getEnergy();

	/**
	 * @param energy the energy to set
	 */
	void setEnergy(int energy);

	/**
	 * setter for bottom
	 *
	 * @param bottom bottom
	 */
	void setBottom(int bottom);

	/**
	 * setter for gravitation const
	 *
	 * @param gravitationalConstant G*
	 */
	void setGravitationalConstant(double gravitationalConstant);

	/**
	 * setter for leftWall
	 *
	 * @param leftWall the value
	 */
	void setLeftWall(int leftWall);

	/**
	 * setter for energy ratio at wall
	 *
	 * @param lossOfEnergyAtTheWall the
	 *                              value
	 */
	void setLossOfEnergyAtTheWall(double lossOfEnergyAtTheWall);

	/**
	 * setter for energy ratio at wall by
	 * drag
	 *
	 * @param lossOfEnergyAtTheWallByDrag the
	 *                                    value
	 */
	void setLossOfEnergyAtTheWallByDrag(double lossOfEnergyAtTheWallByDrag);

	/**
	 * setter for minVerVel
	 *
	 * @param minVerVel the value
	 */
	void setMinVerVel(int minVerVel);

	/**
	 * setter for rightWall
	 *
	 * @param rightWall the value
	 */
	void setRightWall(int rightWall);
}
