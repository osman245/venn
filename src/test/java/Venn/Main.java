package Venn;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	public static Venn v;
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		 v = new Venn();
		 //CustomizationWindow c = new CustomizationWindow(new TextBox());
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}

