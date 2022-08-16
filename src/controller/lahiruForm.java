package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import model.Client;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class lahiruForm {
    public AnchorPane anchorPane;
    public TextArea chatArea;
    public TextField chatTxtField;
    public ImageView sendBtn;
    public AnchorPane chatPage;
    public ImageView minimizedBtn;
    public Label clientName;
    final int PORT =5000;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    public void initialize() throws IOException {
        chatArea.setEditable(false);
        new Thread(()->{
            try {
                socket=new Socket("localhost",5000);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());

                chatArea.appendText("\n"+message);

                while (!message.equals("exit")){
                    message=dataInputStream.readUTF();
                    chatArea.appendText("\n"+message);
                }
            } catch (IOException e) {
                throw  new RuntimeException(e);
            }

        }).start();

    }
    public void sendOnAction(MouseEvent mouseEvent) throws IOException {
        dataOutputStream.writeUTF(chatTxtField.getText().trim());
        dataOutputStream.flush();
        chatArea.appendText("\n"+"You : "+chatTxtField.getText());
        chatTxtField.clear();
    }

    public void closeChatOnAction(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) chatPage.getScene().getWindow();
        window.close();
    }

    public void minimizeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage)((ImageView)mouseEvent.getSource()).getScene().getWindow();
        // is stage minimizable into task bar. (true | false)
        stage.setIconified(true);
    }

    public void setEmojiOnAction(MouseEvent mouseEvent) {
    }

    public void setImageOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image ");
        File selectedImage = fileChooser.showOpenDialog(stage);
    }
}
  /*  minimizedBtn.setOnAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            // is stage minimizable into task bar. (true | false)
            stage.setIconified(true);
        }
    });*/

