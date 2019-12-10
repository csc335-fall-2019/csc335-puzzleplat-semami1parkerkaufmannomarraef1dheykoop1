package tests;
import exacutable.*;
import javafx.application.Application;

import static org.junit.Assert.*;

import org.junit.Test;

public class testPuzzlePlatModel {

	@Test
	public void testCharacters() {
		Application.launch(dummyApp.class);
		PuzzlePlatModel model = new PuzzlePlatModel();
		PlayerOne p = new PlayerOne(1, 1);
		model.setP(p);
		assertTrue(model.getP() instanceof PlayerOne);
		assertTrue(model.getCharacters().get(0) instanceof PlayerOne );
	}

}
