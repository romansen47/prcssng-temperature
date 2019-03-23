package temperature;

import processing.core.PConstants;
import processing.template.Gui;

public class Temperature extends Gui {

	private final IFunctions functions = Functions.getInstance();

	public static void main(String[] args) {
		(new Gui()).run("temperature.Temperature");
	}

	// Create a set of balls
	Ball[] Balls;

	@Override
	public void draw() {

		// Setting background to black
		this.background(0);

		// If restart condition satisfied create
		// new set of balls
		if (Config.getInstance().isRestart()) {
			Config.getInstance().setRestart(false);
			final Ball[] BallsTmp = getFunctions().createNewBalls(Config.getInstance().getAmountOfBalls());
			Balls = new Ball[BallsTmp.length];
			for (int j = 0; j < Balls.length - 1; j++) {
				Balls[j] = BallsTmp[j];
			}
			final double[] Pos = new double[2];
			Pos[1]	= -100;
			Pos[0]	= Config.getInstance().getRightWall() / 2;
			final double[] Vel = new double[2];
			Vel[0]	= 0;
			Vel[1]	= 0;
			final int[] Col = new int[3];
			Col[0]					= 255;
			Col[1]					= 0;
			Col[2]					= 0;
			Balls[Balls.length - 1]	= new Ball(Pos, Vel, Config.getInstance().getMaxRad() * 1, Col);
		}

		double MaxMass = 0;
		for (int j = 0; j < Balls.length; j++) {
			if (MaxMass < Balls[j].getMass()) {
				MaxMass = Balls[j].getMass();
			}
		}

		for (int i = 0; i < Config.getInstance().getSpeed(); i++) {
			getFunctions().MoveBalls(Balls);
			for (int j = 0; j < Balls.length; j++) {
				Balls[j].setColor(
						getFunctions().Color(getFunctions().getMathOperator().MagnitudeOfVector(Balls[j].getVelocity()),
								(Balls[j].getMass()) / MaxMass));
			}
			Balls = getFunctions().collision(Balls, Config.getInstance().getLevelOfCorrectness());
		}

		// Check, if restart condition is
		// satisfied
		int VelOfAll = 0;
		for (int i = 0; i < Balls.length; i++) {
			VelOfAll += getFunctions().getMathOperator().MagnitudeOfVector(Balls[i].getVelocity());
		}
		if (VelOfAll < Config.getInstance().getRestartCondition()) {
			Config.getInstance().setRestart(true);
		}

		// Draw the balls
		DrawBalls(Balls);

		// Manual restart if 'r' is pressed -
		// exit() if another key or mouse is
		// pressed
		if ((mousePressed && (mouseButton == PConstants.LEFT)) || (keyPressed && key == 'r')) {
			try {
				Thread.sleep(400);
			} catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Config.getInstance().setRestart(true);
		}
		if ((mousePressed && (mouseButton == PConstants.RIGHT)) || (keyPressed && (key == 'q'))) {
			exit();
		}
	}

	// These drawing functions use fill()
	// and will be exported to
	// functions.java
	public void draw(Ball A) {
		this.fill(A.getColor()[0], A.getColor()[1], A.getColor()[2]);
		ellipse((float) A.getPosition()[0], (float) A.getPosition()[1], 2 * A.getRadius(), 2 * A.getRadius());
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
		frameRate(Config.getInstance().getFramerate());
	}

	/**
	 * @return the functions
	 */
	public IFunctions getFunctions() {
		return functions;
	}

}