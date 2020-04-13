package Venn;

import java.util.ArrayList;
import java.util.Optional;

import Venn_form.Form;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	private Scene scene;
	private ArrayList<TextBox> textBoxes;


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

		//clicking this will parse a text area and every new line will create a new text field
		/*
		 * Button add = new Button("add text boxes"); add.setPrefSize(200, 40);
		 * add.setLayoutX(maxW-add.getPrefWidth()-15);
		 * add.setLayoutY(maxH-add.getPrefHeight()); add.
		 * setStyle("-fx-text-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;-fx-background-color: #8f7a66"
		 * );
		 * 
		 * TextArea ta = new TextArea(); ta.setPrefSize(200, maxH/2);
		 * ta.setLayoutX(maxW-ta.getPrefWidth()-15);
		 * ta.setLayoutY(maxH-ta.getPrefHeight()-add.getPrefHeight()-20); ta.
		 * setStyle("-fx-text-fill: black; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;-fx-background-color: #8f7a66"
		 * ); ta.setWrapText(true);
		 * 
		 * add.setOnAction(e->addTextBox(ta));
		 */	

		createTextBoxAdder();

		//adding the colour choosers				
		ColorPicker cp1 = new ColorPicker(/*Color.rgb(255,191,0)*/);
		ColorPicker cp2 = new ColorPicker(/*Color.rgb(91,154,213)*/);
		ColorPicker cp3 = new ColorPicker(/*Color.rgb(48,232,69)*/);

		Label set1 = new Label("set1");
		set1.setPrefSize(100, 40);
		set1.setStyle("-fx-font-family: Clear Sans; -fx-font-size: 18px;");
		set1.setAlignment(Pos.CENTER_RIGHT);
		Label set2 = new Label("set2");
		set2.setPrefSize(100, 40);
		set2.setStyle("-fx-font-family: Clear Sans; -fx-font-size: 18px;");
		set2.setAlignment(Pos.CENTER_RIGHT);
		Label set3 = new Label("set3");
		set3.setPrefSize(100, 40);
		set3.setStyle("-fx-font-family: Clear Sans; -fx-font-size: 18px;");
		set3.setAlignment(Pos.CENTER_RIGHT);

		if(code.length() == 1)
		{		
			cp1.setPrefSize(200, 40);
			cp2.setPrefSize(200, 40);

			//cp1.setLayoutX(add.getLayoutX()-15-cp1.getPrefWidth());
			//cp2.setLayoutX(add.getLayoutX()-15-cp2.getPrefWidth());

			set1.setLayoutX(cp1.getLayoutX()-set1.getPrefWidth()-10);
			set2.setLayoutX(cp2.getLayoutX()-set2.getPrefWidth()-10);

			cp2.setLayoutY(maxH-cp1.getPrefHeight());
			cp1.setLayoutY(maxH-cp2.getPrefHeight()*2);

			set2.setLayoutY(cp2.getLayoutY());
			set1.setLayoutY(cp1.getLayoutY());

			root.getChildren().addAll(cp1, cp2,set1,set2);

		}
		else
		{			
			cp1.setPrefSize(200, 40);
			cp2.setPrefSize(200, 40);
			cp3.setPrefSize(200, 40);

			/*
			 * cp1.setLayoutX(add.getLayoutX()-15-cp1.getPrefWidth());
			 * cp2.setLayoutX(add.getLayoutX()-15-cp2.getPrefWidth());
			 * cp3.setLayoutX(add.getLayoutX()-15-cp3.getPrefWidth());
			 */

			set1.setLayoutX(cp1.getLayoutX()-set1.getPrefWidth()-10);
			set2.setLayoutX(cp2.getLayoutX()-set2.getPrefWidth()-10);
			set3.setLayoutX(cp3.getLayoutX()-set3.getPrefWidth()-10);

			cp3.setLayoutY(maxH-cp1.getPrefHeight());
			cp2.setLayoutY(maxH-cp2.getPrefHeight()*2);
			cp1.setLayoutY(maxH-cp3.getPrefHeight()*3);

			set1.setLayoutY(cp1.getLayoutY());
			set2.setLayoutY(cp2.getLayoutY());
			set3.setLayoutY(cp3.getLayoutY());

			root.getChildren().addAll(cp1, cp2, cp3,set1,set2,set3);
		}

		/*
		 * root.getChildren().add(add); root.getChildren().add(ta);
		 */

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

		//setting up the event handlers 



		//adding the circles to the scene here
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

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));

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

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

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

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

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

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

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

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

			root.getChildren().add(c3);
			root.getChildren().add(c1);
			root.getChildren().add(c2);
		}		

	}


	private void createTextBoxAdder()
	{
		VBox container = new VBox();
		container.setPrefWidth(170);
		container.setPrefHeight(maxH);
		container.setAlignment(Pos.CENTER);
		container.setLayoutX(maxW-200);

		//init the array list
		VBox tbPane = new VBox();
		tbPane.setSpacing(-140);

		textBoxes = new ArrayList<TextBox>();

		// scroll pane
		ScrollPane sp = new ScrollPane();
		sp.setPrefSize(180, maxH-100);
		//sp.setPannable(true);
		sp.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		sp.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);

		//text field
		TextField tf = new TextField();
		tf.setPrefSize(170, 40);
		tf.setStyle("-fx-font-family: arial; -fx-font-size: 16; ");
		tf.setAlignment(Pos.CENTER);

		sp.setContent(tbPane);

		container.getChildren().add(tf);
		container.getChildren().add(sp);
		//container.getChildren().add(tbPane);
		root.getChildren().add(container);

		tf.setOnAction(e-> 
		{
			//add to the scroll pane storing the text boxes 
			if(!tf.getText().trim().isEmpty())
			{
				TextBox t = new TextBox(tf.getText());
				t.setExpand(false);
				//t.setManaged(false);
				t.setOnMousePressed(event->{
					if(tbPane.getChildren().contains(t))
					{
						tbPane.getChildren().remove(t);
						root.getChildren().add(t);
						t.setExpand(true);	

						t.setXpos(event.getSceneX()-t.getPrefWidth()/2);
						t.setYpos(event.getSceneY()-t.getPrefHeight()/2);
					}
				
				});
				
				t.setOnMouseDragged(event->{
					t.setXpos(event.getSceneX()-t.getPrefWidth()/2);
					t.setYpos(event.getSceneY()-t.getPrefHeight()/2);
				});

				t.setOnMouseClicked(event -> mouseClickEvent(event, t, root));
				
				textBoxes.add(t);
				//tbPane.getChildren().add(t);
				tbPane.getChildren().add(t);
				//t.setManaged(false);

				tf.clear();
			}
		});

	}
	
	public static void mouseClickEvent(MouseEvent e, TextBox t, Pane root)
	{

		if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 ) 
		{
			t.expand();
		}
		else if(  e.getButton().equals(MouseButton.SECONDARY) && !t.isPreview())
		{
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Set Text");
			dialog.setHeaderText(null);
			dialog.setContentText("");
			dialog.getDialogPane().getButtonTypes().clear();

			ButtonType delete = new ButtonType("Delete");
			ButtonType customize = new ButtonType("Customize");

			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL,delete,customize);


			Button btnDel = (Button)dialog.getDialogPane().lookupButton(delete);
			btnDel.addEventFilter(ActionEvent.ACTION, event->
			{
				/*
				 * Alert alert = new Alert(AlertType.CONFIRMATION);
				 * alert.setContentText("Are you sure you want to delete this text box?");
				 * alert.setHeaderText(null); alert.getButtonTypes().clear();
				 * alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
				 * 
				 * dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
				 * dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
				 * dialog.getDialogPane().lookupButton(delete).setDisable(true);
				 * 
				 * Optional<ButtonType> result = alert.showAndWait(); if (result.get() ==
				 * ButtonType.YES) { root.getChildren().remove(b); } else { alert.close();
				 * 
				 * }
				 */

				root.getChildren().remove(t);

			});


			Button btnCustom = (Button)dialog.getDialogPane().lookupButton(customize);
			btnCustom.addEventFilter(ActionEvent.ACTION, event -> { new CustomizationWindow(t); });

			Optional<String> result = (dialog).showAndWait();

			if (result.isPresent())
			{
				t.setTitleText(result.get());
			}

		}



		/*
		 * if(e.getButton().equals(MouseButton.PRIMARY)) { if(isExpanded())
		 * setExpanded(false); else setExpanded(true); }
		 */
	}


	private void changeCol(Color col, Circle c)
	{
		c.setFill(col);
	}

}