package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import message.Message;
/*
* Autor: Emil Steinkopf
*/

public class Controller implements Initializable {

    private ObservableList<Message> m;
    
	@FXML
	private MenuItem exit;

	@FXML
	private MenuItem about;

	@FXML
	private MenuItem setBasePath;

	@FXML
	private MenuItem setFilter;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		exit.setOnAction((e) -> exitFile());
		about.setOnAction((e) -> about());
		setBasePath.setOnAction((e) -> setBasePath());
		setFilter.setOnAction((e) -> setFilter());

		// TODO Auto-generated method stub

	}

	public void exitFile() {
		System.out.println("File closed");
		System.exit(0);

	}

	public void about() {
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("About");
            window.setMinWidth(250);
            window.setMinHeight(300);

            Label label = new Label();
            label.setText("Author: Emil Steinkopf");
            Button okButton = new Button("OK");
            okButton.setOnAction(e -> window.close());

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, okButton);
            layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
		System.out.println("Help is used");
	}

	public void setBasePath() {
		System.out.println("setBasePath...");
		
	}

	public void setFilter() {
		System.out.println("SetFilter...");
	}

}
