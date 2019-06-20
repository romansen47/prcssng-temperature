package temperature;

import deprecated.math.IMathOp;
import deprecated.math.MathOp;

final public class Functions implements IFunctions {

	final static IMathOp mathOperator = MathOp.getInstance(Config.getInstance().getSquareRootCorrectness());

	private static IFunctions instance;

	private Functions() {
	}

	public static IFunctions getInstance() {
		if (instance == null) {
			return new Functions();
		}
		return instance;
	}

	@Override
	public IMathOp getMathOperator() {
		return mathOperator;
	}

}