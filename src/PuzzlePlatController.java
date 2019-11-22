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
	
	private boolean canMoveRight = false;
	
	/**
	 * make all the floors for level one
	 */
	public void makeStageOneFloors() {
		model.setPlatformFloorY(39); //base floor for testing character movement
		
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
		
		movePlayer();
		if(canMoveRight) {
			moveRight();
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
	
	
//////PLAYER ONE STUFF BEGIN///////
//	
//	public void initPlayerOneAgain(GraphicsContext g) {
//		final ImageView imageView = new ImageView("Elf2.png");
//		imageView.setViewport(new Rectangle2D(10, 10, 10, 10));
////	
////		final Animation animation = new SpriteAnimation(
////				imageView,
////				Duration.millis(1000),
////				10, 4,
////				10, 10, 50, 50);
////		animation.setCycleCount(Animation.INDEFINITE);
////		animation.play();
//		g.drawImage(imageView.getImage(), 40, 200);
//		
//	}
	
	public void createPlayerOne() {
		PlayerOne p1 = new PlayerOne(40,200);
		model.setP(p1);
		model.characters.add(model.getP());
	}
	
	public PlayerOne getP1() {
		return model.getP();
	}
	
	public void moveRight() {
		Random rand = new Random();
		int randy = rand.nextInt(3);
		if(randy == 0) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR1());
		}
		else if(randy == 1) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR3());
		}
		else if(randy == 2) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpR5());
		}
//		else if(randy == 3) {
//			model.characters.get(0).SetPlayerImg(model.getP().getPpR4());
//		}
//		else if(randy == 4) {
//			model.characters.get(0).SetPlayerImg(model.getP().getPpR5());
//		}
//		//model.getP()
		//model.characters.get(0).SetPlayerImg(model.getP().getPpR3());
//		Timeline t = new Timeline();
//		t.setCycleCount(Timeline.INDEFINITE);
//		
//		
////		t.getKeyFrames().add(new KeyFrame(
////				Duration.millis(1000),
////				(ActionEvent e) -> {
////			model.getP().getRightGroup().getChildren().setAll(model.getP().getPpR3());
////		}));
//		t.play();
	}
	

	public void moveLeft() {
		Random rand = new Random();
		int randy = rand.nextInt(5);
		if(randy == 0) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL1());
		}
		else if(randy == 1) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL2());
		}
		else if(randy == 2) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL3());
		}
		else if(randy == 3) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL4());
		}
		else if(randy == 4) {
			model.characters.get(0).SetPlayerImg(model.getP().getPpL5());
		}
//		//model.getP()
		//model.characters.get(0).SetPlayerImg(model.getP().getPpR3());
//		Timeline t = new Timeline();
//		t.setCycleCount(Timeline.INDEFINITE);
//		
//		
////		t.getKeyFrames().add(new KeyFrame(
////				Duration.millis(1000),
////				(ActionEvent e) -> {
////			model.getP().getRightGroup().getChildren().setAll(model.getP().getPpR3());
////		}));
//		t.play();
	}

	public boolean isCanMoveRight() {
		return canMoveRight;
	}

	public void setCanMoveRight(boolean canMove) {
		this.canMoveRight = canMove;
	}
	
}
