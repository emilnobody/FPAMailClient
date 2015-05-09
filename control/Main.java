package control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = FXMLLoader.load(getClass().getResource("../view/root.fxml"));
        primaryStage.setTitle("FPA Mail-Client");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("/images/new100.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
        
        root.setRight(FXMLLoader.load(getClass().getResource("../view/FPAMailClient.fxml")));
        root.setLeft(FXMLLoader.load(getClass().getResource("../view/treeview.fxml")));
    }

    
        
        
    


    public static void main(String[] args) {
        launch(args);
    }
}
