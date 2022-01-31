package Lab1_Q1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*This class is Server class.
 *The server class will be connected using telnet to send messages to the server.
 *
 * Name: Hyejin Kim
 * Student# 6823116
 * Date: 9/21/2021 */

public class Server {
    public static void main(String[] args) throws IOException{

        System.out.println("Waiting for telnet client connection. Please open another window to connect client.");
        boolean bool = true;

        try(

                ServerSocket Socket_S = new ServerSocket(4000); //This line is for creating the server socket (Socket_S) using localhost 4000.
                //The server socket will accept the client socket (Socket_C).
                Socket Socket_C = Socket_S.accept();
                //This printwriter will enable the client to write in the terminal.
                PrintWriter print_writer = new PrintWriter(Socket_C.getOutputStream(),bool);
                //This input stream receives the string or any inputs from the client terminal.
                InputStream input_stream = Socket_C.getInputStream();
                //This input stream reader can read the received strings or other messages from the client.
                InputStreamReader input_stream_reader = new InputStreamReader(input_stream);
                //This buffered reader reads strings whatever the client says in the terminal.
                BufferedReader buffered_reader = new BufferedReader(input_stream_reader);
        ){
            String client_message;
            client_message = null;
            System.out.println("The client and server are now connected.");

            //While loop for receiving the messages from the client.
            while(true){
                client_message = buffered_reader.readLine();
                System.out.println("Message from a client: " + client_message);
                if(client_message==null){ //If the client does not say anything in the window, then the server will be ended.
                    break;
                }
            }
        }catch(IOException ioexeption){ //The exception handling will catch the incorrect server values or information.
            System.out.println(ioexeption.getMessage() + " there is an exception in the server.");
        }

    }
}
