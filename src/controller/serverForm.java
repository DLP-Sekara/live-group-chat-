package controller;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class serverForm {
    public TextArea serverChatArea;
    public TextField serverChatTxtField;
    public ImageView serverSendBtn;

    static DataOutputStream dataOutputStreamOne;
    static DataInputStream dataInputStreamOne;

    static DataOutputStream dataOutputStreamTwo;
    static DataInputStream dataInputStreamTwo;

    static DataOutputStream dataOutputStreamThree;
    static DataInputStream dataInputStreamThree;

    Socket accept =null;

    public static void main(String[] args) {
        System.out.println("======server started======");

        //======client one========
        new Thread(()->{
            String message = "";
            try {
                ServerSocket serverSocket = new ServerSocket(5000);
                Socket socket = serverSocket.accept();
                dataOutputStreamOne = new DataOutputStream(socket.getOutputStream());
                dataInputStreamOne = new DataInputStream(socket.getInputStream());
                while (!message.equals("exit")) {

                    message = dataInputStreamOne.readUTF();
                    System.out.println("Client 1 : " + message);

                    dataOutputStreamTwo.writeUTF("Lahiru : " + message);
                    dataOutputStreamThree.writeUTF("Lahiru : " + message);
                }
                if (message.equals("exit")) {
                    System.out.println("Lahiru left the chat");
                    dataOutputStreamTwo.writeUTF("Lahiru left the chat");
                    dataOutputStreamThree.writeUTF("Lahiru left the chat");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //======client two========
        new Thread(()->{
            String message = "";
            try {
                ServerSocket serverSocket = new ServerSocket(5001);
                Socket socket = serverSocket.accept();
                dataOutputStreamTwo = new DataOutputStream(socket.getOutputStream());
                dataInputStreamTwo = new DataInputStream(socket.getInputStream());
                while (!message.equals("exit")) {

                    message = dataInputStreamTwo.readUTF();
                    System.out.println("Client 2 : " + message);

                    dataOutputStreamOne.writeUTF("Pasan : " + message);
                    dataOutputStreamThree.writeUTF("Pasan : " + message);
                }
                if (message.equals("exit")) {
                    System.out.println("Pasan left the chat");
                    dataOutputStreamOne.writeUTF("Pasan left the chat");
                    dataOutputStreamThree.writeUTF("Pasan left the chat");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //======client three========
        new Thread(()->{
            String message = "";
            try {
                ServerSocket serverSocket = new ServerSocket(5002);
                Socket socket = serverSocket.accept();
                dataOutputStreamThree = new DataOutputStream(socket.getOutputStream());
                dataInputStreamThree = new DataInputStream(socket.getInputStream());
                while (!message.equals("exit")) {

                    message = dataInputStreamThree.readUTF();
                    System.out.println("Client 3 : " + message);

                    dataOutputStreamOne.writeUTF("Bimal : " + message);
                    dataOutputStreamTwo.writeUTF("Bimal : " + message);
                }
                if (message.equals("exit")) {
                    System.out.println("Bimal left the chat");
                    dataOutputStreamOne.writeUTF("Bimal left the chat");
                    dataOutputStreamTwo.writeUTF("Bimal left the chat");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    /*public void initialize(){
        serverChatArea.setEditable(false);
        new Thread(()->{
            try {
              *//*  ServerSocket serverSocket=new ServerSocket(5000);
                while (!serverSocket.isClosed()){
                    Socket acceptSocket = serverSocket.accept();
                    System.out.println("A new Client has Connected !");

                    ClientHandler clientHandler = new ClientHandler(acceptSocket);

                    Thread thread = new Thread(clientHandler);
                    thread.start();
                }*//*
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
        serverChatTxtField.clear();
    }*/
}
