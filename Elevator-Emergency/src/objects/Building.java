package objects;

public class Building {

	private static final int positivesFloors = 15;
	private static final int startPosition = 0;
	private static final int negativesFloors = -2;
	private static final int totalFloors = positivesFloors + Math.abs(negativesFloors) + 1;

	public static int getPositivesfloors() {
		return positivesFloors;
	}

	public static int getNegativesfloors() {
		return negativesFloors;
	}

	public static int getTotalfloors() {
		return totalFloors;
	}

	public static int getStartposition() {
		return startPosition;
	}

}
