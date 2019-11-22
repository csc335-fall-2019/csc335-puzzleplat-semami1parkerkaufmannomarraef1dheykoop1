import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class PuzzlePlatModel extends Observable{
	ArrayList<Shape> floors = new ArrayList<>();
	
	ArrayList<Shape> obstacles = new ArrayList<>();

	ArrayList<PlayerOne> characters = new ArrayList<>();
	
	//Character player1
	private PlayerOne p;
	
	private int platformFloorY; //updated with every stage initiation
	
	//jumping stuff
	private double maxDY;
	private double gravity = 0.5;
	private boolean falling; //debatable
	
	/**
	 * getter for list of floors
	 * @return list of floors
	 */
	public ArrayList<PlayerOne> getCharacters() {
		return characters;
	}
	
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
	
	
	////// PLAYER ONE STUFF BEGIN///////
	

	public PlayerOne getP() {
		return p;
	}

	public void setP(PlayerOne p) {
		this.p = p;
	}

	public int getPlatformFloorY() {
		return platformFloorY;
	}

	public void setPlatformFloorY(int platformFloorY) {
		this.platformFloorY = platformFloorY;
	}
	
//	public void jumpPlayer() { //move to controller
//		if(getP().isCanJump()) {
//			getP().setVelocity(getP().getVelocity().add(0, -30));
//			getP().setCanJump(false);
//			}
//	}
	
	////// PLAYER ONE STUFF END///////

}
