package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class loginForm {

    public AnchorPane loginPage;
    public  TextField usernameTxtField;


    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        System.out.println(usernameTxtField.getText());
       //String username=usernameTxtField.getText();
        if(usernameTxtField.getText().equals("lahiru")) {
            URL resource = getClass().getResource("../view/lahiru.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) loginPage.getScene().getWindow();
            window.setTitle("Live Chat");
            window.setScene(new Scene(load));
        }else if(usernameTxtField.getText().equals("pasan")){
            URL resource = getClass().getResource("../view/pasan.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) loginPage.getScene().getWindow();
            window.setTitle("Live Chat");
            window.setScene(new Scene(load));
        }else if(usernameTxtField.getText().equals("bimal")){
            URL resource = getClass().getResource("../view/bimal.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) loginPage.getScene().getWindow();
            window.setTitle("Live Chat");
            window.setScene(new Scene(load));
        }else{
            System.out.println("no such client");
        }
        /*URL resource = getClass().getResource("../view/lahiru.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) loginPage.getScene().getWindow();
        window.setTitle("Live Chat");
        window.setMaximized(true);
        window.setScene(new Scene(load));*/

    }
}
