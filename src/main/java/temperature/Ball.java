package temperature;

public class Ball implements IBall{

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

	@Override
	public int[] getColor() {
		return Color;
	}
	
	@Override
	public void setColor(int[] color) {
		Color = color;
	}

	@Override
	public double[] getPosition() {
		return Position;
	}

	@Override
	public void setPosition(double[] position) {
		Position = position;
	}

	@Override
	public double[] getVelocity() {
		return this.Velocity;
	}

	@Override
	public void setVelocity(double[] vel) {
		this.Velocity = vel;
	}

	@Override
	public int getRadius() {
		return Radius;
	}

	@Override
	public void setRadius(int radius) {
		Radius = radius;
	}

	@Override
	public double getMass() {
		return Mass;
	}

	@Override
	public void setMass(double mass) {
		Mass = mass;
	}

}