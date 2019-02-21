package temperature;

public interface IBall {

	default public Ball Prediction() {
		double[] NewVelocity = new double[2];
		NewVelocity[0] = getVelocity()[0] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[0]);
		NewVelocity[1] = getVelocity()[1] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[1])
				+ Config.getGravitationalConstant();
		return new Ball(Functions.mathOperator.AdditionOfVectors(getPosition(), getVelocity()), NewVelocity, getRadius(), getColor());
	}

	default void move() {

		getVelocity()[0] = getVelocity()[0] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[0]);
		if (getPosition()[0] > Config.getRightWall() - (float)getRadius()) {
			getVelocity()[0] = -Config.getLossOfEnergyAtTheWall() * getVelocity()[0];
			getPosition()[0] = Config.getRightWall() - (float)getRadius();
		} else {
			if (getPosition()[0] < Config.getLeftWall() + (float)getRadius()) {
				getVelocity()[0] = -Config.getLossOfEnergyAtTheWall() * getVelocity()[0];
				getPosition()[0] = Config.getLeftWall() + (float)getRadius();
			}
		}
		if (getPosition()[1] + getVelocity()[1] >= Config.getBottom() - getRadius()) {
			getPosition()[1] = Config.getBottom() - getRadius();// 0.5*(Position[1]+Bottom-Radius);
			if (Functions.mathOperator.Abs(getVelocity()[1]) < Config.getMinVerVel())
				getVelocity()[1] = 0;
			else {
				if (getVelocity()[1] >= 0)
					getVelocity()[1] = -Config.getLossOfEnergyAtTheWallByDrag() * getVelocity()[1];
				else
					getVelocity()[1] = Config.getLossOfEnergyAtTheWallByDrag() * getVelocity()[1]
							- Config.getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[1])
							+ getMass() * Config.getGravitationalConstant();
			}
		} else
			getVelocity()[1] = getVelocity()[1] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(getVelocity()[1])
					+ Config.getGravitationalConstant();
		setPosition(Functions.mathOperator.AdditionOfVectors(getPosition(), getVelocity()));
	};
	
	int[] getColor();
	void setColor(int[] color);
	double[] getPosition();
	void setPosition(double[] position);
	double[] getVelocity();
	void setVelocity(double[] vel);
	int getRadius();
	void setRadius(int radius);
	double getMass();
	void setMass(double mass);

}
