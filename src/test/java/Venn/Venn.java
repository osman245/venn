package Venn;

import java.util.Optional;

import Venn_form.Form;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Venn extends Stage
{

	private Button init;
	private String code;
	private Pane root;
	private double maxH, maxW;
	private int txtCount =0;
	private Scene scene;


	public Venn()
	{
		startStage();

	}

	private void startStage() {
		// making a basic gui

		this.setMaximized(true);
		this.setResizable(false);


		this.setOnCloseRequest(e->{

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to exit?");
			alert.setTitle("Exit Form");
			alert.initModality(Modality.NONE);
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
			alert.showAndWait().ifPresent(response ->{
				if(response == ButtonType.YES)
				{
					e.consume();
					this.close();
				}
				else
				{
					e.consume();
				}
			});;


		});


		root = new Pane();
		root.setPadding(new Insets(15,15,15,15));

		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		maxH = bounds.getMaxY();
		maxW = bounds.getMaxX();

		//	System.out.println(maxW);

		scene = new Scene(root);
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
	//this method is called after the user selects the options from the form 
	/*
	 * the circles are added and other interface features
	 */
	public void init(String code)
	{
		//init.setVisible(false);
		root.getChildren().clear();

		//clicking this will parse a ttext area and every new line will create a new text field
		Button add = new Button("add text boxes");
		add.setPrefSize(200, 40);
		add.setLayoutX(maxW-add.getPrefWidth()-15);
		add.setLayoutY(maxH-add.getPrefHeight());
		add.setStyle("-fx-text-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;-fx-background-color: #8f7a66");

		TextArea ta = new TextArea();
		ta.setPrefSize(200, maxH/2);
		ta.setLayoutX(maxW-ta.getPrefWidth()-15);
		ta.setLayoutY(maxH-ta.getPrefHeight()-add.getPrefHeight()-20);
		ta.setStyle("-fx-text-fill: black; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;-fx-background-color: #8f7a66");
		ta.setWrapText(true);

		add.setOnAction(e->addTextBox(ta));

		root.getChildren().add(add);
		root.getChildren().add(ta);

		//check the code and add the correct amount of circles accordingly 
		/*
		 * code 1 -> basic 2 intersection
		 * code 111 ->  3 way intersection
		 * code 110, 011, 101,  -> 3 2 intersections 3 circles 
		 * 
		 * circle hex codes defaults 
		 * circle 1: #ffbf00
		 * circle 2: #5b9ad5
		 * circle 3: #30e845
		 * 
		 */

		if(code.equals("1"))
		{
			Circle c1 = new Circle(maxW/4.5);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/4+10);
			c1.setLayoutY(maxH/2);

			Circle c2 = new Circle(maxW/4.5);
			c2.setStyle("-fx-fill: #5b9ad5");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2);
			c2.setLayoutY(maxH/2);

			root.getChildren().add(c1);
			root.getChildren().add(c2);
		}
		else if (code.equals("011"))
		{
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #30e845");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #5b9ad5");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);		

			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(c3);
		}
		else if (code.equals("110"))
		{
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #30e845");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #ffbf00");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #5b9ad5");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);	

			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(c3);
		}
		else if (code.equals("101"))
		{
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			//middle
			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #5b9ad5");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #30e845");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);		

			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(c3);
		}
		else if (code.equals("111"))
		{	

			//bottom left
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #5b9ad5");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/4+20);
			c1.setLayoutY(maxH/1.5);

			//bottom right
			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #30e845");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.5);
			c2.setLayoutY(maxH/1.5);

			//top
			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #ffbf00");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/3.225);
			c3.setLayoutY(maxH/2.75);

			root.getChildren().add(c3);
			root.getChildren().add(c1);
			root.getChildren().add(c2);
		}

	}
	
	

	private void addTextBox(TextArea t)
	{

		String[] inputs = t.getText().split("\n");
		int c=0;
		
		for(int i=0; i< inputs.length; i++)
		{
			//-fx-border-color: #8f7a66
			if(!inputs[i].isEmpty() && inputs[i].trim().length()>0)
			{
				Button b = new Button();
				Label l = new Label();
				
				l.setPrefSize(150, 60);
				l.setWrapText(true);
				l.setAlignment(Pos.CENTER);
				l.setText(inputs[i]);
				l.setStyle("-fx-background-color:transparent;-fx-text-fill: black; -fx-font-family: Clear Sans; -fx-font-size: "
						+ "18px; -fx-font-weight:bold; ");
				b.setStyle("-fx-background-color:transparent;-fx-text-fill: black; -fx-font-family: Clear Sans; -fx-font-size: "
						+ "18px; -fx-font-weight:bold; ");
								b.setGraphic(l);
				
				b.setLayoutX(maxW-l.getPrefWidth()-20);
				b.setLayoutY(l.getPrefHeight()*c);
				b.setAlignment(Pos.CENTER);		
				
				c++;
				b.setOnMouseDragged(e->{
					if( e.getButton().equals(MouseButton.PRIMARY))
					{
						b.setLayoutX(b.getLayoutX()+e.getX()-b.getWidth()/2);
						b.setLayoutY(b.getLayoutY()+e.getY()-b.getHeight()/2);
					}
				});

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
							l.setText(result.get());
						}
					}
					else if(e.getButton().equals(MouseButton.SECONDARY))
					{
						//for now delete
						//in the future add an options panel
						//text colours, background color, size
						root.getChildren().remove(b);
					}
				});
				
				root.getChildren().add(b);

			}
		}
		
		//clear the text area
		t.clear();

		//add window limits so you cant drag off screen
		//going to add right click to access options

	}

}
