import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;

import javafx.application.Application;
	import javafx.application.Platform;
	import javafx.beans.value.ChangeListener;
	import javafx.beans.value.ObservableValue;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.scene.Group;
	import javafx.scene.Scene;
	import javafx.scene.control.Alert;
	import javafx.scene.control.Alert.AlertType;
	import javafx.scene.control.Button;
	import javafx.scene.control.ButtonBar.ButtonData;
	import javafx.scene.control.ButtonType;
	import javafx.scene.control.DialogPane;
	import javafx.scene.control.Label;
	import javafx.scene.control.RadioButton;
	import javafx.scene.control.TextField;
	import javafx.scene.control.Toggle;
	import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.TilePane;
	import javafx.scene.layout.VBox;
	import javafx.stage.Modality;
	import javafx.stage.Stage;

	
public class Menu extends Application {
		
		private String player;
		private String level;
		private Stage dummyStage;
		private static boolean enter;

		public void start(Stage primaryStage) throws Exception {
		
			Stage dialog = new Stage();
			enter = false;
			dialog.setTitle("Start Menu");
			
			TilePane pane = new TilePane(); 
		     
	    	VBox vbox = new VBox();
			  
		    ToggleGroup toggle = new ToggleGroup(); 
		  
		        
		    Label label = new Label("Choose Players: ");
		        
		    RadioButton p1Button = new RadioButton("One Player"); 
		    p1Button.setSelected(true);
		    RadioButton p2Button = new RadioButton("Two Players"); 
		    
		    
		    Image image6 = new Image(getClass().getResourceAsStream("User-512.png"));
		    p1Button.setGraphic(new ImageView(image6));
		    
		    
		    Image image7 = new Image(getClass().getResourceAsStream("people.png"));
		    p2Button.setGraphic(new ImageView(image7));
		        
		    label.setPadding(new Insets(8,8,8,8));
		    p1Button.setPadding(new Insets(8,8,8,8));
		    p2Button.setPadding(new Insets(8,8,8,8));
		   
		    p1Button.setToggleGroup(toggle); 
		    p2Button.setToggleGroup(toggle); 
		    
		    HBox group1 = new HBox();
		    group1.setPadding(new Insets(8,8,8,8));
		    group1.getChildren().add(label);
		    group1.getChildren().add(p1Button); 
		    group1.getChildren().add(p2Button); 
		        
		    
		    ToggleGroup toggle2 = new ToggleGroup(); 
		  	 
		    Label label2 = new Label("Level: ");
		    RadioButton tutorialButton = new RadioButton("Tutorial"); 
		    
		    Image image3 = new Image(getClass().getResourceAsStream("Mod_Add-On_1-35-512.png"));
		    tutorialButton.setGraphic(new ImageView(image3));
		    
		    tutorialButton.setSelected(true);
		    RadioButton l1Button = new RadioButton("Level 1");
		    
		    Image image4 = new Image(getClass().getResourceAsStream("twoStars.png"));
		    l1Button.setGraphic(new ImageView(image4));
		    
		    RadioButton l2Button = new RadioButton("Level 2"); 
		    
		    Image image5 = new Image(getClass().getResourceAsStream("threeStars.png"));
		    l2Button.setGraphic(new ImageView(image5));
			  
		  
		    label2.setPadding(new Insets(8,8,8,8));
		    tutorialButton.setPadding(new Insets(8,8,8,8));
		    l1Button.setPadding(new Insets(8,8,8,8));
		    l2Button.setPadding(new Insets(8,8,8,8));
		        
		        
		    tutorialButton.setToggleGroup(toggle2); 
		    l1Button.setToggleGroup(toggle2); 
		    l2Button.setToggleGroup(toggle2);
		        
		    HBox group2 = new HBox();
		    group2.setPadding(new Insets(8,8,8,8));
		    group2.getChildren().add(label2);
		    group2.getChildren().add(tutorialButton); 
		    group2.getChildren().add(l1Button); 
		    group2.getChildren().add(l2Button); 
		       
		   
		    HBox group3 = new HBox();
		    group3.setPadding(new Insets(8,8,8,8));
		        
		        
		    Button game = new Button("Play Game");
		    Button cancel = new Button("Cancel");
		    
		    Image image1 = new Image(getClass().getResourceAsStream("Games-512.png"));
		    game.setGraphic(new ImageView(image1));
		    
		   
		    
		    Image image2 = new Image(getClass().getResourceAsStream("010_x-3-512.png"));
		    cancel.setGraphic(new ImageView(image2));
		    
		    cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					dialog.close();
				}
		    	
		    });
		    
		    game.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					level = ((RadioButton)toggle2.getSelectedToggle()).getText();
		        	player = ((RadioButton)toggle.getSelectedToggle()).getText();

		        	PuzzlePlatView newGame = new PuzzlePlatView();
		        	newGame.setLevel(level);
		        	try {
						newGame.start(new Stage());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	
		        	dialog.close();

				}
		    	
		    });
		 
		    HBox group = new HBox();
		    group.setSpacing(15);
		    group.setPadding(new Insets(8,8,8,8));
		       
		    group.getChildren().addAll(game, cancel);

		    vbox.getChildren().add(group1);
		    vbox.getChildren().add(group2);
		    vbox.getChildren().add(group3);
		    vbox.getChildren().add(group);
		        
		    pane.getChildren().add(vbox);
		  
			Scene scene = new Scene(pane, 600, 200);
			
			dialog.setScene(scene);

			dialog.initOwner(primaryStage);
			dialog.initModality(Modality.APPLICATION_MODAL); 
			dialog.showAndWait();
			
		}
		
		public String getLevelNum() {
			return this.level;
		}
		
		
		public String getPlayerNum() {
			return this.player;
		}
		
		public static boolean getSubmitted() {
			return enter;
		}
		
		public static void main(String args[]) {
			Application.launch(Menu.class);
		}

}
