package Venn;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TextBox 
{

	private Text t;
	private VBox p;
	private Pane root;


	public TextBox()
	{
		this("");
	}

	public TextBox(String text)
	{

		p = new VBox();
		p.setAlignment(Pos.CENTER);	

		t = new Text();
		t.setText(text);
		t.setWrappingWidth(150);
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFontSmoothingType(FontSmoothingType.GRAY);			

		p.getChildren().addAll(t);


	}

	public Node getNode()
	{
		return p;
	}
	public void setText(String text)
	{
		t.setText(text);
	}

	public void setContainerStyle(String style)
	{
		p.setStyle(style);}
	public void setTextStyle(String style)
	{
		t.setStyle(style);
	}
	public void setSize(double width, double height)
	{
		p.setPrefSize(width, height);
		t.setWrappingWidth(width);
	}

	public void setRoot(Pane root)
	{
		this.root = root;
	}
	public void setXpos(double xPos)
	{
		p.setLayoutX(xPos);
	}
	public void setYpos(double yPos)
	{
		p.setLayoutY(yPos);
	}

	public double getX() 
	{
		return p.getLayoutX();
	}
	public double getY() 
	{
		return p.getLayoutY();
	}
	public double getWidth()
	{
		return p.getPrefWidth();
	}
	public double getHeight() 
	{
		return p.getPrefHeight();
	}


}
