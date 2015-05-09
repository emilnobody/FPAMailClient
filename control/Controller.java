package control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import message.Message;



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
		System.out.println("Help is used");
	}

	public void setBasePath() {
		System.out.println("setBasePath...");
		
	}

	public void setFilter() {
		System.out.println("SetFilter...");
	}

}
