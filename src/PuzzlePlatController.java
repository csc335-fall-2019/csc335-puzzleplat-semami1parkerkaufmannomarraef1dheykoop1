import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Optional;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import java.awt.event.KeyListener;

public class PuzzlePlatController {

	private PuzzlePlatModel model = new PuzzlePlatModel();
	private AnimationTimer at;
	private boolean gameOver = false;

	/**
	 * make all the floors for level one
	 */
	public void makeStageOneFloors() {
		model.setPlatformFloorY(211); // base floor for testing character movement

		Rectangle rect = new Rectangle(0, 250, 300, 50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(350, 250, 200, 50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(600, 250, 400, 50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(1100, 250, 100, 50);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(125, 180, 75, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		// rect = new Rectangle(375,150,50,20);
		// rect.setFill(Color.DARKOLIVEGREEN);
		// model.addFloor(rect);

		rect = new Rectangle(410, 230, 20, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(450, 140, 50, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(600, 180, 100, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(700, 230, 20, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(600, 100, 100, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(750, 140, 100, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(900, 180, 100, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(900, 100, 100, 20);
		rect.setFill(Color.DARKOLIVEGREEN);
		model.addFloor(rect);

		rect = new Rectangle(880, 230, 20, 20);
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
	 * make all the floors for level two
	 */
	public void makeStageTwoFloors() {
		model.setPlatformFloorY(211); // base floor for testing character movement

		Rectangle rect = new Rectangle(0, 250, 125, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(200, 250, 50, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(325, 250, 50, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(425, 250, 25, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(500, 250, 200, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(900, 250, 25, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(1000, 250, 200, 50);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(100, 50, 30, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(170, 50, 80, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(275, 50, 25, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(325, 50, 50, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(500, 100, 200, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(800, 100, 100, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);

		rect = new Rectangle(1000, 100, 50, 25);
		rect.setFill(Color.SADDLEBROWN);
		model.addFloor(rect);
	}

	/**
	 * make obstacles for stage2
	 */
	public void makeStageTwoObstacles() {
		Rectangle rect = new Rectangle(125, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(250, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(375, 275, 50, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(450, 275, 50, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(700, 275, 200, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(925, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);
	}

	/**
	 * maek buttons for stage2
	 */
	public void makeStageTwoButtons() {
		Rectangle rect = new Rectangle(600, 240, 20, 10);
		rect.setFill(Color.WHITE);
		model.addButton(rect);
	}

	/**
	 * make all the floors for level three
	 */
	public void makeStageThreeFloors() {
		model.setPlatformFloorY(211); // base floor for testing character movement

		Rectangle rect = new Rectangle(0, 250, 125, 50);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(200, 250, 25, 50);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(300, 240, 25, 60);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(400, 250, 25, 50);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(500, 240, 25, 60);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(600, 250, 25, 50);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(700, 240, 25, 60);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(800, 240, 25, 60);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(900, 250, 25, 50);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(1000, 240, 25, 60);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(1100, 250, 100, 50);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(200, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(300, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(400, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(500, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(600, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(700, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(800, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(900, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

		rect = new Rectangle(1000, 50, 25, 25);
		rect.setFill(Color.FIREBRICK);
		model.addFloor(rect);

	}

	public void makeStageThreeObstacles() {
		Rectangle rect = new Rectangle(125, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(225, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(325, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(425, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(525, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(625, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(725, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(825, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(925, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

		rect = new Rectangle(1025, 275, 75, 25);
		rect.setFill(Color.ORANGERED);
		model.addObstacle(rect);

	}

	/**
	 * checks if a button has been clicked
	 * 
	 * @return a boolean representing if the button has been clicked
	 */
	public boolean checkButtonClick() {
		for (Object x : model.getCharacters()) {
			for (Shape button : model.getButtons()) {
				// System.out.println(x.getY() + " " + ((Rectangle) button).getY());
				if (button instanceof Rectangle) {
					if (((Dialog<ButtonType>) x).getX() >= (((Rectangle) button).getX()) - 30
							&& ((Dialog<ButtonType>) x).getX() <= (((Rectangle) button).getX())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * make all the rain
	 * 
	 * @param min minimum number of rain drops
	 * @param max maximum number of rain drops
	 */
	public void makeRain(int min, int max) {
		myCircle circ;
		int numCircles = randomNum(min, max);

		for (int i = 0; i < numCircles; i++) {
			circ = new myCircle(randomNum(100, 1100), 0, 5, randomNum(1, 2));
			circ.setFill(Color.DARKBLUE);
			model.addObstacle(circ);
		}
	}

	/**
	 * get a random number between min and max (inclusive)
	 * 
	 * @param min minimum number
	 * @param max maximum number
	 * @return a random number between min and max
	 */
	public int randomNum(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}

	/**
	 * add observer to observable
	 * 
	 * @param o observer
	 */
	public void addObserver(Observer o) {
		model.addObserver(o);
	}

	/**
	 * getter for list of floors
	 * 
	 * @return list of floors
	 */
	public ArrayList<Shape> getFloors() {
		return model.getFloors();
	}

	/**
	 * getter for list of obstacles
	 * 
	 * @return list of obstacles
	 */
	public ArrayList<Shape> getObstacles() {
		return model.getObstacles();
	}

	/**
	 * getter for list of buttons
	 * 
	 * @return list of buttons
	 */
	public ArrayList<Shape> getButtons() {
		return model.getButtons();
	}

	/**
	 * Begins the game by setting up a timer that calls tick() 60 times per second.
	 */
	public void start() {
		int ticksPerFrame = 1;
		at = new AnimationTimer() {
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
		ArrayList<ArrayList<? extends Object>> state = new ArrayList<ArrayList<? extends Object>>();// state of the
																									// character,
																									// obstacles, and
																									// floor

		// If there's a collision, we should check what direction it was going last so
		// we can go a different direction.
		if (((PlayerOne) model.characters.get(0)).isCancelJump()
				&& ((PlayerOne) model.characters.get(0)).getLastMove().equals(KeyCode.UP)) {
			cancelJumpP1();

		}

		if (isCollision()) {
			if (((PlayerOne) model.characters.get(0)).getLastMove().equals(KeyCode.RIGHT)) {

				if (model.getP1().isCanMoveLeft()) { // && !isCollision()) {
					movePlayer();
					moveRightP1();
				}
				if (model.getP1().isCanJump()) {
					movePlayer();
					playerJumpP1();
				}
			} else if (((PlayerOne) model.characters.get(0)).getLastMove().equals(KeyCode.LEFT)) {

				if (model.getP1().isCanMoveRight()) { // && !isCollision()) {
					movePlayer();
					moveRightP1();
				}
				if (model.getP1().isCanJump()) {
					movePlayer();
					playerJumpP1();
				}

			}
		}

		// If there aren't collisions, should be free to move anywhere
		else {
			movePlayer();
			if (model.getP1().isCanMoveRight()) { // && !isCollision()) {
				moveRightP1();
			}
			if (model.getP1().isCanMoveLeft()) {
				moveLeftP1();
			}
			if (model.getP1().isCanJump()) {
				playerJumpP1();
			}
		}
		if (getP1().getLastMove() != null) {
			if (!getP1().getLastMove().equals(KeyCode.UP) || noMovement())
				bringToFloor();
		}

		// moveEnemies();
		moveRain();
		checkForDeath();

		// checkForWin();
		state.add(model.getFloors());
		state.add(model.getObstacles());

		state.add(model.getCharacters());
		state.add(model.getButtons());

		model.update();
		model.notifyObservers(state);
	}

	private void movePlayer() {
		model.getP1().setX(model.getP1().getX() + model.getP1().getVelX());
		model.getP1().setY(model.getP1().getY() + model.getP1().getVelY());
	}

	/**
	 * update location of the rain
	 */
	private void moveRain() {
		myCircle newCirc;
		ArrayList<Shape> newRain = new ArrayList<>();

		for (Shape shape : model.getObstacles()) {
			if (shape instanceof myCircle && ((Circle) shape).getCenterY() + ((Circle) shape).getRadius() < 275
					&& !endRain(shape)) {// limits amount of rain in game
				newCirc = new myCircle(((myCircle) shape).getCenterX(),
						((myCircle) shape).getCenterY() + ((myCircle) shape).getMoveSpeed(),
						((myCircle) shape).getRadius(), ((myCircle) shape).getMoveSpeed());
				newCirc.setFill(((myCircle) shape).getFill());
				newRain.add(newCirc);
			} else if (shape instanceof Rectangle) {// if its not rain then ignore
				newRain.add(shape);
			}
		}

		model.setAllObstacles(newRain);

	}

	/**
	 * checks if rain has hit a floor
	 * 
	 * @param shape rain
	 * @return boolean representing if rain is touching a floor
	 */
	private boolean endRain(Shape shape) {
		if (shape instanceof myCircle) {
			for (Shape floor : model.getFloors()) {
				if (floor instanceof Rectangle) {
					if (((myCircle) shape).getCenterY() == ((Rectangle) floor).getY()
							&& ((myCircle) shape).getCenterX() >= ((Rectangle) floor).getX() && ((myCircle) shape)
									.getCenterX() <= ((Rectangle) floor).getX() + ((Rectangle) floor).getWidth()) {
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
		PlayerOne p1 = new PlayerOne(30, 210);
		model.setP1(p1);
		model.characters.add(model.getP1());
	}

	/**
	 * 
	 * @return player one
	 */
	public PlayerOne getP1() {
		return model.getP1();
	}

	/**
	 * called every tick(), replaces rendered image with sprite frame every 5 ticks
	 * for right movements
	 */
	public void moveRightP1() {
		if (getP1().getPlayerImgNumber() < 5) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpR1());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 5 && getP1().getPlayerImgNumber() < 10) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpR2());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 10 && getP1().getPlayerImgNumber() < 15) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpR3());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 15 && getP1().getPlayerImgNumber() < 20) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpR4());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 20 && getP1().getPlayerImgNumber() < 25) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpR5());
			getP1().setPlayerImgNumber(0);
		}
	}

	/**
	 * Determines if there is a collision between the player and obstacles. The
	 * floor is ignored here.
	 * 
	 * @return whether or not there is a collision between player and an obstacle
	 */
	public boolean isCollision() {
		// add player two checks TODO
		PlayerOne player = (PlayerOne) model.getCharacters().get(0);
		double player_width = player.getPlayerImg().getWidth();
		double player_height = 49;
		double player_x = player.getX();
		double player_y = player.getY();
		if ((player_x < 0) || (player_x + player_width) > 1250) {
			return true;
		}
		ArrayList<Shape> obstacles = model.getFloors();
		for (Shape s : obstacles) {
			if (s instanceof Rectangle) {
				double height = ((Rectangle) s).getHeight();
				double width = ((Rectangle) s).getWidth();
				double x = ((Rectangle) s).getX();
				double y = ((Rectangle) s).getY();
				if (y < 250) {
					Collision new_collision = new Collision(player_x, player_y, player_height, player_width, x, y,
							height, width);
					if (new_collision.isCollision()) {
						getP1().setCancelJump(true);
						return true;
					}
				}

			}
			// if (s instanceof myCircle){
			// }
		}
		return false;
	}

	/**
	 * When the character is standing in the crack above the lava, we need to bring
	 * him down towards the lava.
	 * 
	 */
	public void bringToFloor() {
		// add player two stuff TODO
		PlayerOne player = getP1();
		ArrayList<Shape> floors = getFloors();
		double player_width = player.getPlayerImg().getWidth();
		double player_height = player.getPlayerImg().getHeight();
		double player_x = player.getX();
		double player_y = player.getY();

		ArrayList<Shape> obstacles = getObstacles();
		for (Shape s : obstacles) {
			if (s instanceof Rectangle) {
				double height = ((Rectangle) s).getHeight();
				double width = ((Rectangle) s).getWidth();
				double x = ((Rectangle) s).getX();
				double y = ((Rectangle) s).getY();
				if (player_x + 20 > x && ((player_x + player_width) - 20) < (x + width)) {
					// Bring back down gradually - should go down when the character is not
					// moving or trying to walk over the lava
					if (noMovement() || getP1().movingLeft() || getP1().movingRight())
						;
					player.setY(player.getY() + 2);
				}
			}
		}

	}

	/**
	 * Tells if the character is still.
	 * 
	 * @return boolean that tells us if the character is not moving
	 */
	public boolean noMovement() {
		// add player two stuff TODO
		return !getP1().movingRight() && !getP1().movingLeft();
	}

	/**
	 * Checks whether there is a collision between the character and a block of
	 * lava. This will then set variables to end the game.
	 */
	public void checkForDeath() {
		// add PLAYER two stuff TODO
		PlayerOne player = getP1();
		ArrayList<Shape> floors = getFloors();
		double player_width = player.getPlayerImg().getWidth();
		double player_height = player.getPlayerImg().getHeight();
		double player_x = player.getX();
		double player_y = player.getY();
		ArrayList<Shape> obstacles = getObstacles();
		for (Shape s : obstacles) {
			if (s instanceof Rectangle) {
				double height = ((Rectangle) s).getHeight();
				double width = ((Rectangle) s).getWidth();
				double x = ((Rectangle) s).getX();
				double y = ((Rectangle) s).getY();
				Collision new_col = new Collision(player_x, player_y, player_height, player_width, x, y, height, width);
				if (new_col.isCollision()) {
					// Sets the player at a reasonable height, but causes a little jump when
					// the game ends and the show window pops up
					player.setY(y - 45);
					player.setInLava(true);
					gameOver = true;
				}
			}
		}
	}

	/**
	 * Tells whether the game is over or not.
	 * 
	 * @return boolean that is true if the game is over.
	 */
	public boolean isGameOver() {
		// add Player two stuff ? TODO
		return gameOver || getP1().getX() + getP1().getPlayerImg().getWidth() > 1220;
	}

	/**
	 * called every tick(), replaces rendered image with sprite frame every 5 ticks
	 * for left movements
	 */
	public void moveLeftP1() {
		if (getP1().getPlayerImgNumber() < 5) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpL1());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 5 && getP1().getPlayerImgNumber() < 10) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpL2());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 10 && getP1().getPlayerImgNumber() < 15) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpL3());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 15 && getP1().getPlayerImgNumber() < 20) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpL4());
			getP1().setPlayerImgNumber(getP1().getPlayerImgNumber() + 1);
		} else if (getP1().getPlayerImgNumber() >= 20 && getP1().getPlayerImgNumber() < 25) {
			((PlayerOne) model.characters.get(0)).SetPlayerImg(model.getP1().getPpL5());
			getP1().setPlayerImgNumber(0);
		}
	}

	/**
	 * sets flag on keyboard input
	 * 
	 * @param canMove
	 */
	public void setCanMoveRightP1(boolean canMove) {
		this.model.getP1().setCanMoveRight(canMove);
	}

	/**
	 * sets flag on keyboard input
	 * 
	 * @param canMove
	 */
	public void setCanMoveLeftP1(boolean canMove) {
		this.model.getP1().setCanMoveLeft(canMove);
	}

	/**
	 * called every tick() if jumpFlag is true. makes player jump
	 */
	public void playerJumpP1() {
		getP1().setY(getP1().getY() - getP1().getJumpStrength());

		getP1().setJumpStrength(getP1().getJumpStrength() - getP1().getWeight());

		// Collision new_collision = floorCollision();

		if (getP1().getY() >= this.model.getPlatformFloorY()) {
			// || collision with floor
			getP1().setY(this.model.getPlatformFloorY());
			setCanJumpP1(false);
			getP1().setJumpStrength(13);
		}
		/*
		 * else if(new_collision != null) { if (getP1().getY() >= new_collision.getY2())
		 * { System.out.println("floor collision at " + new_collision.getY2());
		 * getP1().setY(new_collision.getY2()); setCanJump(false);
		 * getP1().setJumpStrength(13); } }
		 */
	}

	public Collision floorCollision() {
		PlayerOne player = getP1();
		ArrayList<Shape> floors = getFloors();
		double player_width = player.getPlayerImg().getWidth();
		double player_height = 49;
		double player_x = player.getX();
		double player_y = player.getY();
		Collision new_col;
		for (Shape s : floors) {
			if (s instanceof Rectangle) {
				double height = ((Rectangle) s).getHeight();
				double width = ((Rectangle) s).getWidth();
				double x = ((Rectangle) s).getX();
				double y = ((Rectangle) s).getY();
				new_col = new Collision(player_x, player_y, player_height, player_width, x, y, height, width);
				if (new_col.isCollision()) {
					return new_col;
				}
			}
		}
		return null;
	}

	/**
	 * Handles the collision that happens when the character's head hits the bottom
	 * of one of the obstacles
	 * 
	 */
	public void cancelJumpP1() {
		if (!getP1().isCanJumpAgain()) {
			getP1().setJumpStrength(0);
		}

		Collision new_col = floorCollision();
		if (new_col != null) {
			if (new_col.getY1() < new_col.getY2())
				// System.out.println(new_col.getY1() + " " + new_col.getY2());
				getP1().setY(new_col.getY2() - new_col.getHeight1());

			// Not important but a NOTE: making setCanJump here lets you jump on top
			// like a trampoline
			setCanJumpP1(false);

			setCancelJumpP1(false);
			getP1().setCanJumpAgain(false);
			getP1().setJumpStrength(13);
			return;

		}

		getP1().setCanJumpAgain(true);

		getP1().setY(getP1().getY() - getP1().getJumpStrength());

		getP1().setJumpStrength(getP1().getJumpStrength() - getP1().getWeight());

		if (getP1().getY() >= this.model.getPlatformFloorY()) {
			getP1().setY(this.model.getPlatformFloorY());
			setCanJumpP1(false);
			setCancelJumpP1(false);
			getP1().setCanJumpAgain(false);
			getP1().setJumpStrength(13);
		}

	}

	/**
	 * Sets the cancelJump flag to b
	 * 
	 * @param b true if we need to cancel a jump
	 */
	private void setCancelJumpP1(boolean b) {
		this.model.getP1().setCancelJump(b);
	}

	/**
	 * sets character jump flag
	 * 
	 * @param b
	 */
	public void setCanJumpP1(boolean b) {
		this.model.getP1().setCanJump(b);
	}

	/**
	 * returns the platform's base floor (for testing purposes)
	 * 
	 * @return base floor
	 */
	public int getPlatformFloor() {
		return this.model.getPlatformFloorY();
	}

	/**
	 * Stops the animation from running. I did this because I couldn't produce the
	 * show view without stopping the animation.
	 */
	public void stop() {
		at.stop();

	}

	/**
	 * add a new floor piece
	 * 
	 * @param rect
	 */
	public void addFloor(Shape rect) {
		model.addFloor(rect);
	}

}
