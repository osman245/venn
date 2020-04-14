package Venn;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import Venn_form.Form;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
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
	private Button initLoad;
	//private String code;
	protected Pane root;
	protected double maxH, maxW;
	//private int txtCount =0;
	protected Scene scene;
	Circle c1 = new Circle();
	Circle c2 = new Circle();
	Circle c3 = new Circle();
	Label label1 = new Label();
	Label label2 = new Label();
	Label label3 = new Label();
	private ArrayList<TextBox> textBoxes;



	public Venn()
	{
		startStage();

	}

	private void startStage() {
		// making a basic gui

		this.setMaximized(true);
		this.setFullScreen(false);


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

		scene = new Scene(root,500,450);
		root.setStyle("-fx-background-color:#D67822;");


		//title for window
		Text txtTitle = new Text("VennSmart");
		txtTitle.setStyle("-fx-fill: #ffffff; -fx-font-family: Roboto; -fx-font-size: 150px; -fx-font-weight:bold;");
		txtTitle.setStroke(Color.BROWN);

		//graphic text for the button
		Text txtInit = new Text("Create a new Venn Diagram");
		txtInit.setFontSmoothingType(FontSmoothingType.GRAY);
		txtInit.setStyle("-fx-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;");

		Text txtLoad = new Text("Load a new Venn Diagram");
		txtLoad.setFontSmoothingType(FontSmoothingType.GRAY);
		txtLoad.setStyle("-fx-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;");



		init = new Button();
		init.setGraphic(txtInit);
		init.setStyle("-fx-background-color: #8f7a66;");
		init.setPrefSize(200, 75);
		init.setAlignment(Pos.CENTER);

		initLoad = new Button();
		initLoad.setGraphic(txtLoad);
		initLoad.setStyle("-fx-background-color: #8f7a66;");
		initLoad.setPrefSize(200, 75);
		initLoad.setAlignment(Pos.CENTER);

		init.setOnAction(e ->{
			Form f = new Form();
			f.setButton(init);
			init.setDisable(true);
			initLoad.setDisable(true);
		});

		HBox title = new HBox(10);
		HBox panel = new HBox(100);

		title.getChildren().add(txtTitle);


		panel.setAlignment(Pos.CENTER);
		panel.getChildren().addAll(init,initLoad);
		panel.setLayoutX(panel.getPrefWidth()+400);
		panel.setLayoutY(panel.getPrefHeight()+400);
		title.setLayoutX(panel.getPrefWidth() +280);
		title.setLayoutY(panel.getPrefWidth());
		root.getChildren().addAll(panel,title);

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


	public void init(String code) throws IOException
	{
		//init.setVisible(false);
		root.getChildren().clear();

		//clicking this will parse a text area and every new line will create a new text field

		//obsolete code
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
		 * 
		 * root.getChildren().addAll(add,ta);
		 */

		// new text box adder is here
		createTextBoxAdder();


		//temp button to be added to menu bar
		Button btnMultiEdit = new Button("multi edit");
		btnMultiEdit.setOnAction(e-> new MultiEdit(textBoxes, root));
		btnMultiEdit.setLayoutY(200);
		root.getChildren().add(btnMultiEdit);



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
		c1size.setOnAction(e -> Csize(c1,root));
		c2size.setOnAction(e -> Csize(c2,root));
		c3size.setOnAction(e -> Csize(c3,root));


		c1drag.setOnAction(e -> Cdrag(c1,root));

		//save
		Menu save = new Menu("Save");
		save.setOnAction(e -> {
			/*  // the content of scrollPane is saved as a JPEG file.
		    WritableImage img = root.snapshot(new SnapshotParameters(), null);
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		    BufferedImage img2 = SwingFXUtils.fromFXImage(img, null);
		    int result = chooser.showSaveDialog(null);
		    if (result == JFileChooser.APPROVE_OPTION) {
		        try {
		            File fileToSave = chooser.getSelectedFile();
		            ImageIO.write(img2, "png", fileToSave);
		        } catch (IOException ex) {
		          //  Logger.getLogger(GuiClass.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }*/
			WritableImage writableImage = 
					new WritableImage((int)root.getWidth(), (int)root.getHeight());
			scene.snapshot(writableImage);

			File file = new File("snapshot.png");
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
				System.out.println("snapshot saved: " + file.getAbsolutePath());
			} catch (IOException ex) {
				//Logger.getLogger(JavaFXSnapshot.class.getName()).log(Level.SEVERE, null, ex);
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






	private void createTextBoxAdder() 
	{
		// TODO Auto-generated method stub
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
					if(event.getButton() == MouseButton.PRIMARY && tbPane.getChildren().contains(t))
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

				t.setOnMouseClicked(event -> mouseClickEvent(event, t, root, textBoxes));

				textBoxes.add(t);
				//tbPane.getChildren().add(t);
				tbPane.getChildren().add(t);
				//t.setManaged(false);

				tf.clear();
			}
		});

	}

	public static void mouseClickEvent(MouseEvent e, TextBox t, Pane root, ArrayList<TextBox>textBoxes)
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

				Pane temp = (Pane) t.getParent();
				
				temp.getChildren().remove(t);
				textBoxes.remove(t);

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

	/*
	 * OUTDATED METHOD
	 * protected void addTextBox(TextArea t) { String[] inputs =
	 * t.getText().split("\n"); int c=0;
	 * 
	 * for(int i=0; i< inputs.length; i++) { //-fx-border-color: #8f7a66
	 * if(!inputs[i].isEmpty() && inputs[i].trim().length()>0) {
	 * 
	 * TextBox tb = new TextBox(); //tb.setSize(100, 30); //tb.setPrefWidth(100);
	 * tb.setTitleText(inputs[i]);
	 * //tb.setContainerStyle("-fx-background-color: green;"); tb.
	 * setTextStyle("-fx-text-fill: black; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold;"
	 * ); tb.setRoot(root); tb.setXpos(maxW-tb.getPrefWidth()-40);
	 * tb.setYpos(tb.getPrefHeight()*c);
	 * 
	 * c++;
	 * 
	 * root.getChildren().add(tb); } }
	 * 
	 * //clear the text area t.clear();
	 * 
	 * //add window limits so you cant drag off screen //going to add right click to
	 * access options }
	 */
	protected void SaveVenn(TextArea t) throws IOException {

	}

	protected void Label_click(Label l,MouseEvent e) {
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
	protected void DragLabel(Label l, MouseEvent e) {

		if( e.getButton().equals(MouseButton.PRIMARY))
		{
			l.setLayoutX(l.getLayoutX()+e.getX()-l.getWidth()/2);
			l.setLayoutY(l.getLayoutY()+e.getY()-l.getHeight()/2);
		}

	}
	/*	private void info(Button b, MouseEvent e) {

	}*/	
	protected void Ccolor(Circle circle,Pane root) {
		ColorPicker cp = new ColorPicker((Color) circle.getFill());
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
	protected void Lcolor(Label label,Pane root) {
		ColorPicker cp = new ColorPicker((Color) label.getTextFill());
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

	}
	protected void Cdrag(Circle circle, Pane root) {
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
	void Bcolor(Scene scene,Pane root) {
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

	public void saveAsPng() {

	}
	void Csize(Circle c,Pane root) {

		Slider x = new Slider();

		x.setPrefSize(150, 30);
		x.setLayoutX(10);
		x.setLayoutY(50);
		x.setMax(300);
		x.setMin(150);
		x.setShowTickLabels(true);
		x.setOnDragDetected(e -> {
			c.setRadius(x.getValue());
		});
		x.setOnDragDone(e -> {
			root.getChildren().remove(x);
		});
		root.getChildren().add(x);

	}

	public  ArrayList<TextBox> getTextBoxes() {
		// TODO Auto-generated method stub
		return textBoxes;
	}

}