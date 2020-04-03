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
			 */

			GridPane root = new GridPane();
			root.setPrefSize(400, 500);
			root.setPadding(new Insets(20,15,20,15));
			root.setAlignment(Pos.TOP_CENTER);
			root.setHgap(20);
			root.setVgap(20);
			root.setStyle("-fx-background-color:#faf8ef;");

			//controls
			Label lblTxtBox = new Label("Text box customizations");
			lblTxtBox.setPrefWidth(root.getPrefWidth());
			lblTxtBox.setAlignment(Pos.CENTER);
			lblTxtBox.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");

			Label lblTxtCol = new Label("Text Colour:");
			lblTxtCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblTxtCol.setPrefWidth(125);

			ColorPicker txtCol = new ColorPicker(Color.BLACK);
			txtCol.setPrefWidth(125);

			Label lblFont = new Label("Font:");
			lblFont.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			List<String> fonts = Font.getFamilies();
			ComboBox<String> cboFonts = new ComboBox<String>();
			cboFonts.getItems().addAll(fonts);
			cboFonts.setPrefWidth(125);
			cboFonts.setPromptText("Arial");


			Label lblTxtSize = new Label("Font Size:");
			lblTxtSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> sizeSpinner = new Spinner<Integer>(1,100,12);
			sizeSpinner.setEditable(false);
			sizeSpinner.setPrefWidth(125);

			CheckBox chkBold = new CheckBox("Bold");
			chkBold.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkBold.setPrefWidth(400/6);

			CheckBox chkItalic= new CheckBox("Italic");
			chkItalic.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkItalic.setPrefWidth(400/6);

			CheckBox chkUnderLine = new CheckBox("Underline");
			chkUnderLine.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkUnderLine.setPrefWidth(400/3);


			//adding the controls
			root.add(lblTxtBox, 0, 0, 3, 1);
			root.add(lblTxtCol, 0, 1, 1, 1);
			root.add(txtCol, 1, 1, 1, 1);
			root.add(lblFont, 0, 2, 1, 1);
			root.add(cboFonts, 1, 2, 1, 1);
			root.add(lblTxtSize, 0, 3, 1, 1);
			root.add(sizeSpinner, 1, 3, 1, 1);
			root.add(chkBold, 0, 4, 1, 1);
			root.add(chkItalic, 1, 4, 1, 1);
			root.add(chkUnderLine, 2, 4, 1, 1);

			Scene scene = new Scene(root);
			root.setStyle("-fx-background-color:#faf8ef;");

			s.setScene(scene);
			s.show();




		}
	}
}
