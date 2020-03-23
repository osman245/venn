package Venn;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TextBox extends TitledPane
{

	private Text t;
	//	private VBox p;
	private Pane root;


	public TextBox()
	{
		this("");
	}

	public TextBox(String text)
	{

		//p = new VBox();
		super();
		setAlignment(Pos.CENTER);	
		setExpanded(false);
		setAnimated(true);
		setPrefWidth(100);

		t = new Text();
		t.setText(text);
	//	t.setWrappingWidth(100);
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFontSmoothingType(FontSmoothingType.GRAY);	

		TextField field = new TextField();
		field.setPrefSize(100,150);
		field.setAlignment(Pos.TOP_LEFT);
		
		setStyle("-fx-border-color: blue;");
		setGraphic(t);
		setContent(field);

		setOnMouseDragged(e->move(e));
		setOnMouseClicked(e->mouseClickEvent(e));

	}

	private void mouseClickEvent(MouseEvent e)
	{

		/*(e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 ) || */
		if(  e.getButton().equals(MouseButton.SECONDARY))
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

				root.getChildren().remove(getNode());						


			});

			Optional<String> result = (dialog).showAndWait();

			if (result.isPresent())
			{
				if(result.get().length()==0)
					root.getChildren().remove(getNode());
				t.setText(result.get());
			}

		}

		/*
		 * if(e.getButton().equals(MouseButton.PRIMARY)) { if(isExpanded())
		 * setExpanded(false); else setExpanded(true); }
		 */
	}


	private void move(MouseEvent e) 
	{
		if( e.getButton().equals(MouseButton.PRIMARY))
		{
			setXpos(getX()+e.getX()-getPrefWidth()/2);
			setYpos(getY()+e.getY()-getPrefHeight()/2);
			setExpanded(false);
		}
	}

	public Node getNode()
	{
		return this;
	}
	public void setTitleText(String text)
	{
		t.setText(text);
	}

	public void setContainerStyle(String style)
	{
		setStyle(style);
	}
	public void setTextStyle(String style)
	{
		t.setStyle(style);
	}
	public void setSize(double width, double height)
	{
		setPrefSize(width, height);
		t.setWrappingWidth(width);
	}

	public void setRoot(Pane root)
	{
		this.root = root;
	}
	public void setXpos(double xPos)
	{
		setLayoutX(xPos);
	}
	public void setYpos(double yPos)
	{
		setLayoutY(yPos);
	}

	public double getX() 
	{
		return getLayoutX();
	}
	public double getY() 
	{
		return getLayoutY();
	}



}