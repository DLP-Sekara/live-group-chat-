package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class loginForm {
    public AnchorPane loginPage;

    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/chatPage.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) loginPage.getScene().getWindow();
        window.setTitle("Live Chat");
        window.setMaximized(true);
        window.setScene(new Scene(load));
    }
}
