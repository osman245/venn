package Venn;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TextBox extends VBox
{

	private Text t;
	//private VBox p;
	Pane root;
	private TextArea field;
	private HBox  topRow;
	boolean isPreview;
	private boolean expandable;
	private String fieldTxtCol;

	public TextBox()
	{
		this("");
	}

	public TextBox(String text)
	{
		//p = new VBox();
		super();
		topRow = new HBox();
		topRow.setPadding(new Insets(5,15,5,5));
		topRow.setAlignment(Pos.CENTER);
		topRow.setPrefHeight(30);

		setAlignment(Pos.CENTER);	
		setPrefWidth(150);

		t = new Text();
		t.setText(text);
		//	t.setWrappingWidth(100);
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFontSmoothingType(FontSmoothingType.GRAY);	
		t.setTextAlignment(TextAlignment.CENTER);

		topRow.getChildren().add(t);

		field = new TextArea();
		field.setPrefSize(100,150);
		//field.setAlignment(Pos.TOP_LEFT);
		field.setDisable(true);
		field.setVisible(false);
		field.setWrapText(true);

		setDefaultStyle();
		isPreview = false;
		expandable = true;

		getChildren().addAll(topRow, field);

		//setOnMouseDragged(e->move(e));
		//setOnMouseClicked(e->mouseClickEvent(e));

	}

	public void setFieldTxtCol(String c)
	{
		fieldTxtCol = c;
	}
	
	public String getFieldTxtCol()
	{
		return fieldTxtCol;
	}
	
	private void setDefaultStyle() 
	{	
		String bgStyle = "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5; -fx-background-color: "+"transparent"+"; -fx-border-color: "+"black"+"; -fx-border-width:"+1+";";
		String txtStyle = "-fx-font-family: "+"Arial"+"; -fx-underline: "+"false"+";-fx-font-size: "+15+"; -fx-font-weight: "+"normal"+"; -fx-fill: "+"black"+"; -fx-font-style: "+ "normal"+";";
		String fieldStyle ="-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5; -fx-font-family: "+"Arial"+"; -fx-text-fill: "+"black"+"; -fx-border-color: "+"black"+"; -fx-font-size: "+15+";";
		fieldTxtCol ="black";
		
		getTextObj().setStyle(txtStyle);
		getPane().setStyle(bgStyle);
		getTextField().setStyle(fieldStyle);
	}

	void expand() 
	{
		if(expandable)
		{
			if (field.isVisible())
			{
				//close
				field.setDisable(true);
				field.setVisible(false);
			}
			else
			{
				//open
				field.setDisable(!true);
				field.setVisible(!false);
			}
		}
	}

	public void setExpand(boolean expand)
	{
		this.expandable = expand;
	}

	public boolean getExpand()
	{
		return expandable;
	}


	
	
	public Text getTextObj()
	{
		return t;
	}

	public Node getTextField()
	{
		return field;
	}

	public Node getNode()
	{
		return this;
	}

	public Pane getPane()
	{
		return topRow;
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

	public boolean isPreview() {
		return isPreview;
	}

	public void setPreview(boolean isPreview) {
		this.isPreview = isPreview;
	}
	
	/*
	 * returns a string in the format of
	 * [obj class],[title text],[field text],[text style], [pane style], [text field style], [X position], [Y position]
	 */
	public String toString()
	{
		String ans ="";
		
		ans += "textbox,";
		ans += t.getText()+",";
		ans += field.getText()+",";
		ans += t.getStyle()+",";
		ans += getPane().getStyle()+",";
		ans += getTextField().getStyle()+",";
		ans += getX()+",";
		ans += getY() +",";
		
		
		return ans;
	}

}
