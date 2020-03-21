package Venn;

//import java.io.File;
//import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import Venn_form.Form;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Venn extends Stage
{
	private Button init;
	//private String code;
	private Pane root;
	private double maxH, maxW;
	//private int txtCount =0;
	private Scene scene;
	Circle c1 = new Circle();
	Circle c2 = new Circle();
	Circle c3 = new Circle();
	Label label1 = new Label();
	Label label2 = new Label();
	Label label3 = new Label();

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
		
		root.getChildren().addAll(add,ta);
		
	
		//----------------MENU BAR---------------------------
		MenuBar customization = new MenuBar();
		customization.setLayoutX(root.getPrefWidth());
		customization.setLayoutY(root.getPrefHeight());
		
		//labels
		Menu labelcustom = new Menu("Labels");
		Menu lcolor = new Menu("Label color");
		MenuItem l1color = new MenuItem("label1");
		MenuItem l2color = new MenuItem("label2");
		MenuItem l3color = new MenuItem("label3");
		MenuItem lfont = new Menu("Label font");
		MenuItem lsize = new Menu("Label size");
		
		l1color.setOnAction(e ->Lcolor(label1,root));
		l2color.setOnAction(e ->Lcolor(label2,root));
		l3color.setOnAction(e ->Lcolor(label3,root));
		
		
		labelcustom.getItems().addAll(lcolor,lfont,lsize);
		
		//title
		Menu titlecustom = new Menu("Title");
		MenuItem tcolor = new MenuItem("Title color");
		MenuItem tfont = new MenuItem("Title font");
		MenuItem tsize = new MenuItem("Title size");
		
		titlecustom.getItems().addAll(tcolor,tfont,tsize);
		
		//circles
		Menu circlecustom = new Menu("Circle");
		
		Menu ccolor = new Menu("Circle color");
		MenuItem c1color = new MenuItem("circle1");
		MenuItem c2color = new MenuItem("circle2");
		MenuItem c3color = new MenuItem("circle3");
		
		Menu csize = new Menu("Circle size");
		MenuItem c1size = new MenuItem("circle1");
		MenuItem c2size = new MenuItem("circle2");
		MenuItem c3size = new MenuItem("circle3");
		
		Menu cdrag = new Menu("Circle drag");
		MenuItem c1drag = new MenuItem("c1drag");
		MenuItem c2drag = new MenuItem("c2drag");
		MenuItem c3drag = new MenuItem("c3drag");
		
		circlecustom.getItems().addAll(ccolor,csize,cdrag);
		
		//background
		Menu backgroundcustom = new Menu("Background");
		MenuItem bcolor = new MenuItem("Background color");
		backgroundcustom.getItems().add(bcolor);
		bcolor.setOnAction(e -> Bcolor(scene,root));
		
		
		c1color.setOnAction(e -> Ccolor(c1,root));
		c2color.setOnAction(e -> Ccolor(c2,root));
		c3color.setOnAction(e -> Ccolor(c3,root));
		
		
		c1drag.setOnAction(e -> Cdrag(c1,root));
		
		//save
		Menu save = new Menu("Save");
		save.setOnAction(e -> {
			try {
				SaveVenn(ta);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		customization.getMenus().addAll(labelcustom,titlecustom,save,circlecustom,backgroundcustom);
		root.getChildren().add(customization);
				
		if(code.length() == 1)
		{	
			lcolor.getItems().addAll(l1color,l2color);
			ccolor.getItems().addAll(c1color,c2color);
			csize.getItems().addAll(c1size,c2size);
			cdrag.getItems().addAll(c1drag,c2drag);
		}
		else
		{			
			lcolor.getItems().addAll(l1color,l2color,l3color);
			ccolor.getItems().addAll(c1color,c2color,c3color);
			csize.getItems().addAll(c1size,c2size,c3size);
			cdrag.getItems().addAll(c1drag,c2drag,c3drag);
		}
		
		//setting up the event handlers 
		//adding the circles to the scene here
		if(code.equals("1"))
		{
			
			label1.setText("Label1");
			label1.setLayoutX(maxW/4+10);
			label1.setLayoutY(maxH/2 - 50);

			
			c1.setRadius(maxW/4.5);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/4+10);
			c1.setLayoutY(maxH/2);
			
			label2.setText("Label2");
			label2.setLayoutX(maxW/2);
			label2.setLayoutY(maxH/2 - 50);
			
			c2.setRadius(maxW/4.5);
			c2.setStyle("-fx-fill: #5b9ad5");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2);
			c2.setLayoutY(maxH/2);
			
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
			
			c1.setRadius(maxW/7);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);
			
			Label label2 = new Label("Label2");
			label2.setLayoutX(maxW/2.75);
			label2.setLayoutY(maxH/2 - 50);
			
			c2.setRadius(maxW/7);
			c2.setStyle("-fx-fill: #30e845");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);
			
			Label label3 = new Label("Label3");
			label3.setLayoutX(maxW/1.85);
			label3.setLayoutY(maxH/2 - 50);
			
			c3.setRadius(maxW/7);
			c3.setStyle("-fx-fill: #5b9ad5");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);		

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
			label1.setText("Label1");
			label1.setLayoutX(maxW/5.5 + 20);
			label1.setLayoutY(maxH/2 - 50);
			
			c1.setRadius(maxW/7);
			c1.setStyle("-fx-fill: #30e845");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			label2.setText("Label2");
			label2.setLayoutX(maxW/2.75);
			label2.setLayoutY(maxH/2 - 50);
			
			c2.setRadius(maxW/7);
			c2.setStyle("-fx-fill: #ffbf00");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			label3.setText("Label2");
			label3.setLayoutX(maxW/1.85);
			label3.setLayoutY(maxH/2 - 50);
			
			c3.setRadius(maxW/7);
			c3.setStyle("-fx-fill: #5b9ad5");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);	

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
			label1.setText("Label1");
			label1.setLayoutX(maxW/5.5 + 20);
			label1.setLayoutY(maxH/2 - 50);
			
			c1.setRadius(maxW/7);
			c1.setStyle("-fx-fill: #ffbf00");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/5.5+20);
			c1.setLayoutY(maxH/2);

			//middle
			label2.setText("Label2");
			label2.setLayoutX(maxW/2.75);
			label2.setLayoutY(maxH/2 - 50);
			
			c2.setRadius(maxW/7);
			c2.setStyle("-fx-fill: #5b9ad5");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.75);
			c2.setLayoutY(maxH/2);

			label3.setText("Label3");
			label3.setLayoutX(maxW/1.85);
			label3.setLayoutY(maxH/2 -50);
			
			c3.setRadius(maxW/7);
			c3.setStyle("-fx-fill: #30e845");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/1.85);
			c3.setLayoutY(maxH/2);		
						
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

			label1.setText("Label1");
			label1.setLayoutX(maxW/4 + 20);
			label1.setLayoutY(maxH/1.5 - 50);
			
			//bottom left
			c1.setRadius(maxW/7);
			c1.setStyle("-fx-fill: #5b9ad5");
			c1.setOpacity(0.5);
			c1.setLayoutX(maxW/4+20);
			c1.setLayoutY(maxH/1.5);

			label2.setText("Label2");
			label2.setLayoutX(maxW/2.5);
			label2.setLayoutY(maxH/1.5 - 50);
			
			//bottom right
			c2.setRadius(maxW/7);
			c2.setStyle("-fx-fill: #30e845");
			c2.setOpacity(0.5);
			c2.setLayoutX(maxW/2.5);
			c2.setLayoutY(maxH/1.5);

			label3.setText("Label3");
			label3.setLayoutX(maxW/3.225);
			label3.setLayoutY(maxH/2.75 - 50);
			
			//top
			c3.setRadius(maxW/7);
			c3.setStyle("-fx-fill: #ffbf00");
			c3.setOpacity(0.5);
			c3.setLayoutX(maxW/3.225);
			c3.setLayoutY(maxH/2.75);
			
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
	private void SaveVenn(TextArea t) throws IOException {
	
	}
