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
		this.getVelocity()[0] = this.getVelocity()[0]
				- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[0]);
		if (this.getPosition()[0] > Config.getInstance().getRightWall() - (float) this.getRadius()) {
			this.getVelocity()[0] = -Config.getInstance().getLossOfEnergyAtTheWall() * this.getVelocity()[0];
			this.getPosition()[0] = Config.getInstance().getRightWall() - (float) this.getRadius();
		} else {
			if (this.getPosition()[0] < Config.getInstance().getLeftWall() + (float) this.getRadius()) {
				this.getVelocity()[0] = -Config.getInstance().getLossOfEnergyAtTheWall() * this.getVelocity()[0];
				this.getPosition()[0] = Config.getInstance().getLeftWall() + (float) this.getRadius();
			}
		}
		if (this.getPosition()[1] + this.getVelocity()[1] >= Config.getInstance().getBottom() - this.getRadius()) {
			this.getPosition()[1] = Config.getInstance().getBottom() - this.getRadius();// 0.5*(Position[1]+Bottom-Radius);
			if (Functions.mathOperator.Abs(this.getVelocity()[1]) < Config.getInstance().getMinVerVel()) {
				this.getVelocity()[1] = 0;
			} else {
				if (this.getVelocity()[1] >= 0) {
					this.getVelocity()[1] = -Config.getInstance().getLossOfEnergyAtTheWallByDrag()
							* this.getVelocity()[1];
				} else {
					this.getVelocity()[1] = Config.getInstance().getLossOfEnergyAtTheWallByDrag()
							* this.getVelocity()[1]
							- Config.getInstance().getAirDrag()
									* Functions.mathOperator.SignumFunction(this.getVelocity()[1])
							+ this.getMass() * Config.getInstance().getGravitationalConstant();
				}
			}
		} else {
			this.getVelocity()[1] = this.getVelocity()[1]
					- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[1])
					+ Config.getInstance().getGravitationalConstant();
		}
		this.setPosition(Functions.mathOperator.AdditionOfVectors(this.getPosition(), this.getVelocity()));
	}

	default Ball Prediction() {
		final double[] NewVelocity = new double[2];
		NewVelocity[0] = this.getVelocity()[0]
				- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[0]);
		NewVelocity[1] = this.getVelocity()[1]
				- Config.getInstance().getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[1])
				+ Config.getInstance().getGravitationalConstant();
		return new Ball(Functions.mathOperator.AdditionOfVectors(this.getPosition(), this.getVelocity()), NewVelocity,
				this.getRadius(), this.getColor());
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
