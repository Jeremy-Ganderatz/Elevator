package objects;

public class Elevator {

	private int totalFloors = Building.getTotalfloors();

	private static Elevator elevator = null;
	private static int maximumUsers = 5;
	private boolean enginePower; // unactive
	private boolean securityBrake; // unactive
	private boolean positionDoor; // closed
	private String controlPanel[] = new String[totalFloors];

	public Elevator() {

	}

	public static Elevator getInstance() {
		if (elevator == null) {
			elevator = new Elevator();
		}
		return elevator;
	}

	public boolean isPositionDoor() {
		return positionDoor;
	}

	public void setPositionDoor(boolean positionDoor) {
		this.positionDoor = positionDoor;
	}

	public String[] getControlPanel() {
		return controlPanel;
	}

	public String[] setControlPanel(int totalStairs, int bottomStair) {
		int nb = bottomStair;
		String controlPanel[] = new String[totalStairs];
		for (int i = 0; i < totalStairs; i++) {
			controlPanel[i] = String.valueOf(nb);
			nb++;
		}
		return controlPanel;
	}

	public static int getMaximumUsers() {
		return maximumUsers;
	}

	public boolean isEnginePower() {
		return enginePower;
	}

	public void setEnginePower(boolean enginePower) {
		this.enginePower = enginePower;
	}

	public boolean isSecurityBrake() {
		return securityBrake;
	}

	public void setSecurityBrake(boolean securityBrake) {
		this.securityBrake = securityBrake;
	}

}
