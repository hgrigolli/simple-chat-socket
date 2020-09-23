package dev.grigolli.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {


    public static void main(String argv[]) throws Exception
    {


        try (
                ServerSocket welcomeSocket = new ServerSocket(41821);
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                        new BufferedReader(new
                                InputStreamReader(connectionSocket.getInputStream()));

                DataOutputStream outToClient =
                        new DataOutputStream(connectionSocket.getOutputStream());
        ){
            String clientSentence;

            clientSentence = inFromClient.readLine();
            while(!clientSentence.equals("QUIT")){
                System.out.println("CLIENTE diz: " + clientSentence);
                BufferedReader inFromServer =
                        new BufferedReader(new InputStreamReader(System.in));
                String resposta = inFromServer.readLine();
                outToClient.writeBytes("SERVIDOR diz: " + resposta + '\n');
                clientSentence = inFromClient.readLine();
            }
            outToClient.writeBytes("QUIT");

        }
    }
}
