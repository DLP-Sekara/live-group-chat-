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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class lahiruForm {
    public TextArea chatArea;
    public TextField chatTxtField;
    public ImageView sendBtn;
    public AnchorPane chatPage;
    public ImageView minimizedBtn;
    public Label clientName;
    final int PORT =5000;
    public AnchorPane emojiArea;
    public ImageView style1;
    public ImageView style2;
    public ImageView style3;
    public ImageView style4;
    public ImageView style5;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";
    public void initialize() throws IOException {
        chatArea.setEditable(false);
        emojiArea.setVisible(false);
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
                throw   new RuntimeException(e);
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
        emojiArea.setVisible(true);
    }

    public void setImageOnAction(MouseEvent mouseEvent) throws IOException, InterruptedException {
            try {
        Stage stage = (Stage) chatPage.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image ");
        File selectedImage = fileChooser.showOpenDialog(stage);
        System.out.println(selectedImage);
        socket.close();
            }catch (Exception e){
                throw  new RuntimeException(e);
            }

    }

    public void style1OnAction(MouseEvent mouseEvent) {
        chatTxtField.appendText("\uD83D\uDE0D");
    }

    public void style2OnAction(MouseEvent mouseEvent) {
        chatTxtField.appendText("\uD83D\uDE0E");
    }

    public void style3OnAction(MouseEvent mouseEvent) {
        chatTxtField.appendText("\uD83D\uDE09");
    }

    public void style4OnAction(MouseEvent mouseEvent) {
        chatTxtField.appendText("\uD83D\uDE2A");
    }

    public void style5OnAction(MouseEvent mouseEvent) {
        chatTxtField.appendText("\uD83D\uDE07");
    }

    public void collapseOnAction(MouseEvent mouseEvent) {
        emojiArea.setVisible(false);
    }
}
  /*  minimizedBtn.setOnAction(new EventHandler<ActionEvent>() {

        public void handle(ActionEvent event) {
            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            // is stage minimizable into task bar. (true | false)
            stage.setIconified(true);
        }
    });*/

