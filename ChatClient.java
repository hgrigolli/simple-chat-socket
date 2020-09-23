package dev.grigolli.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String resposta = "";

        try(
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("192.168.0.117", 41821);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());
        ){

            while(!resposta.contains("QUIT")){
                BufferedReader inFromServer =
                        new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                sentence = inFromUser.readLine();

                outToServer.writeBytes(sentence + '\n');

                resposta = inFromServer.readLine();

                System.out.println(resposta);

            }

        }


    }
}
