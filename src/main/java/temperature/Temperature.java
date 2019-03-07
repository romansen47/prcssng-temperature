package temperature;

import processing.core.PConstants;
import processing.template.Gui;

public class Temperature extends Gui {

	public static void main(String[] args) {
		(new Gui()).run("temperature.Temperature");
	}

	// Create a set of balls
	Ball[] Balls;

	@Override
	public void draw() {

		// Setting background to black
		this.background(0);

		// If restart condition satisfied create new set of balls
		if (Config.restart == true) {
			Config.restart = false;
			final Ball[] BallsTmp = Functions.createNewBalls(Config.AmountOfBalls);
			this.Balls = new Ball[BallsTmp.length];
			for (int j = 0; j < this.Balls.length - 1; j++) {
				this.Balls[j] = BallsTmp[j];
			}
			final double[] Pos = new double[2];
			Pos[1] = -100;
			Pos[0] = Config.getRightWall() / 2;
			final double[] Vel = new double[2];
			Vel[0] = 0;
			Vel[1] = 0;
			final int[] Col = new int[3];
			Col[0] = 255;
			Col[1] = 0;
			Col[2] = 0;
			this.Balls[this.Balls.length - 1] = new Ball(Pos, Vel, Config.MaxRad * 1, Col);
		}

		double MaxMass = 0;
		for (int j = 0; j < this.Balls.length; j++) {
			if (MaxMass < this.Balls[j].getMass()) {
				MaxMass = this.Balls[j].getMass();
			}
		}

		for (int i = 0; i < Config.speed; i++) {
			Functions.MoveBalls(this.Balls);
			for (int j = 0; j < this.Balls.length; j++) {
				this.Balls[j]
						.setColor(Functions.Color(Functions.mathOperator.MagnitudeOfVector(this.Balls[j].getVelocity()),
								(this.Balls[j].getMass()) / MaxMass));
			}
			this.Balls = Functions.collision(this.Balls, Config.LevelOfCorrectness);
		}

		// Check, if restart condition is satisfied
		int VelOfAll = 0;
		for (int i = 0; i < this.Balls.length; i++) {
			VelOfAll += Functions.mathOperator.MagnitudeOfVector(this.Balls[i].getVelocity());
		}
		if (VelOfAll < Config.RestartCondition) {
			Config.restart = true;
		}

		// Draw the balls
		this.DrawBalls(this.Balls);

		// Manual restart if 'r' is pressed - exit() if another key or mouse is pressed
		if ((this.mousePressed && (this.mouseButton == PConstants.LEFT)) || (this.keyPressed && this.key == 'r')) {
			try {
				Thread.sleep(400);
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Config.restart = true;
		}
		if ((this.mousePressed && (this.mouseButton == PConstants.RIGHT)) || (this.keyPressed && (this.key == 'q'))) {
			this.exit();
		}
	}

	// These drawing functions use fill() and will be exported to Functions.java
	public void draw(Ball A) {
		this.fill(A.getColor()[0], A.getColor()[1], A.getColor()[2]);
		this.ellipse((float) A.getPosition()[0], (float) A.getPosition()[1], 2 * A.getRadius(), 2 * A.getRadius());
	}

	public void DrawBalls(Ball[] Bs) {
		for (int i = 0; i < Bs.length; i++) {
			this.draw(Bs[i]);
		}
	}

	@Override
	public void settings() {
		this.fullScreen(PConstants.P3D);
	}

	@Override
	public void setup() {
		this.frameRate(Config.framerate);
	}

}