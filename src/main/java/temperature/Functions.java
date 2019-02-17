package temperature;

import java.util.Random;

import math.IMathOp;
import math.MathOp;

public class Functions {

	final static public IMathOp mathOperator = new MathOp();

	static public Ball[] Collision(Ball[] Balls, int LevelOfCorrectness) {
		if (Balls.length < 2) {
			return Balls;
		}
		if (Balls.length == 2) {
			Balls = Interaction(Balls[0], Balls[1]);
		} else {
			Ball tmp1 = Balls[Balls.length - 1];
			Ball[] TmpBalls1 = new Ball[Balls.length - 1];
			for (int i = 0; i < Balls.length - 1; i++) {
				TmpBalls1[i] = Balls[i];
			}
			for (int i = 0; i < TmpBalls1.length; i++) {
				Ball[] Interaction = Interaction(TmpBalls1[i], tmp1);
				TmpBalls1[i] = Interaction[0];
				tmp1 = Interaction[1];
			}
			TmpBalls1 = Collision(TmpBalls1, LevelOfCorrectness - 1);
			if (LevelOfCorrectness > 0) {
				Ball tmp2 = Balls[0];
				Ball[] TmpBalls2 = new Ball[Balls.length - 1];
				for (int i = 0; i < Balls.length - 1; i++) {
					TmpBalls2[i] = Balls[i + 1];
				}
				for (int i = 0; i < TmpBalls2.length; i++) {
					Ball[] Interaction = Interaction(TmpBalls2[i], tmp2);
					TmpBalls2[i] = Interaction[0];
					tmp2 = Interaction[1];
				}
				TmpBalls2 = Collision(TmpBalls2, LevelOfCorrectness - 1);

				Balls[0].setPosition(mathOperator.ScalarMultiplication(0.5,
						mathOperator.AdditionOfVectors(TmpBalls1[0].getPosition(), tmp2.getPosition())));
				Balls[Balls.length - 1].setPosition(mathOperator.ScalarMultiplication(0.5, mathOperator
						.AdditionOfVectors(TmpBalls2[TmpBalls2.length - 1].getPosition(), tmp1.getPosition())));
				for (int i = 1; i < Balls.length - 1; i++) {
					Balls[i].setPosition(mathOperator.ScalarMultiplication(0.5, mathOperator
							.AdditionOfVectors(TmpBalls1[i].getPosition(), TmpBalls2[i - 1].getPosition())));
				}
			} else {
				for (int i = 0; i < Balls.length - 2; i++) {
					Balls[i] = TmpBalls1[i];
					Balls[Balls.length - 1] = tmp1;
				}
			}
		}
		return Balls;
	}

