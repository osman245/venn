package Venn_form;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Venn.Main;
import Venn.Venn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * this class will create the interface for the venn builder form
 */
public class Form 
{
	Button b;
	Venn vLoad = new Venn();
	//List<Boolean> selections;
	private GridPane root;
	private VBox test = new VBox(20);
	private  List<String> codes;
	private String codeTxt;
	private ImageView i;
	private Label preview;
	private Button btnDone;
	

	public Form()
	{
		startStage();

	}

	private void startStage() {


		Stage s = new Stage();
		s.setTitle("Venn Builder Form");
		s.setResizable(false);
		s.setAlwaysOnTop(true);

		s.setOnCloseRequest(e->{

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to discard this form?");
			alert.setTitle("Exit Form");
			alert.initModality(Modality.NONE);
			((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
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
		root.setPrefSize(400, 550);
		root.setPadding(new Insets(20,15,20,15));
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
		
		HBox topRow = new HBox(30);
		topRow.getChildren().addAll(lblRegions, cboRegions);
		topRow.setPrefWidth(root.getPrefWidth());
	

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
		codes.add("011");
		//filling the sets with the needed check boxes and labels
		cboRegions.setOnAction(e->{
			if (cboRegions.getValue() != null)
				fillCheckArray(cboRegions.getValue(), chkBoxes);
			preview.setVisible(true);
		});


		i = new ImageView(new Image("/venn_pics/venn111.png"));
		i.setVisible(false);

		HBox previewPic = new HBox(root.getPrefWidth());
		previewPic.getChildren().add(i);
		previewPic.setAlignment(Pos.CENTER);
		
		preview = new Label("Preview:");
		preview.setStyle("-fx-fill:#8f7a66; -fx-font-size: 18px; -fx-font-weight:bold;");
		preview.setVisible(false);
		
		//done button
		btnDone = new Button("Done");
		btnDone.setVisible(false);
		btnDone.setDisable(true);
		btnDone.setStyle("-fx-text-fill: white; -fx-font-family: Clear Sans; -fx-font-size: 18px; -fx-font-weight:bold; -fx-background-color: #8f7a66;");
		btnDone.setOnAction(e->{
			
			try {
				Main.v.init(codeTxt);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			s.close();
		});
		
		HBox bottom = new HBox();
		bottom.getChildren().add(btnDone);
		bottom.setAlignment(Pos.BOTTOM_RIGHT);
		
		root.addRow(0, topRow);
		root.addRow(1,setRelations);
		root.addRow(2,preview);
		root.addRow(3,previewPic);
		root.addRow(4, bottom);
		//root.add(lblShapes,0,1);
		//root.add(cboShapes, 1, 1);

		Scene scene = new Scene(root);
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


		this.i.setVisible(false);
		btnDone.setVisible(false);
		btnDone.setDisable(true);

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

				if(codes.contains(codeTxt) && codeTxt.length() == c.size())
				{
					this.i.setVisible(true);
					this.i.setImage(new Image("/venn_pics/venn"+codeTxt+".png"));
					btnDone.setVisible(true);
					btnDone.setDisable(false);
					System.out.println(codeTxt);
					vLoad.CodeText(codeTxt);
				}
				else
				{
					this.i.setVisible(false);
					btnDone.setVisible(false);
					btnDone.setDisable(true);
				}
			});
			
			chk.fire();
		}


		if(!root.getChildren().contains(test))
			root.add(test, 0, 1);
	}


	public void setButton(Button b)
	{
		this.b = b;
	}


}
