package temperature;

import java.util.Random;

import math.IMathOp;

public interface IFunctions {

	IMathOp getMathOperator();

	default Ball[] collision(Ball[] Balls, int LevelOfCorrectness) {
		if (Balls.length < 2) {
			return Balls;
		}
		if (Balls.length == 2) {
			Balls = Interaction(Balls[0], Balls[1]);
		} else {
			Ball	tmp1		= Balls[Balls.length - 1];
			Ball[]	TmpBalls1	= new Ball[Balls.length - 1];
			for (int i = 0; i < Balls.length - 1; i++) {
				TmpBalls1[i] = Balls[i];
			}
			for (int i = 0; i < TmpBalls1.length; i++) {
				final Ball[] Interaction = Interaction(TmpBalls1[i], tmp1);
				TmpBalls1[i]	= Interaction[0];
				tmp1			= Interaction[1];
			}
			TmpBalls1 = collision(TmpBalls1, LevelOfCorrectness - 1);
			if (LevelOfCorrectness > 0) {
				Ball	tmp2		= Balls[0];
				Ball[]	TmpBalls2	= new Ball[Balls.length - 1];
				for (int i = 0; i < Balls.length - 1; i++) {
					TmpBalls2[i] = Balls[i + 1];
				}
				for (int i = 0; i < TmpBalls2.length; i++) {
					final Ball[] Interaction = Interaction(TmpBalls2[i], tmp2);
					TmpBalls2[i]	= Interaction[0];
					tmp2			= Interaction[1];
				}
				TmpBalls2 = collision(TmpBalls2, LevelOfCorrectness - 1);

				Balls[0].setPosition(getMathOperator().ScalarMultiplication(0.5,
						getMathOperator().AdditionOfVectors(TmpBalls1[0].getPosition(), tmp2.getPosition())));
				Balls[Balls.length - 1].setPosition(getMathOperator().ScalarMultiplication(0.5, getMathOperator()
						.AdditionOfVectors(TmpBalls2[TmpBalls2.length - 1].getPosition(), tmp1.getPosition())));
				for (int i = 1; i < Balls.length - 1; i++) {
					Balls[i].setPosition(getMathOperator().ScalarMultiplication(0.5, getMathOperator()
							.AdditionOfVectors(TmpBalls1[i].getPosition(), TmpBalls2[i - 1].getPosition())));
				}
			} else {
				for (int i = 0; i < Balls.length - 2; i++) {
					Balls[i]				= TmpBalls1[i];
					Balls[Balls.length - 1]	= tmp1;
				}
			}
		}
		return Balls;
	}

	default int[] Color(double spd, double Mass) {
		final double	energy	= Mass * (spd * spd) / Config.getInstance().getEnergy();
		final int[]		tmp		= new int[3];
		if (energy < 1) {
			tmp[0]	= (int) (255 * (getMathOperator().SQRT(1 - Math.pow(1 - energy, 2))));
			tmp[1]	= (int) (255 * getMathOperator().SQRT(energy) * getMathOperator().SQRT(1 - energy));
			tmp[2]	= (int) (255 * (1 - getMathOperator().SQRT(1 - Math.pow(1 - energy, 2))));
		} else {
			tmp[2]	= 0;
			tmp[1]	= 0;
			tmp[0]	= 255;
		}
		return (tmp);
	}

	default Ball[] createNewBalls(int Amount) {
		final Random	random	= new Random();
		final Ball[]	Balls	= new Ball[Amount];
		{
			for (int i = 0; i < Amount; i++) {
				final double[] Velocity = new double[2];
				{
					Velocity[0]	= random.nextInt() % 2;
					Velocity[1]	= 0;					// random.nextInt()%0;
				}
				final int[]	Color	= new int[3];
				final int	Radius	= Config.getInstance().getMinRad() + Math
						.abs(random.nextInt() % (Config.getInstance().getMaxRad() - Config.getInstance().getMinRad()));	// 9/4*displayWidth/(config.AmountOfBalls)+i/10;//
				// (int)(random(5,10));
				Color[0]	= 50 + Math.abs(random.nextInt() % 150);// (int) random(200)%256;
				Color[1]	= 50 + Math.abs(random.nextInt() % 150);// (int) (random(200)+i)%256;
				Color[2]	= 50 + Math.abs(random.nextInt() % 150);// (int) (random(200)+2*i)%256;
				final double[] Position = new double[2];
				{
					Position[0]	= Radius + Math.abs(random.nextInt()
							% (Config.getInstance().getRightWall() - Config.getInstance().getMaxRad()));
					Position[1]	= Radius + (random.nextInt() % (Config.getInstance().getBottom() - 2 * Radius));
				}
				Balls[i] = new Ball(Position, Velocity, Radius, Color);
			}
			return (Balls);
		}
	}

