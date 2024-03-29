package _OOP_develop_gradle.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageChangeController implements StageChangeControllerInterface {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @Override
    public void changeScene(ActionEvent e, String nameScene) throws IOException {
        root = FXMLLoader.load(getClass().getResource(nameScene));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void mainMenu(ActionEvent e) throws IOException {
        changeScene(e, "/MainMenuView.fxml");
    }
}
