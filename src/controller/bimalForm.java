package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;

public class bimalForm {
    public AnchorPane anchorPane;
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
                socket=new Socket("localhost",5002);
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
        emojiArea.setVisible(true);
    }

    public void setImageOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image ");
        File selectedImage = fileChooser.showOpenDialog(stage);
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



