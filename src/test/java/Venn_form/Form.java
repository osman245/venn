package Venn_form;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * this class will create the interface for the venn builder form
 */
public class Form 
{
	Button b;
	
	public Form()
	{
		startStage();
		
	}

	private void startStage() {
		Stage s = new Stage();
		s.setTitle("Venn Builder Form");
		
		s.setOnCloseRequest(e->{
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to discard this form?");
			alert.setTitle("Exit Form");
			alert.initModality(Modality.NONE);
			alert.getButtonTypes().clear();
			alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.CANCEL);
			alert.showAndWait().ifPresent(response ->{
				if(response == ButtonType.YES)
				{
					b.setDisable(false);
					e.consume();
					s.close();
				}
				else
				{
					e.consume();
				}
			});;
			

		});
		
		
		GridPane root = new GridPane();
		root.setPadding(new Insets(15,15,15,15));
		root.setAlignment(Pos.TOP_CENTER);
		root.setHgap(20);
		
		// adding components 
		
		//label + combo box for n circles of choice
		Label lblRegions = new Label("Number of Regions");
		lblRegions.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");
		
		ComboBox<String> cboRegions = new ComboBox<String>();
		cboRegions.setPromptText("select a value");
		cboRegions.getItems().addAll("2","3","4");
		cboRegions.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
	
		
		
		root.add(lblRegions,0,0);
		root.add(cboRegions, 1, 0);
		
		Scene scene = new Scene(root,400,400);
		root.setStyle("-fx-background-color:#faf8ef;");
		
		s.setScene(scene);
		s.show();
		
		
		
	}
	
	public void setButton(Button b)
	{
		this.b = b;
	}
	
	
}
