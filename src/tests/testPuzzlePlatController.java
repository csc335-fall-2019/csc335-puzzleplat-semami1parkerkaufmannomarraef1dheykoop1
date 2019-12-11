package tests;
import exacutable.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPuzzlePlatController {

	@Test
	public void testStage1FloorsAndObstacles() {
		PuzzlePlatController control = new PuzzlePlatController();
		control.makeStageOneFloors();
		control.makeRain(0, 1);
		control.makeStageOneObstacles();
		
		assertEquals(201,control.getPlatformFloor()); //floors
		
	}
	
	@Test
	public void testStage2FloorsAndObstacles() {
		//fail("Not yet implemented");
		PuzzlePlatController control = new PuzzlePlatController();
		control.makeStageTwoFloors();
		control.makeRain(0, 1);
		control.makeStageTwoObstacles();
		control.makeStageTwoButtons();
		
		assertTrue(control.getButtons().get(0) instanceof Shape);
		
		assertEquals(201,control.getPlatformFloor()); //floors
	
	}
	
	@Test
	public void testStage3FloorsAndObstacles() {
		//fail("Not yet implemented");
		PuzzlePlatController control = new PuzzlePlatController();
		control.makeStageThreeFloors();
		control.makeRain(0, 1);
		control.makeStageThreeObstacles();
		
		assertEquals(201,control.getPlatformFloor()); //floors
	}
	
	@Test
	public void testButtonClick() { //TODO Partial coverage (1/2 branches missed for checkButtonClick())
		PuzzlePlatController control = new PuzzlePlatController();
		control.start(); //TODO unsure how to test start() & tick()
						// with tick(); movePlayer, moveRight, moveRain, moveLeft are tested since they're private
		control.makeStageTwoFloors();
		control.makeRain(0, 1);
		control.makeStageTwoObstacles();
		control.makeStageTwoButtons();
		
		control.createPlayerOne(550.0, 240.0, 100, 3);
		assertFalse(control.checkButtonClick());
		control.getP1().incrementX();
		control.getP1().incrementX();
		control.getP1().incrementX();
		control.getP1().incrementX();
		//System.out.println(control.getP1().getX());
		//System.out.println(((Rectangle) control.getButtons().get(0)).getX());
		assertTrue(control.checkButtonClick());
		control.getP1().incrementX();
		control.getP1().incrementX();
		control.getP1().incrementX();
		control.getP1().incrementX();
		assertFalse(control.checkButtonClick());
		control.getP1().incrementX();
		control.getP1().incrementX();
		assertFalse(control.checkButtonClick());
		control.stop();
	
	}
	
	@Test
	public void testObserver() {
		PuzzlePlatController control = new PuzzlePlatController();

		PuzzlePlatView view = new PuzzlePlatView();
		control.addObserver(view);	
	}

}
