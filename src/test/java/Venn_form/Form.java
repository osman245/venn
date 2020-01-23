package Venn_form;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * this class will create the interface for the venn builder form
 */
public class Form 
{
	Button b;
	//List<Boolean> selections;
	private GridPane root;
	private VBox test = new VBox(20);
	private  List<String> codes;
	private String codeTxt;

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


		root = new GridPane();
		root.setPadding(new Insets(15,15,15,15));
		root.setAlignment(Pos.TOP_CENTER);
		root.setHgap(20);
		root.setVgap(20);

		// adding components 

		//label + combo box for n circles of choice
		Label lblRegions = new Label("Number of Regions");
		lblRegions.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");
		

		ComboBox<String> cboRegions = new ComboBox<String>();
		cboRegions.setPromptText("select a value");
		cboRegions.getItems().addAll("2","3");
		cboRegions.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

		//add later
		/*
		 * Label lblShapes = new Label("Shape type:"); lblShapes.
		 * setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");
		 * 
		 * ComboBox<String> cboShapes = new ComboBox<String>();
		 * cboShapes.setPromptText("select a shape");
		 * cboShapes.getItems().addAll("circle","rounded rectangle");
		 * cboShapes.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
		 * cboShapes.setPrefWidth(cboRegions.getPrefWidth());
		 */

		//this pane will contain the set relations check boxes
		VBox setRelations = new VBox(20);

		//these lists will contain various set relationships for the users to check off
		List<CheckBox> chkBoxes = new ArrayList<CheckBox>();
		codes = new ArrayList<String>();
		codes.add("1");
		codes.add("111");
		codes.add("110");
		codes.add("101");
		//filling the sets with the needed check boxes and labels
		cboRegions.setOnAction(e->{
			if (cboRegions.getValue() != null)
				fillCheckArray(cboRegions.getValue(), chkBoxes);
		});


		root.add(lblRegions,0,0);
		root.add(cboRegions, 1, 0);
		root.add(setRelations, 0, 1);
		//root.add(lblShapes,0,1);
		//root.add(cboShapes, 1, 1);

		Scene scene = new Scene(root,400,400);
		root.setStyle("-fx-background-color:#faf8ef;");

		s.setScene(scene);
		s.show();



	}

	private void fillCheckArray(String value, List<CheckBox> c)
	{
		
		if(test.getChildren().size() >0) {
			test.getChildren().removeAll(c);
			c.clear();
		}

		switch(value)
		{
		case "2":
			c.add(new CheckBox("S1 intersect S2"));
			break;
		case "3":
			c.add(new CheckBox("S1 intersect S2"));
			c.add(new CheckBox("S1 intersect S3"));
			c.add(new CheckBox("S2 intersect S3"));
			break;
		
		default:
			break;

		}

		//selections = new ArrayList<Boolean>();
		for( CheckBox chk : c)
		{
			chk.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chk.setId("0");
			test.getChildren().add(chk);
			chk.setOnAction(e->{
				if(chk.isSelected())
					chk.setId("1");
				else
					chk.setId("0");
				
				codeTxt ="";
				for(int i=0; i< c.size(); i++)
				{
					codeTxt += c.get(i).getId();
				}
				
				if(!codes.contains(codeTxt))
				{
					//System.err.println("please select more intersection options to view a preview "+"code: "+codeTxt);
				}
			});
		}

		
		
		
		if(!root.getChildren().contains(test))
		root.add(test, 0, 1);
	}

	
	public void setButton(Button b)
	{
		this.b = b;
	}


}
