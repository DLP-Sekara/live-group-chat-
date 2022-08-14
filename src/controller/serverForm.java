package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serverForm {
    public TextArea serverChatArea;
    public TextField serverChatTxtField;
    public ImageView serverSendBtn;
    Socket accept =null;
    public void initialize(){
        new Thread(()->{
            try {
                ServerSocket serverSocket=new ServerSocket(5000);
                System.out.println("Server Started!");

                accept = serverSocket.accept();
                System.out.println("Client Connected!");

                InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                String record=bufferedReader.readLine();
                System.out.println(record);
                serverChatArea.appendText("\n"+record);

                while (!record.equals("exit")){
                    record=bufferedReader.readLine();
                    serverChatArea.appendText("\n"+record);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
    public void serverSendOnAction(MouseEvent mouseEvent) throws IOException {
        PrintWriter printWriter=new PrintWriter(accept.getOutputStream());
        printWriter.println(serverChatTxtField.getText());
        printWriter.flush();
        serverChatArea.appendText("\n"+serverChatTxtField.getText());
    }
}
