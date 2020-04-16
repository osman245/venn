package Venn;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MultiEdit  {

	private ArrayList<TextBox> boxes;
	ArrayList<CheckBox> checkBoxes;
	private Stage s;

	public MultiEdit(List<TextBox> textBoxes, Pane root)
	{
		boxes = (ArrayList<TextBox>) textBoxes;

		if(boxes.size() == 0)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("MUST SELECT ATLEAST 1 TEXTBOX TO EDIT");
			alert.setTitle("ERROR");
		//	alert.initModality(Modality.NONE);
			((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
			alert.show();
		}
		else
		{
			startStage();
		}
	}

	private void startStage() 
	{


		s = new Stage();
		s.setTitle("Customization window");
		s.setResizable(false);
		s.setAlwaysOnTop(true);

		s.setOnCloseRequest(e->{

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to discard this form?");
			alert.setTitle("Exit Form");
		//	alert.initModality(Modality.NONE);
			((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
			alert.showAndWait().ifPresent(response ->{
				if(response == ButtonType.YES)
				{
					e.consume();
					s.close();
				}
				else
				{
					e.consume();
				}
			});

		});

		VBox root = new VBox();
		root.setPadding(new Insets(15,15,15,15));
		root.setSpacing(15);
		//root.setPrefHeight(285);

		Label title = new Label("Select which textboxes to edit");
		title.setStyle("-fx-font-size:18;");
		title.setAlignment(Pos.CENTER);

		VBox innerPane = new VBox();
		innerPane.setSpacing(-140);// adding tb's to this
		//innerPane.setAlignment(Pos.CENTER);


		checkBoxes = new ArrayList<CheckBox>();

		root.getChildren().add(title);

		for(int i=0; i< boxes.size(); i++)
		{


			CheckBox c = new CheckBox();
			c.setId(""+i);
			c.setPrefHeight(30);
			checkBoxes.add(c);
			


			TextBox t = new TextBox(boxes.get(i).getTextObj().getText());
			t.setExpand(false);
			
			t.getTextObj().setStyle(boxes.get(i).getTextObj().getStyle());
			t.getPane().setStyle(boxes.get(i).getPane().getStyle());
			t.getTextField().setStyle(boxes.get(i).getTextField().getStyle());

			HBox temp = new HBox(10);
			//temp.setAlignment(Pos.CENTER);
			temp.getChildren().addAll(t,c);

			innerPane.getChildren().add(temp);


		}

		ScrollPane sp = new ScrollPane();
		sp.setPrefSize(180, 200);
		//sp.setPannable(true);
		sp.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		sp.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
		sp.setContent(innerPane);


		Button btnDone = new Button("Done");
		btnDone.setStyle("-fx-background-color: #8f7a66; -fx-font-size:15px;-fx-font-color:white; -fx-font-weight:bold;");
		btnDone.setAlignment(Pos.CENTER);
		btnDone.setPrefWidth(125);
		btnDone.setOnAction(e->customize());
		
		Button btnDelete = new Button("Delete");
		btnDelete.setStyle("-fx-background-color: #8f7a66; -fx-font-size:15px;-fx-font-color:white; -fx-font-weight:bold;");
		btnDelete.setAlignment(Pos.CENTER);
		btnDelete.setPrefWidth(125);
		btnDelete.setOnAction(e->delete());
		
		HBox temp2 = new HBox(20);
		temp2.getChildren().addAll(btnDone, btnDelete);

		root.getChildren().addAll(sp, temp2);

		Scene scene = new Scene(root);
		s.setScene(scene);
		s.show();



	}

	private void delete()
	{
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete these text boxes?");
		alert.setTitle("delete selected");
	//	alert.initModality(Modality.NONE);
		((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
		alert.showAndWait().ifPresent(response ->{
			if(response == ButtonType.YES)
			{
		
			}
			else
				return;
		
		});
		
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for(int i=0; i< boxes.size(); i++)
		{
			if(checkBoxes.get(i).isSelected())
			{
				tempList.add(Integer.parseInt(checkBoxes.get(i).getId()));
			}
		}

		if(tempList.size() == 0)
		{
			 alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("MUST SELECT ATLEAST 1 TEXTBOX TO DELETE");
			alert.setTitle("ERROR");
			//alert.initModality(Modality.NONE);
			((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
			alert.show();
		}
		else
		{
			for(int i=0; i< tempList.size(); i++)
			{
				TextBox t = boxes.get(tempList.get(i));
				Pane tempPane = (Pane) t.getParent();
				tempPane.getChildren().remove(t);
				
			}
			ArrayList<TextBox> temp = (ArrayList<TextBox>) boxes.clone();
			for(int i=0;i< checkBoxes.size(); i++)
			{
				if(checkBoxes.get(i).isSelected())
				{
					boxes.remove(temp.get(i));
				}
			}
			
			
			s.close();
		}
	}

	private void customize() 
	{

		TextBox dummy = new TextBox();

		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for(int i=0; i< boxes.size(); i++)
		{
			if(checkBoxes.get(i).isSelected())
			{
				tempList.add(Integer.parseInt(checkBoxes.get(i).getId()));
			}
		}

		if(tempList.size() == 0)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("MUST SELECT ATLEAST 1 TEXTBOX TO EDIT");
			alert.setTitle("ERROR");
			//alert.initModality(Modality.NONE);
			((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
			alert.show();
		}
		else
			new CustomizationWindow(dummy,boxes ,tempList, s);

	}

}
