package Venn;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import Venn_form.Form;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
		this.setResizable(true);


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
	
			
		//---------------------Save button----------------------------------
		Button save = new Button("SAVE");
		save.setLayoutX(root.getPrefWidth() + 50);
		save.setLayoutY(root.getPrefHeight());
		save.setAlignment(Pos.TOP_LEFT);
		
		save.setOnAction(e -> {
			try {
				Save_Venn(ta);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});	
		
		//---------------Load button---------------------------------------
		Button load = new Button("LOAD");
		load.setLayoutX(root.getPrefWidth() + 150);
		load.setLayoutY(root.getPrefHeight());
		load.setAlignment(Pos.TOP_LEFT);


				
		ColorPicker cp1 = new ColorPicker(Color.rgb(255,191,0));
		ColorPicker cp2 = new ColorPicker(Color.rgb(91,154,213));
		ColorPicker cp3 = new ColorPicker(Color.rgb(48,232,69));
	
		
		//setting up the event handlers 
		
		

		//adding the circles to the scene here
		if(code.equals("1"))
		{
			Label label1 = new Label("Label1");
			label1.setLayoutX(maxW/4+10);
			label1.setLayoutY(maxH/2 - 50);

			Circle c1 = new Circle(maxW/4.5);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/4+10);
			c1.setLayoutY(maxH/2);
			
			Label label2 = new Label("Label2");
			label2.setLayoutX(maxW/2);
			label2.setLayoutY(maxH/2 - 50);
			
			Circle c2 = new Circle(maxW/4.5);
			c2.setStyle("-fx-fill: #5b9ad5");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2);
			c2.setLayoutY(maxH/2);
			
			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));

			label1.setOnMouseClicked(e-> Label_click(label1, e));
			label2.setOnMouseClicked(e->  Label_click(label2, e));
			label1.setOnMouseDragged(e -> DragLabel(label1,  e));
			label2.setOnMouseDragged(e -> DragLabel(label2,  e));			
			
			
			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(label1);
			root.getChildren().add(label2);
		}
		else if (code.equals("011"))
		{
			Label label1 = new Label("Label1");
			label1.setLayoutX(maxW/5.5+20);
			label1.setLayoutY(maxH/2 - 50);
			
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);
			
			Label label2 = new Label("Label2");
			label2.setLayoutX(maxW/2.75);
			label2.setLayoutY(maxH/2 - 50);
			
			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #30e845");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);
			
			Label label3 = new Label("Label3");
			label3.setLayoutX(maxW/1.85);
			label3.setLayoutY(maxH/2 - 50);
			
			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #5b9ad5");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);		

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));
			

			label1.setOnMouseClicked(e-> Label_click(label1, e));
			label2.setOnMouseClicked(e-> Label_click(label2, e));
			label3.setOnMouseClicked(e-> Label_click(label3, e));
			
			label1.setOnMouseDragged(e -> DragLabel(label1,  e));
			label2.setOnMouseDragged(e -> DragLabel(label2,  e));
			label3.setOnMouseDragged(e -> DragLabel(label3,  e));

			
			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(c3);
			root.getChildren().add(label1);
			root.getChildren().add(label2);
			root.getChildren().add(label3);
		}
		else if (code.equals("110"))
		{
			Label label1 = new Label("Label1");
			label1.setLayoutX(maxW/5.5 + 20);
			label1.setLayoutY(maxH/2 - 50);
			
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #30e845");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			Label label2 = new Label("Label2");
			label2.setLayoutX(maxW/2.75);
			label2.setLayoutY(maxH/2 - 50);
			
			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #ffbf00");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			Label label3 = new Label("Label2");
			label3.setLayoutX(maxW/1.85);
			label3.setLayoutY(maxH/2 - 50);
			
			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #5b9ad5");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);	
			
			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));
			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

			label1.setOnMouseClicked(e-> Label_click(label1, e));
			label2.setOnMouseClicked(e-> Label_click(label2, e));
			label3.setOnMouseClicked(e-> Label_click(label3, e));
			label1.setOnMouseDragged(e -> DragLabel(label1,  e));
			label2.setOnMouseDragged(e -> DragLabel(label2,  e));
			label3.setOnMouseDragged(e -> DragLabel(label3,  e));
			
			
			
			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(c3);
			root.getChildren().add(label1);
			root.getChildren().add(label2);
			root.getChildren().add(label3);
		}
		else if (code.equals("101"))
		{
			Label label1 = new Label("Label1");
			label1.setLayoutX(maxW/5.5 + 20);
			label1.setLayoutY(maxH/2 - 50);
			
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			//middle
			Label label2 = new Label("Label2");
			label2.setLayoutX(maxW/2.75);
			label2.setLayoutY(maxH/2 - 50);
			
			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #5b9ad5");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			Label label3 = new Label("Label3");
			label3.setLayoutX(maxW/1.85);
			label3.setLayoutY(maxH/2 -50);
			
			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #30e845");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);		
			
			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));
			
			label1.setOnMouseClicked(e-> Label_click(label1, e));
			label2.setOnMouseClicked(e-> Label_click(label1, e));
			label3.setOnMouseClicked(e-> Label_click(label1, e));
			label1.setOnMouseDragged(e -> DragLabel(label1,  e));
			label2.setOnMouseDragged(e -> DragLabel(label2,  e));
			label3.setOnMouseDragged(e -> DragLabel(label3,  e));
					

			
			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(c3);
			root.getChildren().add(label1);
			root.getChildren().add(label2);
			root.getChildren().add(label3);
		}
		else if (code.equals("111"))
		{	

			Label label1 = new Label("Label1");
			label1.setLayoutX(maxW/4 + 20);
			label1.setLayoutY(maxH/1.5 - 50);
			
			//bottom left
			Circle c1 = new Circle(maxW/7);
			c1.setStyle("-fx-fill: #5b9ad5");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/4+20);
			c1.setLayoutY(maxH/1.5);

			Label label2 = new Label("Label2");
			label2.setLayoutX(maxW/2.5);
			label2.setLayoutY(maxH/1.5 - 50);
			
			//bottom right
			Circle c2 = new Circle(maxW/7);
			c2.setStyle("-fx-fill: #30e845");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.5);
			c2.setLayoutY(maxH/1.5);

			Label label3 = new Label("Label3");
			label3.setLayoutX(maxW/3.225);
			label3.setLayoutY(maxH/2.75 - 50);
			
			//top
			Circle c3 = new Circle(maxW/7);
			c3.setStyle("-fx-fill: #ffbf00");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/3.225);
			c3.setLayoutY(maxH/2.75);
			
			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));

			cp1.setOnAction(e->changeCol(cp1.getValue(), c1));
			cp2.setOnAction(e->changeCol(cp2.getValue(), c2));
			cp3.setOnAction(e->changeCol(cp3.getValue(), c3));
			
			label1.setOnMouseClicked(e-> Label_click(label1, e));
			label2.setOnMouseClicked(e-> Label_click(label2, e));
			label3.setOnMouseClicked(e-> Label_click(label3, e));
			label1.setOnMouseDragged(e -> DragLabel(label1,  e));
			label2.setOnMouseDragged(e -> DragLabel(label2,  e));
			label3.setOnMouseDragged(e -> DragLabel(label3,  e));
			
			root.getChildren().add(c3);
			root.getChildren().add(c1);
			root.getChildren().add(c2);
			root.getChildren().add(label1);
			root.getChildren().add(label2);
			root.getChildren().add(label3);
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

					if( (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 ) || e.getButton().equals(MouseButton.SECONDARY))
					{
						TextInputDialog dialog = new TextInputDialog();
						dialog.setTitle("Set Text");
						dialog.setHeaderText(null);
						dialog.setContentText("");
						dialog.getDialogPane().getButtonTypes().clear();
						
						ButtonType delete = new ButtonType("delete");
												
						dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL,delete );
									
						
						Button btnDel = (Button)dialog.getDialogPane().lookupButton(delete);
						btnDel.addEventFilter(ActionEvent.ACTION, event->
						{
							
							  Alert alert = new Alert(AlertType.CONFIRMATION);
							  alert.setContentText("Are you sure you want to delete this text box?");
							  alert.setHeaderText(null); alert.getButtonTypes().clear();
							  alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
							  
							  dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
							  dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
							  dialog.getDialogPane().lookupButton(delete).setDisable(true);
							  
							  Optional<ButtonType> result = alert.showAndWait(); if (result.get() ==
							  ButtonType.YES) { root.getChildren().remove(b); } else { alert.close();
							 
							  }
							 						
							root.getChildren().remove(b);						
							
							
						});
						
						Optional<String> result = (dialog).showAndWait();
						
						if (result.isPresent())
						{
							if(result.get().length()==0)
								root.getChildren().remove(b);
							l.setText(result.get());
						}
						
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
	
	private void Save_Venn(TextArea t) throws IOException {
	
	}
	private void SaveFile(String content, File file) throws IOException{
    
          
    }
	private void LoadFile(String content, File file) throws IOException{
        
        FileWriter fileWriter;
          
        fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    
      
}
	private void changeCol(Color x, Circle y) {
		
	}
	private void Label_click(Label l,MouseEvent e) {
		if( (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 ) || e.getButton().equals(MouseButton.SECONDARY))
		{
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Set Label");
			dialog.setHeaderText(null);
			dialog.setContentText("");
			dialog.getDialogPane().getButtonTypes().clear();
			
									
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL );
						
			
			Optional<String> result = (dialog).showAndWait();
			
			if (result.isPresent())
			{
				if(result.get().length()==0) {
					l.setText(l.getText());
				}
				else
				l.setText(result.get());
			}	
	}
}
	private void DragLabel(Label l, MouseEvent e) {
		
		if( e.getButton().equals(MouseButton.PRIMARY))
		{
			l.setLayoutX(l.getLayoutX()+e.getX()-l.getWidth()/2);
			l.setLayoutY(l.getLayoutY()+e.getY()-l.getHeight()/2);
		}
	
	}
	private void info(Button b, MouseEvent e) {
		
	}
}


