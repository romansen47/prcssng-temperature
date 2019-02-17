package temperature;

public class Ball {

	private double[] Position = new double[2];
	private double[] Velocity = new double[2];
	private double Mass = 1;
	private int Radius = 1;
	private int[] Color = new int[3];

	public Ball() {
	}

	public Ball(double[] position, double[] velocity, int radius, int[] col) {
		Position = position;
		Velocity = velocity;
		Radius = radius;
		Color = col;
		Mass = Math.pow(Radius, 1);
	}

	public Ball Prediction() {
		double[] NewVelocity = new double[2];
		NewVelocity[0] = Velocity[0] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(Velocity[0]);
		NewVelocity[1] = Velocity[1] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(Velocity[1])
				+ Config.getGravitationalConstant();
		return new Ball(Functions.mathOperator.AdditionOfVectors(Position, Velocity), NewVelocity, Radius, Color);
	}

	public void move() {
		Velocity[0] = Velocity[0] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(Velocity[0]);
		if (getPosition()[0] > Config.getRightWall() - getRadius()) {
			Velocity[0] = -Config.getLossOfEnergyAtTheWall() * Velocity[0];
			getPosition()[0] = Config.getRightWall() - getRadius();
		} else {
			if (getPosition()[0] < Config.getLeftWall() + getRadius()) {
				Velocity[0] = -Config.getLossOfEnergyAtTheWall() * Velocity[0];
				getPosition()[0] = Config.getLeftWall() + getRadius();
			}
		}
		if (getPosition()[1] + Velocity[1] >= Config.getBottom() - getRadius()) {
			getPosition()[1] = Config.getBottom() - getRadius();// 0.5*(Position[1]+Bottom-Radius);
			if (Functions.mathOperator.Abs(Velocity[1]) < Config.getMinVerVel())
				Velocity[1] = 0;
			else {
				if (Velocity[1] >= 0)
					Velocity[1] = -Config.getLossOfEnergyAtTheWallByDrag() * Velocity[1];
				else
					Velocity[1] = Config.getLossOfEnergyAtTheWallByDrag() * Velocity[1]
							- Config.getAirDrag() * Functions.mathOperator.SignumFunction(Velocity[1])
							+ getMass() * Config.getGravitationalConstant();
			}
		} else
			Velocity[1] = Velocity[1] - Config.getAirDrag() * Functions.mathOperator.SignumFunction(Velocity[1])
					+ Config.getGravitationalConstant();
		setPosition(Functions.mathOperator.AdditionOfVectors(getPosition(), Velocity));
	}

	public int[] getColor() {
		return Color;
	}

	public void setColor(int[] color) {
		Color = color;
	}

	public double[] getPosition() {
		return Position;
	}

	public void setPosition(double[] position) {
		Position = position;
	}

	public double[] getVelocity() {
		return this.Velocity;
	}

	public void setVelocity(double[] vel) {
		this.Velocity = vel;
	}

	public int getRadius() {
		return Radius;
	}

	public void setRadius(int radius) {
		Radius = radius;
	}

	public double getMass() {
		return Mass;
	}

	public void setMass(double mass) {
		Mass = mass;
	}

}