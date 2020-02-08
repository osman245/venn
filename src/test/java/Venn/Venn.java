package Venn;

import java.util.Optional;

import Venn_form.Form;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Venn extends Stage
{

	private Button init;
	private String code;
	private Pane root;
	private double maxH, maxW;
	private int txtCount =0;


	public Venn()
	{
		startStage();

	}

	private void startStage() {
		// making a basic gui

		this.setMaximized(true);
		this.setResizable(false);
		root = new Pane();
		root.setPadding(new Insets(15,15,15,15));

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		maxH = bounds.getMaxY();
		maxW = bounds.getMaxX();

	//	System.out.println(maxW);

		Scene scene = new Scene(root);
		root.setStyle("-fx-background-color:#faf8ef;");

		//graphic text for the button
		Text txtInit = new Text("Create a new Venn Diagram");
		txtInit.setFontSmoothingType(FontSmoothingType.GRAY);
		txtInit.setStyle("-fx-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;");

		init = new Button();
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
		panel.setLayoutX(maxW/2-init.getPrefWidth()/2);
		panel.setLayoutY(maxH/2-init.getPrefHeight()/2);

		root.getChildren().add(panel);

		this.setTitle("custom venn diagram maker");
		this.getIcons().clear();
		//s.getIcons().add(new Image("/Images/VennIcon"+".png"));
		this.setScene(scene);
		this.show();

	}
	public void init(String code)
	{
		init.setVisible(false); 


		
		Button add = new Button("add a text box");
		add.setPrefSize(200, 40);
		add.setLayoutX(maxW-add.getPrefWidth()-15);
		add.setLayoutY(maxH-add.getPrefHeight());
		add.setStyle("-fx-text-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;-fx-background-color: #8f7a66");
		
		add.setOnAction(e->addTextBox());
		
			
		root.getChildren().add(add);
		
		
	}

	private void addTextBox()
	{
		Button b = new Button();
		b.setPrefSize(150, 40);
		b.setLayoutX(maxW-b.getPrefWidth());
		b.setLayoutY(b.getPrefHeight()*txtCount);
		
		txtCount++;
		//figure out limit later
		
		b.setOnMouseDragged(e->{
			b.setLayoutX(b.getLayoutX()+e.getX()-b.getWidth()/2);
			b.setLayoutY(b.getLayoutY()+e.getY()-b.getHeight()/2);
		});
		
		
		//going to add right click to access options
		b.setOnMouseClicked(e->{
			if( e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2)
			{
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Set Text");
				dialog.setHeaderText(null);
				dialog.setContentText("");
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent())
				{
				b.setText(result.get());
				}
			}
		});
		root.getChildren().add(b);
	}

}