	static public Ball[] Interaction(Ball A, Ball B) {
		Ball ATmp = A.Prediction();
		Ball BTmp = B.Prediction();
		double[] Tmp = mathOperator.AdditionOfVectors(ATmp.getPosition(),
				mathOperator.ReversalOfVector(BTmp.getPosition()));
		if (mathOperator.Abs(Tmp[0]) <= A.getRadius() + B.getRadius()
				&& mathOperator.Abs(Tmp[1]) <= A.getRadius() + B.getRadius()) {
			double Condition = mathOperator.MagnitudeOfVector(mathOperator.AdditionOfVectors(
					mathOperator.AdditionOfVectors(ATmp.getPosition(), ATmp.getVelocity()),
					mathOperator.AdditionOfVectors(mathOperator.ReversalOfVector(BTmp.getPosition()),
							mathOperator.ReversalOfVector(BTmp.getVelocity()))));
			if (Condition <= A.getRadius() + B.getRadius()) {
				double[] connection, PointOfCollision, projectedVelocityA, projectedVelocityB = new double[2];
				connection = mathOperator.AdditionOfVectors(B.getPosition(),
						mathOperator.ReversalOfVector(A.getPosition()));
				PointOfCollision = mathOperator.AdditionOfVectors(A.getPosition(),
						mathOperator.ScalarMultiplication(A.getRadius(), mathOperator.UnitVector(connection)));
				A.setPosition(mathOperator.ScalarMultiplication(0.5, mathOperator.AdditionOfVectors(A.getPosition(),
						mathOperator.AdditionOfVectors(PointOfCollision, mathOperator
								.ScalarMultiplication(-A.getRadius() - 1, mathOperator.UnitVector(connection))))));
				B.setPosition(mathOperator.ScalarMultiplication(0.5, mathOperator.AdditionOfVectors(B.getPosition(),
						mathOperator.AdditionOfVectors(PointOfCollision, mathOperator
								.ScalarMultiplication(B.getRadius() + 1, mathOperator.UnitVector(connection))))));
				projectedVelocityA = mathOperator.Projection(A.getVelocity(), mathOperator.UnitVector(connection));
				projectedVelocityB = mathOperator.Projection(B.getVelocity(), mathOperator.UnitVector(connection));
				// double[] TmpVelA= new double[2];
				A.setVelocity(mathOperator.AdditionOfVectors(A.getVelocity(),
						mathOperator.ScalarMultiplication(2 * B.getMass() / (A.getMass() + B.getMass()),
								mathOperator.AdditionOfVectors(projectedVelocityB,
										mathOperator.ReversalOfVector(projectedVelocityA)))));
				B.setVelocity(mathOperator.AdditionOfVectors(B.getVelocity(),
						mathOperator.ScalarMultiplication(2 * A.getMass() / (A.getMass() + B.getMass()),
								mathOperator.AdditionOfVectors(projectedVelocityA,
										mathOperator.ReversalOfVector(projectedVelocityB)))));
			}
		}
		Ball[] BALLS = new Ball[2];
		BALLS[0] = A;
		BALLS[1] = B;
		return BALLS;
	}

	static public void MoveBalls(Ball[] Balls) {
		for (int i = 0; i < Balls.length; i++) {
			Balls[i].move();
		}
	}

	static public Ball[] createNewBalls(int Amount) {
		Random random = new Random();
		Ball[] Balls = new Ball[Amount];
		{
			for (int i = 0; i < Amount; i++) {
				double[] Velocity = new double[2];
				{
					Velocity[0] = random.nextInt() % 2;
					Velocity[1] = 0;// random.nextInt()%0;
				}
				int[] Color = new int[3];
				int Radius = Config.MinRad + Math.abs(random.nextInt() % (Config.MaxRad - Config.MinRad));// 9/4*displayWidth/(config.AmountOfBalls)+i/10;//
																											// (int)(random(5,10));
				Color[0] = 50 + Math.abs(random.nextInt() % 150);// (int) random(200)%256;
				Color[1] = 50 + Math.abs(random.nextInt() % 150);// (int) (random(200)+i)%256;
				Color[2] = 50 + Math.abs(random.nextInt() % 150);// (int) (random(200)+2*i)%256;
				double[] Position = new double[2];
				{
					Position[0] = Radius + Math.abs(random.nextInt() % (Config.getRightWall() - Config.MaxRad));
					Position[1] = Radius + (random.nextInt() % (Config.getBottom() - 2 * Radius));
				}
				;
				Balls[i] = new Ball(Position, Velocity, Radius, Color);
			}
			return (Balls);
		}
	}

	static public int[] Color(double spd, double Mass) {
		double energy = Mass * (spd * spd) / Config.Energy;
		int[] tmp = new int[3];
		if (energy < 1) {
			tmp[0] = (int) (255 * (mathOperator.SQRT(1 - Math.pow(1 - energy, 2))));
			tmp[1] = (int) (255 * mathOperator.SQRT(energy) * mathOperator.SQRT(1 - energy));
			tmp[2] = (int) (255 * (1 - mathOperator.SQRT(1 - Math.pow(1 - energy, 2))));
		} else {
			tmp[2] = 0;
			tmp[1] = 0;
			tmp[0] = 255;
		}
		return (tmp);
	}
}