import java.util.ArrayList;
import java.util.Observable;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
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
		
		Group root = new Group();
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
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(0)));
		drawShapes(((ArrayList<Shape>)((ArrayList<Object>)arg).get(1)));
		
		
		
	}

}
