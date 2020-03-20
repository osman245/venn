package Venn;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;

public class TextBox 
{
	private Button b;
	private Text t;
	private Pane p;
	private Pane root;
	private double xpos, ypos;
	private double width, height;
	
	
	public TextBox()
	{
		this("");
	}
	
	public TextBox(String text)
	{
		b = new Button();
		b.setAlignment(Pos.CENTER);
		
		p = new Pane();
		
		t = new Text();
		t.setText(text);
		t.setWrappingWidth(150);
		t.setFontSmoothingType(FontSmoothingType.GRAY);
			
		b.setGraphic(t);
		p.getChildren().addAll(b);
		
		xpos =0;
		ypos =0;	
	}
	
	public Button getButton()
	{
		return b;
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
		b.setStyle(style);
	}
	
	public void setTextStyle(String style)
	{
		t.setStyle(style);
	}
	public void setSize(double width, double height)
	{	
		b.setPrefSize(width, height);
		p.setPrefSize(width, height);
		t.setWrappingWidth(width);
	}

	public void setRoot(Pane root) 
	{
		this.root = root;
	}
	
	public void setPos(double xPos, double yPos)
	{
		b.setLayoutX(xPos);
		b.setLayoutY(yPos);
		
		p.setLayoutX(xPos);
		p.setLayoutY(yPos);
	}
	
	public double getX() {return b.getLayoutX();}
	public double getY() {return b.getLayoutY();}
	public double getWidth() {return b.getPrefWidth();}
	public double getHeight() {return b.getPrefHeight();}
	
	
}
