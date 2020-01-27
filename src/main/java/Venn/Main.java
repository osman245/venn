<<<<<<< Upstream, based on branch 'Release--Vm' of https://github.com/osman245/venn.git
<<<<<<< Upstream, based on branch 'Release--Vm' of https://github.com/osman245/venn.git
=======
package Venn	;
=======
package Venn;
>>>>>>> af1d29c jada246
	
import javafx.application.Application;
import javafx.stage.Stage;
import java.awt.Graphics;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.stage.Stage;

/*<<<<<<< HEAD
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
=======*/

<<<<<<< Upstream, based on branch 'Release--Vm' of https://github.com/osman245/venn.git
>>>>>>> 73acaf7 jada246
=======

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		Venn v = new Venn();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
>>>>>>> 20d1445 jada246
