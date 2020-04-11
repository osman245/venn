package Venn;

import java.awt.Event;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
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
import javafx.scene.control.Button;
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
	private String bgStyle, fieldStyle, txtStyle;
	private String txtColour, txtFontSize, txtFont, txtBold, txtUnderLine, txtItalic, txtBGCol, txtBrCol, fieldFont, fieldTxtCol, fieldBrCol, fieldFontSize, txtBrWidth;
	private Stage s;
	private TextBox tbPreview;

	public CustomizationWindow(Node e)
	{
		StartStage();
		n = e;
	}

	private void StartStage()
	{
		s = new Stage();
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

			//default style will update for each set on action
			txtColour = "black";
			txtFontSize = "12";
			txtFont = "Arial";
			txtBold = "normal";
			txtUnderLine = "false";
			txtItalic = "normal";
			txtBGCol= "transparent";
			txtBrCol= "black";
			fieldFont = "Arial";
			fieldTxtCol = "black";
			fieldBrCol = "black";
			fieldFontSize = "10";

			bgStyle = "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5; -fx-background-color: "+txtBGCol+"; -fx-border-color: "+txtBrCol+";";
			txtStyle = "-fx-font: "+txtFont+"; -fx-underline: "+txtUnderLine+";-fx-font-size: "+txtFontSize+"; -fx-font-weight: "+txtBold+"; -fx-fill: "+txtColour+"; -fx-font-style: "+txtItalic+";";
			fieldStyle = "-fx-font: "+fieldFont+"; -fx-text-fill: "+fieldTxtCol+"; -fx-border-color: "+fieldBrCol+"; -fx-font-size: "+fieldFontSize+";";

			GridPane root = new GridPane();
			root.setPrefSize(400, 750);
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
			txtCol.setOnAction(e->{txtColour = "#"+ txtCol.getValue().toString().substring(2); updateStyle(); });
			

			Label lblFont = new Label("Font:");
			lblFont.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			List<String> fonts = Font.getFamilies();
			ComboBox<String> cboFonts = new ComboBox<String>();
			cboFonts.getItems().addAll(fonts);
			cboFonts.setPrefWidth(150);
			cboFonts.setPromptText("Arial");
			cboFonts.setOnAction(e->{txtFont = cboFonts.getValue(); updateStyle(); });

			Label lblTxtSize = new Label("Font Size:");
			lblTxtSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> sizeSpinner = new Spinner<Integer>(1,25,12);
			sizeSpinner.setEditable(false);
			sizeSpinner.setPrefWidth(150);
			sizeSpinner.setOnMouseClicked(e-> {txtFontSize = ""+sizeSpinner.getValue(); updateStyle();});

			CheckBox chkBold = new CheckBox("Bold");
			chkBold.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkBold.setPrefWidth(400/3);
			chkBold.setOnAction(e->{txtBold = !chkBold.isSelected()?"normal":"bold"; updateStyle(); });

			CheckBox chkItalic= new CheckBox("Italic");
			chkItalic.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkItalic.setPrefWidth(400/3);
			chkItalic.setOnAction(e->{txtItalic= !chkItalic.isSelected()?"normal":"italic"; updateStyle(); });

			CheckBox chkUnderLine = new CheckBox("Underline");
			chkUnderLine.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			chkUnderLine.setPrefWidth(400/2.65);
			chkUnderLine.setOnAction(e->{txtUnderLine = !chkUnderLine.isSelected()?"false":"true"; updateStyle(); });

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
			bgCol.setOnAction(e->{txtBGCol = "#"+bgCol.getValue().toString().substring(2);  updateStyle();});

			Label lblBrCol = new Label("Border Colour:");
			lblBrCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblBrCol.setPrefWidth(150);

			ColorPicker brCol = new ColorPicker(Color.BLACK);
			brCol.setPrefWidth(150);
			brCol.setOnAction(e->{txtBrCol = "#"+brCol.getValue().toString().substring(2); updateStyle(); });

			Label lblBrSize = new Label("Border Thickness:");
			lblBrSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> brSizeSpinner = new Spinner<Integer>(1,20,1);
			brSizeSpinner.setEditable(false);
			brSizeSpinner.setPrefWidth(150);
			brSizeSpinner.setOnMouseClicked(e->{txtBrWidth = ""+brSizeSpinner.getValue(); updateStyle();  });

			//text field customizations
			Label lblField = new Label("Text Field customizations");
			lblField.setPrefWidth(root.getPrefWidth());
			lblField.setAlignment(Pos.CENTER);
			lblField.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");

			Label lblFieldCol = new Label("Text Colour:");
			lblFieldCol.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");
			lblFieldCol.setPrefWidth(150);

			ColorPicker Fieldcol = new ColorPicker(Color.BLACK);
			Fieldcol.setPrefWidth(150);
			Fieldcol.setOnAction(e->{fieldTxtCol = "#"+Fieldcol.getValue().toString().substring(2); updateStyle();});

			Label lblFieldFont = new Label("Font:");
			lblFieldFont.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			ComboBox<String> cboFieldFonts = new ComboBox<String>();
			cboFieldFonts.getItems().addAll(fonts);
			cboFieldFonts.setPrefWidth(150);
			cboFieldFonts.setPromptText("Arial");
			cboFieldFonts.setOnAction(e->{fieldFont = cboFieldFonts.getValue(); updateStyle();});

			Label lblFieldTxtSize = new Label("Font Size:");
			lblFieldTxtSize.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px;");

			Spinner<Integer> fieldSizeSpinner = new Spinner<Integer>(1,20,12);
			fieldSizeSpinner.setEditable(false);
			fieldSizeSpinner.setPrefWidth(150);
			fieldSizeSpinner.setOnMouseClicked(e-> {fieldFontSize = ""+fieldSizeSpinner.getValue(); updateStyle();});

			//preview 
			Label lblPreview = new Label("Preview");
			lblPreview.setPrefWidth(root.getPrefWidth());
			lblPreview.setAlignment(Pos.CENTER);
			lblPreview.setStyle("-fx-fill:#8f7a66; -fx-font-size: 15px; -fx-font-weight:bold;");
			
			tbPreview = new TextBox();
			tbPreview.setTitleText("abdcefghijklmnopqrstuvwxyz");
			tbPreview.setPreview(true);
			updateStyle();
			
			Button btnDone = new Button("Done");
			btnDone.setStyle("-fx-background-color: #8f7a66; -fx-font-size:15px;-fx-font-color:white; -fx-font-weight:bold;");
			btnDone.setAlignment(Pos.CENTER);
			btnDone.setPrefWidth(125);
			btnDone.setOnAction(e->updateNodeStyle());
			
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
			root.add(tbPreview, 0,13,3,1);
			root.add(btnDone, 1, 14,2,1);

			Scene scene = new Scene(root);
			root.setStyle("-fx-background-color:#faf8ef;");

			s.setScene(scene);
			s.show();

		}


	}
	private void updateNodeStyle() {
		updateStyle();
		
		TextBox t = (TextBox)n;
		t.getTextObj().setStyle(txtStyle);
		t.getPane().setStyle(bgStyle);
		t.getTextField().setStyle(fieldStyle);
		
		s.close();
		
	}

	private void updateStyle()
	{
		bgStyle = "-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5; -fx-background-color: "+txtBGCol+"; -fx-border-color: "+txtBrCol+"; -fx-border-width:"+txtBrWidth+";";
		txtStyle = "-fx-font-family: "+txtFont+"; -fx-underline: "+txtUnderLine+";-fx-font-size: "+txtFontSize+"; -fx-font-weight: "+txtBold+"; -fx-fill: "+txtColour+"; -fx-font-style: "+txtItalic+";";
		fieldStyle ="-fx-border-radius: 5 5 5 5; -fx-background-radius: 5 5 5 5; -fx-font-family: "+fieldFont+"; -fx-text-fill: "+fieldTxtCol+"; -fx-border-color: "+fieldBrCol+"; -fx-font-size: "+fieldFontSize+";";
		
		tbPreview.getTextObj().setStyle(txtStyle);
		tbPreview.getPane().setStyle(bgStyle);
		tbPreview.getTextField().setStyle(fieldStyle);
	}
}
