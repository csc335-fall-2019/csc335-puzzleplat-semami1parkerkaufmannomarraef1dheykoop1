import java.util.ArrayList;
import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StageOneGUI extends Application implements java.util.Observer{
	
	PuzzlePlatController controller = new PuzzlePlatController();
	GraphicsContext gc;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		controller.start();//starts game clock
		controller.addObserver(this);
		
		primaryStage.setTitle("Tutorial Stage");
		Canvas canvas = new Canvas(1200,300);
		gc = canvas.getGraphicsContext2D();
		
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(0, 0, 1200, 300);
	
		controller.makeStageOneFloors();//sets up level
		drawShapes(controller.getFloors());
		
		controller.makeRain(0, 1);//makes rain
		controller.makeStageOneObstacles();
		drawShapes(controller.getObstacles());
		
		drawTutorialText();
		
		Group root = new Group();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		scene.setOnKeyPressed((event) -> {
			KeyCode k = event.getCode();
			if (k.isArrowKey()) {
				controller.moveCharacter(k.toString());
			}
		});
		
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
	
	/**
	 * draw all text for the tutorial.
	 */
	private void drawTutorialText() {
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText("Use arrow keys \n to move", 50, 180);
		gc.fillText("Hide under shelter \n to avoid poisonous rain", 175, 130);
		gc.fillText("Don't fall \n into the lava", 325, 200);
		gc.fillText("Jump over obstacles", 400, 130);
		gc.fillText("Get creative on how you \n finish the level", 800, 100);
		gc.fillText("Reach the \n end of the level \n without dying to win!", 1100, 200);
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
		gc.clearRect(0, 0, 1200, 300);
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(0, 0, 1200, 300);
		if(controller.getObstacles().size() < 50) {
			controller.makeRain(0, 1);
		}
		drawTutorialText();
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(0)));
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(1)));
	}

}
