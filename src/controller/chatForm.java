package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class chatForm {
    public TextArea chatArea;
    public TextField chatTxtField;
    public ImageView sendBtn;
    Socket socket=null;
    public void initialize() throws IOException {
        new Thread(()->{
            try {
                socket=new Socket("localhost",5000);
                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String record=bufferedReader.readLine();
                System.out.println(record);
                chatArea.appendText("\n"+record);

                while (!record.equals("exit")){
                    record=bufferedReader.readLine();
                    chatArea.appendText("\n"+record);
                }
            } catch (IOException e) {
                throw  new RuntimeException(e);
            }

        }).start();

    }
    public void sendOnAction(MouseEvent mouseEvent) throws IOException {
        PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
        printWriter.println(chatTxtField.getText());
        printWriter.flush();
        chatArea.appendText("\n"+chatTxtField.getText());
    }
}
