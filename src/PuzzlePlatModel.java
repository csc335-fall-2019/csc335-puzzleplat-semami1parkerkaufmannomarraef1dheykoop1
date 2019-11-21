import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.shape.Shape;

public class PuzzlePlatModel extends Observable{
	ArrayList<Shape> floors = new ArrayList<>();
	ArrayList<Shape> obstacles = new ArrayList<>();
	//Character player1
	
	/**
	 * getter for list of floors
	 * @return list of floors
	 */
	public ArrayList<Shape> getFloors() {
		return floors;
	}
	
	/**
	 * getter for list of obstacles
	 * @return list of obstacles
	 */
	public ArrayList<Shape> getObstacles() {
		return obstacles;
	}
	
	/**
	 * add a floor to the list of floors
	 * @param floor to be added
	 */
	public void addFloor(Shape floor) {
		floors.add(floor);
	}
	
	/**
	 * add a list of obstacles to the list of obstacles
	 * @param obstacle to be added
	 */
	public void addObstacle(Shape obstacle) {
		obstacles.add(obstacle);
	}
	
	/**
	 * tells model that changes have been made.
	 */
	public void update() {
		setChanged();
	}
	
	/**
	 * set new obstacles. this is mostly used for moving rain.
	 * @param newObstacles list of new objects.
	 */
	public void setAllObstacles(ArrayList<Shape> newObstacles) {
		obstacles = newObstacles;
	}
	
	

}
