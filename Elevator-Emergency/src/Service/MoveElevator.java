package Service;

import java.util.Timer;
import java.util.TimerTask;

import objects.Elevator;

public abstract class MoveElevator implements Runnable {

	public static void goUp(int floorToReach, int yourFloor, int[] users) {
		Timer timer = new Timer();
		int begin = 1500;
		int timeInterval = 1000;
		timer.schedule(new TimerTask() {
			int counter = 0;
			int actualFloor = yourFloor;

			@Override
			public void run() {
				if (checkingOtherUsersOrders(users, actualFloor)) {
					OthersUsersGoingOut(actualFloor);
				} else {
					System.out.println("floor : " + actualFloor);
				}
				actualFloor++;

				if (counter == Math.abs(yourFloor - floorToReach)) {
					MainUserReachHisFloor(floorToReach);
					timer.cancel();
				}
				counter++;
			}
		}, begin, timeInterval);
	}

	public static void goDown(int floorToReach, int yourFloor, int[] users) {
		Timer timer = new Timer();
		int begin = 1000;
		int timeInterval = 1000;
		timer.schedule(new TimerTask() {
			int counter = 0;
			int actualFloor = yourFloor;

			@Override
			public void run() {
				if (checkingOtherUsersOrders(users, actualFloor) == true) {
					OthersUsersGoingOut(actualFloor);
				} else {
					System.out.println("floor : " + actualFloor);
				}

				actualFloor--;

				if (counter == Math.abs(yourFloor - floorToReach)) {
					MainUserReachHisFloor(floorToReach);
					timer.cancel();
				}
				counter++;
			}
		}, begin, timeInterval);
	}

	public static boolean checkingOtherUsersOrders(int[] board, int actualFloor) {
		for (int i : board)
			if (i == actualFloor) {
				return true;
			}
		return false;
	}

	public static void OthersUsersGoingOut(int actualFloor) {
		while (!DoorCanBeOpen()) {
			ProtocolWhenElevatorStop();
		}
		System.out.println("---///Door is open///-- User(s) is getting out at floor " + actualFloor + " /// Bye!!");
		while (!ElevatorCanStart()) {
			ProtocolWhenElevatorStart();
		}
		System.out.println("---///Door is closed///---");
	}

	public static void MainUserReachHisFloor(int floorToReach) {
		while (!DoorCanBeOpen()) {
			ProtocolWhenElevatorStop();
		}
		System.out.println("---///Door is open///---");
		System.out.println("YOU ARRIVE AT FLOOR NUMBER : " + floorToReach + "  --- Bye Bye !!! ---");
		System.out.println("---///Door is closed///---");
	}

	static Elevator elevatorInstance = Elevator.getInstance();

	public static void ProtocolWhenElevatorStart() {
		elevatorInstance.setPositionDoor(false);
		elevatorInstance.setSecurityBrake(false);
		elevatorInstance.setEnginePower(true);
	}

	public static void ProtocolWhenElevatorStop() {
		elevatorInstance.setEnginePower(false);
		elevatorInstance.setSecurityBrake(true);
		elevatorInstance.setPositionDoor(true);
	}

	public static boolean ElevatorCanStart() {
		if (!CheckingBrakePosition() && CheckingEnginePosition() && !CheckingPositionDoor()) {
			return true;
		}
		return false;
	}

	public static boolean DoorCanBeOpen() {
		if (CheckingBrakePosition() && !CheckingEnginePosition()) {
			return true;
		}
		return false;
	}

	public static boolean CheckingPositionDoor() {
		if (elevatorInstance.isPositionDoor()) {
			return true;
		}
		return false;
	}

	public static boolean CheckingEnginePosition() {
		if (elevatorInstance.isEnginePower()) {
			return true;
		}
		return false;
	}

	public static boolean CheckingBrakePosition() {
		if (elevatorInstance.isSecurityBrake()) {
			return true;
		}
		return false;
	}

}
