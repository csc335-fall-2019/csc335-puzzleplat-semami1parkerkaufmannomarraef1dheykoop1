import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import java.awt.event.KeyListener;

public class PuzzlePlatController {
	
	private PuzzlePlatModel model = new PuzzlePlatModel();
	
	
	
	/**
	 * make all the floors for level one
	 */
	public void makeStageOneFloors() {
		model.setPlatformFloorY(201); //base floor for testing character movement
		
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
		
		if (!isCollision())
			movePlayer();
		
		if(model.getP().isCanMoveRight() && !isCollision()) { //&& !isCollision()) {
			moveRight();
		}
		if(model.getP().isCanMoveLeft() && !isCollision()) {
			moveLeft();
		}
		if(model.getP().isCanJump() && !isCollision()) {
			playerJump();
		}
		
		//moveEnemies();
		moveRain();
		//checkForDeath();
		//checkForWin();
		state.add(model.getFloors());
		state.add(model.getObstacles());
		
		state.add(model.getCharacters());
		
		model.update();
		model.notifyObservers(state);
	}
	
	private void movePlayer() {
		model.getP().setX(model.getP().getX() + model.getP().getVelX());
		model.getP().setY(model.getP().getY() + model.getP().getVelY());
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
	
	
	/**
	 * Creates Player One and adds them to character Array
	 */
	public void createPlayerOne() {
		PlayerOne p1 = new PlayerOne(30, 200);
		model.setP(p1);
		model.characters.add(model.getP());
	}
	
	/**
	 * 
	 * @return player one
	 */
	public PlayerOne getP1() {
		return model.getP();
	}
	
	/**
	 * called every tick(), replaces rendered image
	 * with sprite frame every 5 ticks
	 * for right movements
	 */
	public void moveRight() {
		if(getP1().getPlayerImgNumber()<5) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR1());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=5 && getP1().getPlayerImgNumber()<10) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR2());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=10 && getP1().getPlayerImgNumber()<15) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR3());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=15 && getP1().getPlayerImgNumber()<20) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR4());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=20 && getP1().getPlayerImgNumber()<25) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR5());
			getP1().setPlayerImgNumber(0);
		}
	}
	
	public boolean isCollision() {
		PlayerOne player = model.getCharacters().get(0);
		double player_width = player.getPlayerImg().getWidth();
		double player_height = player.getPlayerImg().getHeight();
		double player_x = player.getX();
		double player_y = player.getY();
		ArrayList<Shape> obstacles = model.getFloors();
		for (Shape s: obstacles) {
			 if (s instanceof Rectangle) {
				 double height = ((Rectangle)s).getHeight();
				 double width = ((Rectangle)s).getWidth();
				 double x = ((Rectangle)s).getX();
				 double y = ((Rectangle)s).getY();
				 
				 if (y < 250) {
					 // Checks if the right side of the player hits an obstacle
					 if ((player_x) < (x + width) && (player_x + player_width) > x
							 && (player_y) < (y+height) && (player_y + player_height) > y) {
						 //System.out.println("collision");
						 return true;
					 }
					 if ((player_x + player_width) > x && (player_x + player_width) < (x+width) 
							 && (player_y) < (y+height) && (player_y + player_height) > y) {
						 System.out.println("Right collision");
					 }
					 /**
					 // Checks if left side of the player hits an obstacle
					 if ((player_x) <= (x+width) && (player_x) >= x
							 && (player_y + player_height) <= (y+height) && (player_y + player_height) >= y) {
						 return true;
					 }
					 
					 //Checks if top of character has a collision
					 // Might have to move this somewhere different to handle jumping
					 /**
					 if (player_y <= y && ((player_x >= x && player_x <= x+width) 
							 || (player_x + player_width) >= x && (player_x + player_width) <= (x+width))) {
						 return true;
						 
					 }
					 */
					 // Check these things:
					 // right side of character touching obstacle
					 // left side of character touching obstacle
					 // top of character touching obstacle
				 }
				 
			 }
			// Check all sides of character with all sides of the obstacles
			// if overlap exist return true 
		}
		return false;
	}

	/**
	 * called every tick(), replaces rendered image
	 * with sprite frame every 5 ticks
	 * for left movements
	 */
	public void moveLeft() {
		if(getP1().getPlayerImgNumber()<5) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL1());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=5 && getP1().getPlayerImgNumber()<10) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL2());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=10 && getP1().getPlayerImgNumber()<15) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL3());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=15 && getP1().getPlayerImgNumber()<20) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL4());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		}
		else if(getP1().getPlayerImgNumber()>=20 && getP1().getPlayerImgNumber()<25) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL5());
			getP1().setPlayerImgNumber(0);
		}
	}

	/**
	 * sets flag on keyboard input
	 * @param canMove
	 */
	public void setCanMoveRight(boolean canMove) {
		this.model.getP().setCanMoveRight(canMove);
	}
	
	/**
	 * sets flag on keyboard input
	 * @param canMove
	 */
	public void setCanMoveLeft(boolean canMove) {
		this.model.getP().setCanMoveLeft(canMove);
	}
	
	/**
	 * called every tick() if jumpFlag is true.
	 * makes player jump
	 */
	public void playerJump() {
		getP1().setY(getP1().getY()-getP1().getJumpStrength());
		
		getP1().setJumpStrength(getP1().getJumpStrength() - getP1().getWeight());
		
		if(getP1().getY() >= this.model.getPlatformFloorY()) {
			getP1().setY(this.model.getPlatformFloorY());
			setCanJump(false);
			getP1().setJumpStrength(13);
		}
	}

	/**
	 * sets character jump flag
	 * @param b
	 */
	public void setCanJump(boolean b) {
		this.model.getP().setCanJump(b);
	}
	/**
	 * returns the platform's base floor
	 * (for testing purposes)
	 * @return base floor
	 */
	public int getPlatformFloor() {
		return this.model.getPlatformFloorY();
	}
	
}
