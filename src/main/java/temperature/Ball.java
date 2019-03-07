package temperature;

public class Ball implements IBall {

	private double[] Position = new double[2];
	private double[] Velocity = new double[2];
	private double Mass = 1;
	private int Radius = 1;
	private int[] Color = new int[3];

	public Ball() {
	}

	public Ball(double[] position, double[] velocity, int radius, int[] col) {
		this.Position = position;
		this.Velocity = velocity;
		this.Radius = radius;
		this.Color = col;
		this.Mass = Math.pow(this.Radius, 1);
	}

	@Override
	public int[] getColor() {
		return this.Color;
	}

	@Override
	public double getMass() {
		return this.Mass;
	}

	@Override
	public double[] getPosition() {
		return this.Position;
	}

	@Override
	public int getRadius() {
		return this.Radius;
	}

	@Override
	public double[] getVelocity() {
		return this.Velocity;
	}

	@Override
	public void setColor(int[] color) {
		this.Color = color;
	}

	@Override
	public void setMass(double mass) {
		this.Mass = mass;
	}

	@Override
	public void setPosition(double[] position) {
		this.Position = position;
	}

	@Override
	public void setRadius(int radius) {
		this.Radius = radius;
	}

	@Override
	public void setVelocity(double[] vel) {
		this.Velocity = vel;
	}

}