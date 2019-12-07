import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Observable;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PuzzlePlatView extends Application implements java.util.Observer{
	
	PuzzlePlatController controller = new PuzzlePlatController();
	GraphicsContext gc;
	String level;
	Group root;
	private boolean bridgeStageTwoDrawn = false; //has the bridge been made?
	private boolean consumed = false;
	Image image1 = new Image(getClass().getResourceAsStream("background.png"));
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller.start();//starts game clock
		controller.addObserver(this);
		Canvas canvas = null;
		
		
		if(level.toLowerCase().equals("tutorial")) { //if its tutorial
			primaryStage.setTitle("Tutorial Stage");
			canvas = new Canvas(1200,300);
			gc = canvas.getGraphicsContext2D();

			gc.setFill(new ImagePattern(image1));
			gc.fillRect(0, 0, 1200, 300);
			
			controller.makeStageOneFloors();//sets up level
			drawShapes(controller.getFloors());
			
			controller.makeRain(0, 1);//makes rain
			controller.makeStageOneObstacles();
			drawShapes(controller.getObstacles());
			
			drawTutorialText();
			
			controller.createPlayerOne(); //create character 1
		}else if(level.toLowerCase().equals("level 1")) {
			primaryStage.setTitle("Level 1");
			canvas = new Canvas(1200,300);
			gc = canvas.getGraphicsContext2D();
			
			gc.setFill(Color.PINK);
			gc.fillRect(0, 0, 1200, 300);
			
			controller.makeRain(0, 1);//makes rain
			controller.makeStageTwoObstacles();
			drawShapes(controller.getObstacles());
			
			controller.makeStageTwoFloors();//sets up level
			drawShapes(controller.getFloors());
			
			controller.makeStageTwoButtons();
			drawShapes(controller.getButtons());
			
			controller.createPlayerOne(); //create character 1
		}else {//level 2
			primaryStage.setTitle("Level 2");
			canvas = new Canvas(1200,300);
			gc = canvas.getGraphicsContext2D();
			
			gc.setFill(Color.AQUA);
			gc.fillRect(0, 0, 1200, 300);
			controller.makeRain(0, 2);//makes rain
			
			controller.makeStageThreeObstacles();
			drawShapes(controller.getObstacles());
			
			controller.makeStageThreeFloors();//sets up level
			drawShapes(controller.getFloors());
			
			controller.createPlayerOne(); //create character 1
		}
		
		gc.drawImage(new Image("door.png"), 1150, 100,50,150); //draw exit door
		// print coordinates wherever you click, for testing purposes//
		EventHandler<MouseEvent> mooso = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println("y = "+event.getY());
				System.out.println("x = "+event.getX());	
			}
		};
		primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, mooso);
		//////////
		
		// character 1 handlers //
		EventHandler<KeyEvent> keyPressedNav = new EventHandler<KeyEvent>() {
			int jmpCnt = 0;
			@Override
			public void handle(KeyEvent e) {
				consumed = false;
				KeyCode key = e.getCode();
				
				if (controller.onFloor()) {
					jmpCnt = 0;
				}
				/*
				if(controller.getP1().getY()==controller.getPlatformFloor()) {
					jmpCnt = 0;
				}
			*/
				if(key == KeyCode.UP) {
					if (!controller.isCollision()) {
						if(jmpCnt<1) {
							controller.setCanJump(true);
						}
						else if(jmpCnt<2) {
							controller.getP1().setJumpStrength(8);
							controller.setCanJump(true);
						}
						else if (controller.onFloor()) {
							jmpCnt = 0;
						}
						/*
						else if(controller.getP1().getY()==controller.getPlatformFloor()) {// && !controller.getP1().inLava()){
							jmpCnt = 0;
						}
						*/
						else {
							consumed = true;
							e.consume();
						}
						if (!consumed)
							jmpCnt++;
					}
					else if (controller.isCollision() && !controller.getP1().getLastMove().equals(key)) {
						if(jmpCnt<1) {
							controller.setCanJump(true);
						}
						else if(jmpCnt<2) {
							controller.getP1().setJumpStrength(8);
							controller.setCanJump(true);
						}
						else if (controller.onFloor()) {
							jmpCnt = 0;
						}
						/*
						else if(controller.getP1().getY()==controller.getPlatformFloor()) {// && !controller.getP1().inLava()){
							jmpCnt = 0;
						}
						*/
						else {
							consumed = true;
							e.consume();
						}
						if (!consumed)
							jmpCnt++;
					}
					else {
						consumed = true;
						e.consume();
					}
				}
			
				
				// TODO Maybe implement if we add a ladders?
//				else if(key == KeyCode.DOWN) {
//					System.out.println("DOWN");
//				}
				else if(key == KeyCode.RIGHT) {
					if (!controller.isCollision()) {
						controller.getP1().setMovingRight(true);
						controller.getP1().setMovingLeft(false);
						controller.getP1().incrementX();
						controller.getP1().setVelX(3);
						controller.setCanMoveRight(true);
						//(increments, and animate picture
					}
					else if(controller.isCollision() && !controller.getP1().getLastMove().equals(key)) {
						// This allows you to click a different key, then click this key and you will
						// be able to move throught the obstacle
						controller.getP1().setMovingRight(true);
						controller.getP1().setMovingLeft(false);
						controller.getP1().incrementX();
						controller.getP1().setVelX(3);
						
						//call moving right method (called in tick)
						// TODO: add if no collisions
						controller.setCanMoveRight(true);
						//(increments, and animate picture
					}
					
					else {
						consumed = true;
						e.consume();
					}
				}
				else if(key == KeyCode.LEFT) {
					if (!controller.isCollision()) {
						controller.getP1().setMovingLeft(true);
						controller.getP1().decrementX();
						controller.getP1().setVelX(-3);
						// TODO: add if no collisions
						controller.setCanMoveLeft(true);
					}
					else if (controller.isCollision() && !controller.getP1().getLastMove().equals(key)) {
						controller.getP1().setMovingLeft(true);
						controller.getP1().decrementX();
						controller.getP1().setVelX(-3);
						// TODO: add if no collisions
						controller.setCanMoveLeft(true);
					}
					else {
						consumed = true;
						e.consume();
					}
				}
				else {
					consumed = true;
					e.consume();
				}
				if (!consumed)
					controller.getP1().setLastMove(key);
				
			}
			
		};
		EventHandler<KeyEvent> keyReleasedNav = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				KeyCode key = e.getCode();
				if(key == KeyCode.UP) {
					
				}
				// TODO Maybe implement if we add a ladders?
