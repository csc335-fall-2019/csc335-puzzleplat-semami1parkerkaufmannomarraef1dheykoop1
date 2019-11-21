import java.util.ArrayList;
import java.util.Observer;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class PuzzlePlatController {
	
	private PuzzlePlatModel model = new PuzzlePlatModel();
	
	/**
	 * make all the floors for level one
	 */
	public void makeStageOneFloors() {
		
		Rectangle rect = new Rectangle(0,250,300,50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(350,250,200,50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(600,250,400,50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(1100,250,100,50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(125,180,75,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(375,150,50,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(390,230,20,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(450,180,50,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(600,180,100,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(700,230,20,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(600,100,100,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(750,140,100,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(900,180,100,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(900,100,100,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
		rect = new Rectangle(880,230,20,20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);
		
	}
	
	public void makeStageOneObstacles() {
		Rectangle rect = new Rectangle(300, 275, 50, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);
		
		rect = new Rectangle(550, 275, 50, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);
		
		rect = new Rectangle(1000, 275, 100, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);
	}
	
	/**
	 * make all the rain
	 * @param min minimum number of rain drops
	 * @param max maximum number of rain drops
	 */
	public void makeRain(int min, int max) {
		myCircle circ;
		int numCircles = randomNum(min, max);
		
		for(int i = 0; i < numCircles; i++) {
			circ = new myCircle(randomNum(100, 1100), 0, 5, randomNum(1,2));
			circ.setFill(Color.DARKBLUE);
			model.addObstacle(circ);
		}
	}
	
	/**
	 * get a random number between min and max (inclusive)
	 * @param min minimum number
	 * @param max maximum number
	 * @return a random number between min and max
	 */
	public int randomNum(int min, int max) {
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
	/**
	 * add observer to observable
	 * @param o observer
	 */
	public void addObserver(Observer o) {
		model.addObserver(o);
	}
	
	/**
	 * getter for list of floors
	 * @return list of floors
	 */
	public ArrayList<Shape> getFloors() {
		return model.getFloors();
	}
	
	/**
	 * getter for list of obstacles
	 * @return list of obstacles
	 */
	public ArrayList<Shape> getObstacles() {
		return model.getObstacles();
	}
	
	/**
	* Begins the game by setting up a timer that calls tick() 60 times per second.
	*/
	public void start() {
		int ticksPerFrame = 1;
		AnimationTimer at = new AnimationTimer() {
		@Override
		public void handle(long now) {
			// perform ticksPerFrame ticks
			// by default this is 1
				for (int i = 0; i < ticksPerFrame; i++) {
					tick();
				}
			}
		};
		at.start();
	}
	
	/**
	* tick() performs one iteration of the game, and notifies observers to draw a
	* frame. This should be called 60 times per second by default.
	*/
	public void tick() {
		ArrayList<ArrayList<? extends Object>> state = new ArrayList<ArrayList<? extends Object>>();//state of the character, obstacles, and floor
		//movePlayer();
		//moveEnemies();
		moveRain();
		//checkForDeath();
		//checkForWin();
		state.add(model.getFloors());
		state.add(model.getObstacles());
		//state.add(model.getCharacter);
		model.update();
		model.notifyObservers(state);
	}
	
	/**
	 * update location of the rain
	 */
	private void moveRain() {
		myCircle newCirc;
		ArrayList<Shape> newRain = new ArrayList<>();
		
		for(Shape shape: model.getObstacles()) {
			if(shape instanceof myCircle && ((Circle) shape).getCenterY() 
					+ ((Circle) shape).getRadius()< 275
					&& !endRain(shape)) {//limits amount of rain in game
				newCirc = new myCircle(((myCircle) shape).getCenterX(), 
						((myCircle) shape).getCenterY() + ((myCircle) shape).getMoveSpeed(), 
						((myCircle) shape).getRadius(), ((myCircle) shape).getMoveSpeed());
				newCirc.setFill(((myCircle) shape).getFill());
				newRain.add(newCirc);
			}else if(shape instanceof Rectangle) {//if its not rain then ignore
				newRain.add(shape);
			}
		}
		
		model.setAllObstacles(newRain);
		
	}
	
	/**
	 * checks if rain has hit a floor
	 * @param shape rain
	 * @return boolean representing if rain is touching a floor
	 */
	private boolean endRain(Shape shape) {
		if(shape instanceof myCircle) {
			for(Shape floor: model.getFloors()) {
				if(floor instanceof Rectangle) {
					if(((myCircle) shape).getCenterY() == ((Rectangle) floor).getY()
							&& ((myCircle) shape).getCenterX() >= ((Rectangle) floor).getX() 
							&& ((myCircle) shape).getCenterX() <= ((Rectangle) floor).getX() 
							+ ((Rectangle) floor).getWidth()) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
}
