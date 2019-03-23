package temperature;

/**
 * @author ubuntu
 *
 */
public interface IBall {

	/**
	 * getter for Color
	 *
	 * @return the color
	 */
	int[] getColor();

	/**
	 * getter for mass
	 *
	 * @return the mass
	 */
	double getMass();

	/**
	 * getter for position
	 *
	 * @return the position
	 */
	double[] getPosition();

	/**
	 * Getter for the radius
	 *
	 * @return the radius
	 */
	int getRadius();

	/**
	 * getter for the velocity vector
	 *
	 * @return the velocity vector
	 */
	double[] getVelocity();

	/**
	 * move the ball with given position and velocity vectors
	 */
	default void move() {
		getVelocity()[0] = getVelocity()[0]
				- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[0]);
		if (getPosition()[0] > Config.getInstance().getRightWall() - (float) getRadius()) {
			getVelocity()[0] = -Config.getInstance().getLossOfEnergyAtTheWall() * getVelocity()[0];
			getPosition()[0] = Config.getInstance().getRightWall() - (float) getRadius();
		} else {
			if (getPosition()[0] < Config.getInstance().getLeftWall() + (float) getRadius()) {
				getVelocity()[0] = -Config.getInstance().getLossOfEnergyAtTheWall() * getVelocity()[0];
				getPosition()[0] = Config.getInstance().getLeftWall() + (float) getRadius();
			}
		}
		if (getPosition()[1] + getVelocity()[1] >= Config.getInstance().getBottom() - getRadius()) {
			getPosition()[1] = Config.getInstance().getBottom() - getRadius();// 0.5*(Position[1]+Bottom-Radius);
			if (Functions.mathOperator.Abs(getVelocity()[1]) < Config.getInstance().getMinVerVel()) {
				getVelocity()[1] = 0;
			} else {
				if (getVelocity()[1] >= 0) {
					getVelocity()[1] = -Config.getInstance().getLossOfEnergyAtTheWallByDrag() * getVelocity()[1];
				} else {
					getVelocity()[1] = Config.getInstance().getLossOfEnergyAtTheWallByDrag() * getVelocity()[1]
							- Config.getInstance().getAirDrag()
									* Functions.mathOperator.SignumFunction(getVelocity()[1])
							+ getMass() * Config.getInstance().getGravitationalConstant();
				}
			}
		} else {
			getVelocity()[1] = getVelocity()[1]
					- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[1])
					+ Config.getInstance().getGravitationalConstant();
		}
		setPosition(Functions.mathOperator.AdditionOfVectors(getPosition(), getVelocity()));
	}

	default Ball Prediction() {
		final double[] NewVelocity = new double[2];
		NewVelocity[0] = getVelocity()[0]
				- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[0]);
		NewVelocity[1] = getVelocity()[1]
				- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[1])
				+ Config.getInstance().getGravitationalConstant();
		return new Ball(Functions.mathOperator.AdditionOfVectors(getPosition(), getVelocity()), NewVelocity,
				getRadius(), getColor());
	}

	/**
	 * setter for color
	 *
	 * @param color the color
	 */
	void setColor(int[] color);

	/**
	 * Setter for the mass
	 *
	 * @param mass the mass
	 */
	void setMass(double mass);

	/**
	 * setter for the position
	 *
	 * @param position the position
	 */
	void setPosition(double[] position);

	/**
	 * setter for the radius
	 *
	 * @param radius the radius
	 */
	void setRadius(int radius);

	/**
	 * setter for the velocity vector
	 *
	 * @param vel the velocity vector
	 */
	void setVelocity(double[] vel);

}
