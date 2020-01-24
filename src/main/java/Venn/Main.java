<<<<<<< Upstream, based on branch 'Release--Vm' of https://github.com/osman245/venn.git
=======
package Venn	;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane(); 
	
		
		//CIRCLE1
		Circle cir1 = new Circle(300,400,100);
		cir1.setFill(Color.TRANSPARENT);
		cir1.setStroke(Color.CORAL);
		
		//CIRCLE2
		Circle cir2 = new Circle(450,400,100);
		cir2.setFill(Color.TRANSPARENT);
		cir2.setStroke(Color.BLUE);
		
		//NEW_VENN
		Button new_Venn = new Button("New Venn Project");
		new_Venn.setShape(new Circle(10));
		new_Venn.setLayoutX(100);
		new_Venn.setLayoutY(100);
		
		//CHANGE TEXT
		Button C_text = new Button("Change Text");
		C_text.setLayoutX(100);
		C_text.setLayoutY(700);
		
		//CHANGE BACKGROUND COLOR
		Button C_bcolor = new Button("Change Background Color");
		C_bcolor.setLayoutX(300);
		C_bcolor.setLayoutY(700);
		
		//CHANGE Venn COLOR
		Button C_vcolor = new Button("Change Venn Color");
		C_vcolor.setLayoutX(500);
		C_vcolor.setLayoutY(700);
		
    	//SCENE
    	root.getChildren().add(cir1);
    	root.getChildren().add(cir2);
    	root.getChildren().add(new_Venn);
    	root.getChildren().add(C_text);
    	root.getChildren().add(C_bcolor);
    	root.getChildren().add(C_vcolor);
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Venn Diagram");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//EVENTS
		new_Venn.setOnAction(e -> New_Venn(primaryStage));
		C_text.setOnAction(e -> c_Text(primaryStage));
		C_bcolor.setOnAction(e -> c_bColor(primaryStage));
		C_vcolor.setOnAction(e -> c_vColor(primaryStage));
		}
	private void New_Venn(Stage secondaryStage) {
		
		Pane root = new Pane();
		Button b = new Button("press");
		b.setLayoutY(100);
		b.setLayoutX(100);
		root.getChildren().add(b);
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		secondaryStage.setTitle("New Venn Diagram");
		secondaryStage.setScene(scene);
		secondaryStage.show();
	}
	
	private void c_bColor(Stage secondaryStage) {
		Pane root = new Pane();
		//PINK
		Button PINK = new Button("PINK");
		PINK.setLayoutY(100);
		PINK.setLayoutX(100);
		root.getChildren().add(PINK);
		//RED
		Button RED = new Button("RED");
		RED.setLayoutY(100);
		RED.setLayoutX(300);
		root.getChildren().add(RED);
		//SCENE
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		secondaryStage.setTitle("New Venn Diagram");
		secondaryStage.setScene(scene);
		secondaryStage.show();
		//EVENTS
		PINK.setOnAction(e -> C_Pink(secondaryStage,root));
		RED.setOnAction(e -> C_Red(secondaryStage,scene));
	}
	private void c_Text(Stage secondaryStage) {
		secondaryStage.setTitle("new");
	}
	
	private void C_Pink(Stage primaryStage,Pane r) {
		Pane root = new Pane(); 
			
		//CIRCLE1
		Circle cir1 = new Circle(300,400,100);
		cir1.setFill(Color.TRANSPARENT);
		cir1.setStroke(Color.CORAL);
		
		//CIRCLE2
		Circle cir2 = new Circle(450,400,100);
		cir2.setFill(Color.TRANSPARENT);
		cir2.setStroke(Color.BLUE);
		
		//NEW_VENN
		Button new_Venn = new Button("New Venn Project");
		new_Venn.setShape(new Circle(10));
		new_Venn.setLayoutX(100);
		new_Venn.setLayoutY(100);
		
		//CHANGE TEXT
		Button C_text = new Button("Change Text");
		C_text.setLayoutX(100);
		C_text.setLayoutY(700);
		
		//CHANGE BACKGROUND COLOR
		Button C_bcolor = new Button("Change Background Color");
		C_bcolor.setLayoutX(300);
		C_bcolor.setLayoutY(700);
		
		//CHANGE Venn COLOR
		Button C_vcolor = new Button("Change Venn Color");
		C_vcolor.setLayoutX(500);
		C_vcolor.setLayoutY(700);
		
    	//SCENE
    	root.getChildren().add(cir1);
    	root.getChildren().add(cir2);
    	root.getChildren().add(new_Venn);
    	root.getChildren().add(C_text);
    	root.getChildren().add(C_bcolor);
    	root.getChildren().add(C_vcolor);
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Venn Diagram");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//EVENTS
		new_Venn.setOnAction(e -> New_Venn(primaryStage));
		C_text.setOnAction(e -> c_Text(primaryStage));
		C_bcolor.setOnAction(e -> c_bColor(primaryStage));
		C_vcolor.setOnAction(e -> c_vColor(primaryStage));
				
		root.setStyle("-fx-background-color: pink");
		        
		        
	}
	private void c_vColor(Stage primaryStage) {
		
	}
	private void C_Red(Stage primaryStage,Scene s) {
		//ROOT
		Pane root = new Pane();
		StackPane holder = new StackPane();
        Canvas canvas = new Canvas(400,  300);
		//CIRCLE1
		Circle cir1 = new Circle(300,400,100);
		cir1.setFill(Color.TRANSPARENT);
		cir1.setStroke(Color.CORAL);
		
		//CIRCLE2
		Circle cir2 = new Circle(450,400,100);
		cir2.setFill(Color.TRANSPARENT);
		cir2.setStroke(Color.BLUE);
		
		//NEW_VENN
		Button new_Venn = new Button("New Venn Project");
		new_Venn.setShape(new Circle(10));
		new_Venn.setLayoutX(100);
		new_Venn.setLayoutY(100);
		
		//CHANGETEXT
		Button C_text = new Button("Change Text");
		C_text.setLayoutX(100);
		C_text.setLayoutY(700);
		
		//CHANGECOLOR
		Button C_color = new Button("Change Background Color");
		C_color.setLayoutX(300);
		C_color.setLayoutY(700);
		
    	//SCENE
    	root.getChildren().add(cir1);
    	root.getChildren().add(cir2);
    	root.getChildren().add(new_Venn);
    	root.getChildren().add(C_text);
    	root.getChildren().add(C_color);
		Scene scene = new Scene(root,800,800);
		scene.setFill(Color.PINK);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Venn Diagram");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		root.setStyle("-fx-background-color: pink");
        root.getChildren().add(canvas);
        root.getChildren().add(holder);
			}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

>>>>>>> 73acaf7 jada246