/*	private void SaveFile(String content, File file) throws IOException{
    
          
    }
	private void LoadFile(String content, File file) throws IOException{
        
        FileWriter fileWriter;
          
        fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    
      
}*/	
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
/*	private void info(Button b, MouseEvent e) {
		
	}*/	
	private void Ccolor(Circle circle,Pane root) {
		ColorPicker cp = new ColorPicker(Color.rgb(255,191,0));
		cp.setPrefSize(200, 40);	
		cp.setLayoutX(root.getPrefWidth());
		cp.setLayoutY(root.getPrefHeight()+ 100);
		
		cp.setOnAction(e -> 
		{
		circle.setFill(cp.getValue());
		root.getChildren().remove(cp);
		});
	
		root.getChildren().add(cp);	
	}
	private void Lcolor(Label label,Pane root) {
		ColorPicker cp = new ColorPicker(Color.rgb(0,0,0));
		cp.setPrefSize(200, 40);	
		cp.setLayoutX(root.getPrefWidth());
		cp.setLayoutY(root.getPrefHeight()+ 100);
		
		cp.setOnAction(e -> 
		{
		label.setTextFill(cp.getValue());
		root.getChildren().remove(cp);
		});
	
		root.getChildren().add(cp);	
	}
	private void info() {
		
	};
	private void Cdrag(Circle circle, Pane root) {
		Button b = new Button("OFF");
		
		b.setOnMouseClicked(e -> Cdrag1(b,circle,root,e));
		
		root.getChildren().add(b);
	}
	private void Cdrag1(Button b,Circle circle, Pane root,MouseEvent y) {
		if(b.getText()=="OFF") {
			b.setText("ON");
			if( y.getButton().equals(MouseButton.PRIMARY))
			{
				circle.setLayoutX(circle.getLayoutX()+y.getX()-circle.getCenterX()/2);
				circle.setLayoutY(circle.getLayoutY()+y.getY()-circle.getCenterY()/2);
			}
		}
		else {
			b.setText("OFF");
			root.getChildren().remove(b);
		}
	

}
	private void Bcolor(Scene scene,Pane root) {
		ColorPicker cp = new ColorPicker(Color.rgb(255,191,0));
		cp.setPrefSize(200, 40);	
		cp.setLayoutX(root.getPrefWidth());
		cp.setLayoutY(root.getPrefHeight()+ 100);
		
		cp.setOnAction(e -> 
		{
			Paint fill = cp.getValue();
            BackgroundFill backgroundFill =  new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY);
            Background background = new Background(backgroundFill);
            root.setBackground(background);
			root.getChildren().remove(cp);
		});
	
		root.getChildren().add(cp);
	}

	}
	