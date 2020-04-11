package Venn;

import java.util.ArrayList;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TextBoxAdder extends VBox
{
	private ArrayList<TextBox> textBoxes;
	private Pane root;


	public TextBoxAdder(double height)
	{
		

	}

	public ArrayList<TextBox> getNodeList()
	{
		return textBoxes;
	}

	public Node getNode()
	{
		return this;
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}


}
