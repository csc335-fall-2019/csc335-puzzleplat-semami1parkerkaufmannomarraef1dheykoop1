import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * navWindow launches a navigation window for a user to select
 * between the different levels. This info is sent to the view and 
 * the screen gets updated to the selected level.
 */
public class navWindow extends Application{
	
	private Stage dummyStage;
	private String selection;
	
	
	public navWindow(){
		//constructor that launches the GUI when instantiated. 
		try {
			start(dummyStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start(Stage primaryStage) throws Exception {
		/**
		 * Creates the GUI interface for the navigation window which
		 * allows the user to select between levels.
		 * 
		 * @param primaryStage is a Stage Object that the dialog uses
		 */
		Stage dialog = new Stage();
		
		dialog.setTitle("Switch Levels");
		
		TilePane pane = new TilePane(); 
	     
    	VBox vbox = new VBox();
		  
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
		    
		    Button okay = new Button("OK");
		    Button cancel = new Button("Cancel");
		    
		    okay.setOnAction(new EventHandler<ActionEvent>() {
		        @Override public void handle(ActionEvent e) {
		        	selection = ((RadioButton)toggle2.getSelectedToggle()).getText();
		        	dialog.close();
		        }
		    }); 
		    
		    cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					dialog.close(); // TODO fix thread error
				}
		    	
		    });
		    
		    HBox group = new HBox();
		    group.setSpacing(15);
		    group.setPadding(new Insets(8,8,8,8));
		       
		    group.getChildren().addAll(okay, cancel);

		    vbox.getChildren().add(group2);
		    vbox.getChildren().add(group);
		        
		    pane.getChildren().add(vbox);
		  
			Scene scene = new Scene(pane, 600, 100);
			
			dialog.setScene(scene);

			dialog.initOwner(primaryStage);
			dialog.initModality(Modality.APPLICATION_MODAL); 
			dialog.showAndWait();
		       
	}
	
	public String getSelected() {
		/**
		 * gets the level the user has selected
		 * 
		 * @return String with the level name
		 */
		return this.selection;
	}
	

}
