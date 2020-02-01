import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	 Scene scene;
	Stage window;
	Button button;
	 
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		
		
		
		//button
		button = new Button("Create a Venn Diagram");
	    button.setMinWidth(100);
	    button.setOnAction(e -> {
            boolean result = ConfirmBox.display("Title of Window", "");
            
        });
	   //layout 
	    grid.getChildren().add(button);
	     scene = new Scene(grid,350,250);
	     window.setScene(scene);
	     window.show();
	     
		
		
		
	}

}
