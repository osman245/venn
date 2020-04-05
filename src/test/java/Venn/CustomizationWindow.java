package Venn;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomizationWindow 
{
	private Node n;
	public CustomizationWindow(Node e)
	{
		StartStage();
		n = e;
	}

	private void StartStage()
	{
		Stage s = new Stage();
		s.setTitle("Customization window");	

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
					e.consume();
					s.close();
				}
				else
				{
					e.consume();
				}
			});;


		});

		//	if(n instanceof TextBox)
		{
			// things that need to be customized 
			/*
			 * TextBox customizations
			 * 1. text colour
			 * 2. text size
			 * 3. text font
			 * 4. bold, underline, italic
			 * 
			 * Background customization
			 * 5. background colour of textbox
			 * 6. border colour
			 * 
			 * Textfield customizations
			 * 7. text size
			 * 8. text font
			 * 9. border colour
			 */

			GridPane root = new GridPane();
			root.setPrefSize(400, 700);
			root.setPadding(new Insets(20,15,20,15));
			root.setAlignment(Pos.TOP_CENTER);
			root.setHgap(20);
			root.setVgap(20);
			root.setStyle("-fx-background-color:#faf8ef;");

			//controls for the text
			Label lblTxtBox = new Label("Text box customizations");
			lblTxtBox.setPrefWidth(root.getPrefWidth());
			lblTxtBox.setAlignment(Pos.CENTER);
			lblTxtBox.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");

			Label lblTxtCol = new Label("Text Colour:");
			lblTxtCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblTxtCol.setPrefWidth(150);

			ColorPicker txtCol = new ColorPicker(Color.BLACK);
			txtCol.setPrefWidth(150);

			Label lblFont = new Label("Font:");
			lblFont.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			List<String> fonts = Font.getFamilies();
			ComboBox<String> cboFonts = new ComboBox<String>();
			cboFonts.getItems().addAll(fonts);
			cboFonts.setPrefWidth(150);
			cboFonts.setPromptText("Arial");

			Label lblTxtSize = new Label("Font Size:");
			lblTxtSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> sizeSpinner = new Spinner<Integer>(1,100,12);
			sizeSpinner.setEditable(false);
			sizeSpinner.setPrefWidth(150);
			
			CheckBox chkBold = new CheckBox("Bold");
			chkBold.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkBold.setPrefWidth(400/3);

			CheckBox chkItalic= new CheckBox("Italic");
			chkItalic.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkItalic.setPrefWidth(400/3);

			CheckBox chkUnderLine = new CheckBox("Underline");
			chkUnderLine.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkUnderLine.setPrefWidth(400/2.65);
			
			HBox checkBoxes = new HBox();
			checkBoxes.setPrefWidth(root.getPrefWidth());
			//checkBoxes.setSpacing(40);
			//checkBoxes.setPadding(new Insets(0, 0, 0, 0));
			checkBoxes.setAlignment(Pos.CENTER);
			checkBoxes.getChildren().addAll(chkBold, chkItalic, chkUnderLine);		
		
			//background label controls	
			Label lblBgCol = new Label("Background Col.:");
			lblBgCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblBgCol.setPrefWidth(150);

			ColorPicker bgCol = new ColorPicker(Color.TRANSPARENT);
			bgCol.setPrefWidth(150);
			
			Label lblBrCol = new Label("Border Colour:");
			lblBrCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblBrCol.setPrefWidth(150);

			ColorPicker brCol = new ColorPicker(Color.BLACK);
			brCol.setPrefWidth(150);
			
			Label lblBrSize = new Label("Border Size:");
			lblBrSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> brSizeSpinner = new Spinner<Integer>(1,5,12);
			brSizeSpinner.setEditable(false);
			brSizeSpinner.setPrefWidth(150);
			
			//text field customizations
			Label lblField = new Label("Text Field customizations");
			lblField.setPrefWidth(root.getPrefWidth());
			lblField.setAlignment(Pos.CENTER);
			lblField.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");
			
			Label lblFieldCol = new Label("Border Colour:");
			lblFieldCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblFieldCol.setPrefWidth(150);

			ColorPicker Fieldcol = new ColorPicker(Color.BLACK);
			Fieldcol.setPrefWidth(150);
			
			Label lblFieldFont = new Label("Font:");
			lblFieldFont.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			ComboBox<String> cboFieldFonts = new ComboBox<String>();
			cboFieldFonts.getItems().addAll(fonts);
			cboFieldFonts.setPrefWidth(150);
			cboFieldFonts.setPromptText("Arial");
			
			Label lblFieldTxtSize = new Label("Font Size:");
			lblFieldTxtSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> fieldSizeSpinner = new Spinner<Integer>(1,20,12);
			fieldSizeSpinner.setEditable(false);
			fieldSizeSpinner.setPrefWidth(150);	
			
			//preview 
			Label lblPreview = new Label("Preview");
			lblPreview.setPrefWidth(root.getPrefWidth());
			lblPreview.setAlignment(Pos.CENTER);
			lblPreview.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");
			
			//adding the controls for 
			root.add(lblTxtBox, 0, 0, 3, 1);
			root.add(lblTxtCol, 0, 1, 1, 1);
			root.add(txtCol, 1, 1, 1, 1);
			root.add(lblFont, 0, 2, 1, 1);
			root.add(cboFonts, 1, 2, 1, 1);
			root.add(lblTxtSize, 0, 3, 1, 1);
			root.add(sizeSpinner, 1, 3, 1, 1);
			root.add(checkBoxes, 0, 4, 3, 1);
			root.add(lblBgCol, 0, 5, 1, 1);
			root.add(bgCol, 1, 5, 1, 1);
			root.add(lblBrCol, 0, 6, 1, 1);
			root.add(brCol, 1, 6, 1, 1);
			root.add(lblBrSize, 0, 7, 1, 1);
			root.add(brSizeSpinner, 1, 7, 1, 1);
			root.add(lblField, 0, 8, 3, 1);
			root.add(lblFieldCol, 0, 9, 1, 1);
			root.add(Fieldcol, 1, 9, 1, 1);
			root.add(lblFieldFont, 0, 10, 1, 1);
			root.add(cboFieldFonts, 1, 10, 1, 1);
			root.add(lblFieldTxtSize, 0, 11, 1, 1);
			root.add(fieldSizeSpinner, 1, 11, 1, 1);
			root.add(lblPreview, 0,12,3,1);

			Scene scene = new Scene(root);
			root.setStyle("-fx-background-color:#faf8ef;");

			s.setScene(scene);
			s.show();

		}
	}
}
