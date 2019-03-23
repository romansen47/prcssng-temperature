package temperature;

public class Ball implements IBall {

	private int[] Color = new int[3];
	private double Mass = 1;
	private double[] Position = new double[2];
	private int Radius = 1;
	private double[] Velocity = new double[2];

	public Ball() {
	}

	public Ball(double[] position, double[] velocity, int radius, int[] col) {
		Position = position;
		Velocity = velocity;
		Radius = radius;
		Color = col;
		Mass = Math.pow(Radius, 1);
	}

	@Override
	public int[] getColor() {
		return Color;
	}

	@Override
	public double getMass() {
		return Mass;
	}

	@Override
	public double[] getPosition() {
		return Position;
	}

	@Override
	public int getRadius() {
		return Radius;
	}

	@Override
	public double[] getVelocity() {
		return Velocity;
	}

	@Override
	public void setColor(int[] color) {
		Color = color;
	}

	@Override
	public void setMass(double mass) {
		Mass = mass;
	}

	@Override
	public void setPosition(double[] position) {
		Position = position;
	}

	@Override
	public void setRadius(int radius) {
		Radius = radius;
	}

	@Override
	public void setVelocity(double[] vel) {
		Velocity = vel;
	}

}