//				else if(key == KeyCode.DOWN) {
//				}
				else if(key == KeyCode.RIGHT) {
					//controller.getP1().incrementX();
					controller.getP1().setVelX(0);
					//call moving right method, called in tick()
					controller.setCanMoveRight(false);
					controller.getP1().setMovingRight(false);
					//(increments, and animate picture
					
				}
				else if(key == KeyCode.LEFT) {
					//controller.getP1().decrementX();
					controller.getP1().setVelX(0);
					controller.setCanMoveLeft(false);
					controller.getP1().setMovingLeft(false);
				}else {
					//consumed = true;
					e.consume();
				}
				/*
				if (!consumed)
					controller.getP1().setLastMove(key);
				*/
			}
		};
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, keyPressedNav);
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, keyReleasedNav);
		primaryStage.addEventHandler(KeyEvent.KEY_TYPED, keyPressedNav);
		//////////// character control end ///////
		
		root = new Group();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	/**
	 * draws all shapes on the GUI that are saved in the state of the game
	 * @param shapes list of shapes to be drawn
	 * @param gc graphicContext being drawn on
	 */
	public void drawShapes(ArrayList<Shape> shapes) {
		for(Shape shape: shapes) {
			if(shape instanceof Rectangle) {
				gc.setFill(((Rectangle) shape).getFill());
				gc.fillRect(((Rectangle) shape).getX(), ((Rectangle) shape).getY(), 
						((Rectangle) shape).getWidth(), ((Rectangle) shape).getHeight());

			}else if (shape instanceof myCircle) {
				gc.setFill(((myCircle) shape).getFill());
				gc.fillOval(((myCircle) shape).getCenterX(), ((myCircle) shape).getCenterY(),
						((myCircle) shape).getRadius(), ((myCircle) shape).getRadius());;
			}
		}
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * draw all text for the tutorial.
	 */
	private void drawTutorialText() {
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Use arrow keys \n to move", 50, 180);
		gc.fillText("Hide under shelter \n to avoid poisonous rain", 175, 130);
		gc.fillText("Don't fall \n into the lava", 325, 200);
		gc.fillText("Have fun!", 800, 100);
		gc.fillText("Reach the \n end of the level \n without dying to \nwin!", 1100, 200);
	}
	
	private void drawBridgeAnimation(Rectangle rect) {
		TranslateTransition tt = new TranslateTransition();
		tt.setDuration(Duration.millis(2000));
		tt.setNode(rect);
		tt.setByX(200);
		tt.play();
		root.getChildren().add(rect);
	}

	/**
	 * 
	 * updates view
	 * 
	 * @param o observable
	 * @param arg a list containing two lists. the first index is a list of all floors.
	 * 			the second list is a list of all obstacles.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(controller.getObstacles().size() < 50) {
			controller.makeRain(0, 1);
		}
		if(level.toLowerCase().equals("tutorial")) {//if its tutorial
			gc.clearRect(0, 0, 1200, 300);
			gc.setFill(new ImagePattern(image1));
			gc.fillRect(0, 0, 1200, 300);
			drawTutorialText();
		}else if(level.toLowerCase().equals("level 1")){//if its level 1
			gc.clearRect(0, 0, 1200, 300);
			gc.setFill(Color.PINK);
			gc.fillRect(0, 0, 1200, 300);
			if(controller.checkButtonClick() && bridgeStageTwoDrawn == false) { //draw bridge if button is clicked
				Rectangle rect = new Rectangle(700, 250, 200, 25);
				rect.setFill(Color.SADDLEBROWN);
				controller.addFloor(rect);
				//drawBridgeAnimation(rect);
				bridgeStageTwoDrawn = true;
			}
		}else{//level 2
			gc.clearRect(0, 0, 1200, 300);
			gc.setFill(Color.AQUA);
			gc.fillRect(0, 0, 1200, 300);
			controller.makeRain(0, 1);
		}
		gc.drawImage(new Image("door.png"), 1150, 100,50,150);
		//int size = ((ArrayList<Shape>)((ArrayList<Object>)arg).get(0)).size();
		//System.out.println(((ArrayList<Shape>)((ArrayList<Object>)arg).get(0)).get(size - 1));
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(0)));
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(1)));
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(3)));
		PlayerOne renderedPlayer = ((ArrayList<PlayerOne>)((ArrayList<Object>)arg).get(2)).get(0);
		gc.drawImage(renderedPlayer.getPlayerImg(), renderedPlayer.getX(), renderedPlayer.getY());
		
		//gc.setFill(Color.BLACK);
		//gc.fillText("HP:", 20, 20);
		gc.setFill(Color.GREY);//makes rectangle
		gc.fillRect(35, 10, 48, 18);
		gc.setFill(Color.RED);
		gc.fillRect(39, 14, controller.getP1().getHealth() / 2.5, 10);
		
		
		
		if (controller.isGameOver()) {
			controller.stop();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle(null);
		    alert.setHeaderText(null);
		    if (controller.getP1().inLava())
		    	alert.setContentText("Game Over. You fell into some lava!");
		    else if(controller.getP1().getHealth() == 0) {
		    	alert.setContentText("Game Over. The poison rain got you!");
		    }
		    else
		    	alert.setContentText("Level Completed! Great Work.");
		    alert.setOnHidden(evt -> Platform.exit());
		    alert.show(); 
		}

	}
	

}