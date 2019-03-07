package temperature;

public interface IBall {

	int[] getColor();

	double getMass();

	double[] getPosition();

	int getRadius();

	double[] getVelocity();

	default void move() {

		this.getVelocity()[0] = this.getVelocity()[0]
				- Config.getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[0]);
		if (this.getPosition()[0] > Config.getRightWall() - (float) this.getRadius()) {
			this.getVelocity()[0] = -Config.getLossOfEnergyAtTheWall() * this.getVelocity()[0];
			this.getPosition()[0] = Config.getRightWall() - (float) this.getRadius();
		} else {
			if (this.getPosition()[0] < Config.getLeftWall() + (float) this.getRadius()) {
				this.getVelocity()[0] = -Config.getLossOfEnergyAtTheWall() * this.getVelocity()[0];
				this.getPosition()[0] = Config.getLeftWall() + (float) this.getRadius();
			}
		}
		if (this.getPosition()[1] + this.getVelocity()[1] >= Config.getBottom() - this.getRadius()) {
			this.getPosition()[1] = Config.getBottom() - this.getRadius();// 0.5*(Position[1]+Bottom-Radius);
			if (Functions.mathOperator.Abs(this.getVelocity()[1]) < Config.getMinVerVel()) {
				this.getVelocity()[1] = 0;
			} else {
				if (this.getVelocity()[1] >= 0) {
					this.getVelocity()[1] = -Config.getLossOfEnergyAtTheWallByDrag() * this.getVelocity()[1];
				} else {
					this.getVelocity()[1] = Config.getLossOfEnergyAtTheWallByDrag() * this.getVelocity()[1]
							- Config.getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[1])
							+ this.getMass() * Config.getGravitationalConstant();
				}
			}
		} else {
			this.getVelocity()[1] = this.getVelocity()[1]
					- Config.getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[1])
					+ Config.getGravitationalConstant();
		}
		this.setPosition(Functions.mathOperator.AdditionOfVectors(this.getPosition(), this.getVelocity()));
	}

	default Ball Prediction() {
		final double[] NewVelocity = new double[2];
		NewVelocity[0] = this.getVelocity()[0]
				- Config.getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[0]);
		NewVelocity[1] = this.getVelocity()[1]
				- Config.getAirDrag() * Functions.mathOperator.SignumFunction(this.getVelocity()[1])
				+ Config.getGravitationalConstant();
		return new Ball(Functions.mathOperator.AdditionOfVectors(this.getPosition(), this.getVelocity()), NewVelocity,
				this.getRadius(), this.getColor());
	}

	void setColor(int[] color);

	void setMass(double mass);

	void setPosition(double[] position);

	void setRadius(int radius);

	void setVelocity(double[] vel);

}
