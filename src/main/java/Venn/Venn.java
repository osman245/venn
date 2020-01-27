package Venn;

import Venn_form.Form;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Venn 
{
	public Venn()
	{
		startStage();
		
	}

	private void startStage() {
		// making a basic gui
		Stage s = new Stage();
		GridPane root = new GridPane();
		root.setPadding(new Insets(15,15,15,15));
		root.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(root,800,800);
		root.setStyle("-fx-background-color:#faf8ef;");
	
		//graphic text for the button
		Text txtInit = new Text("Create a new Venn Diagram");
		txtInit.setFontSmoothingType(FontSmoothingType.GRAY);
		txtInit.setStyle("-fx-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;");
		
		Button init = new Button();
		init.setGraphic(txtInit);
		init.setStyle("-fx-background-color: #8f7a66;");
		init.setPrefSize(200, 75);
		init.setAlignment(Pos.CENTER);
		init.setOnAction(e ->{
			Form f = new Form();
			f.setButton(init);
			init.setDisable(true);
		});
		
	
		VBox panel = new VBox(20);
		panel.setAlignment(Pos.CENTER);
		panel.getChildren().add(init);
		panel.setLayoutX(scene.getWidth()/2-init.getPrefWidth()/2);
		panel.setLayoutY(scene.getHeight()/2-init.getPrefHeight()/2);	
		
		root.add(panel,0,0);
		
		s.setTitle("custom venn diagram maker");
		s.getIcons().clear();
		//s.getIcons().add(new Image("/Images/VennIcon"+".png"));
		s.setScene(scene);
		s.show();
	
		
		
	}
}