	default Ball[] Interaction(Ball A, Ball B) {
		final Ball		ATmp	= A.Prediction();
		final Ball		BTmp	= B.Prediction();
		final double[]	Tmp		= getMathOperator().AdditionOfVectors(ATmp.getPosition(),
				getMathOperator().ReversalOfVector(BTmp.getPosition()));
		if (getMathOperator().Abs(Tmp[0]) <= A.getRadius() + B.getRadius()
				&& getMathOperator().Abs(Tmp[1]) <= A.getRadius() + B.getRadius()) {
			final double Condition = getMathOperator().MagnitudeOfVector(getMathOperator().AdditionOfVectors(
					getMathOperator().AdditionOfVectors(ATmp.getPosition(), ATmp.getVelocity()),
					getMathOperator().AdditionOfVectors(getMathOperator().ReversalOfVector(BTmp.getPosition()),
							getMathOperator().ReversalOfVector(BTmp.getVelocity()))));
			if (Condition <= A.getRadius() + B.getRadius()) {
				double[] connection, PointOfCollision, projectedVelocityA, projectedVelocityB = new double[2];
				connection			= getMathOperator().AdditionOfVectors(B.getPosition(),
						getMathOperator().ReversalOfVector(A.getPosition()));
				PointOfCollision	= getMathOperator().AdditionOfVectors(A.getPosition(), getMathOperator()
						.ScalarMultiplication(A.getRadius(), getMathOperator().UnitVector(connection)));
				A.setPosition(getMathOperator().ScalarMultiplication(0.5,
						getMathOperator().AdditionOfVectors(A.getPosition(),
								getMathOperator().AdditionOfVectors(PointOfCollision,
										getMathOperator().ScalarMultiplication(-A.getRadius() - 1,
												getMathOperator().UnitVector(connection))))));
				B.setPosition(getMathOperator().ScalarMultiplication(0.5,
						getMathOperator().AdditionOfVectors(B.getPosition(),
								getMathOperator().AdditionOfVectors(PointOfCollision,
										getMathOperator().ScalarMultiplication(B.getRadius() + 1,
												getMathOperator().UnitVector(connection))))));
				projectedVelocityA	= getMathOperator().Projection(A.getVelocity(),
						getMathOperator().UnitVector(connection));
				projectedVelocityB	= getMathOperator().Projection(B.getVelocity(),
						getMathOperator().UnitVector(connection));
				// double[] TmpVelA= new double[2];
				A.setVelocity(getMathOperator().AdditionOfVectors(A.getVelocity(),
						getMathOperator().ScalarMultiplication(2 * B.getMass() / (A.getMass() + B.getMass()),
								getMathOperator().AdditionOfVectors(projectedVelocityB,
										getMathOperator().ReversalOfVector(projectedVelocityA)))));
				B.setVelocity(getMathOperator().AdditionOfVectors(B.getVelocity(),
						getMathOperator().ScalarMultiplication(2 * A.getMass() / (A.getMass() + B.getMass()),
								getMathOperator().AdditionOfVectors(projectedVelocityA,
										getMathOperator().ReversalOfVector(projectedVelocityB)))));
			}
		}
		final Ball[] BALLS = new Ball[2];
		BALLS[0]	= A;
		BALLS[1]	= B;
		return BALLS;
	}

	default void MoveBalls(Ball[] Balls) {
		for (int i = 0; i < Balls.length; i++) {
			Balls[i].move();
		}
	}

}
