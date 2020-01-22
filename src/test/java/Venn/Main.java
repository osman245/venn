package Venn;
	
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.Graphics;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	
		//Create Border outline
		BorderPane root = new BorderPane(); 
		
		//add circle coordinates x,y,r 
		Circle cir1 = new Circle(300,300,100);
		Circle cir2 = new Circle(450,300,100);
    	
		//add color to circles
    	cir1.setFill(Color.TRANSPARENT);
    	cir2.setFill(Color.TRANSPARENT  );
		cir1.setStroke(Color.CORAL);
		cir2.setStroke(Color.BLUE);
    	
    	//add circles to the border layout
    	root.getChildren().add(cir1);
    	root.getChildren().add(cir2);
    	
    	  

            
					
			
			
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Venn Project");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

