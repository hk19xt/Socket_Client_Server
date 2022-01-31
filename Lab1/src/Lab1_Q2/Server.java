package Lab1_Q2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*This class is Server class.
* The server class will be connected with multiple clients through implementing the multi-threads.
* Therefore, the Server class will conduct the multi-thread to receive the number of clients.
*
* Name: Hyejin Kim
* Student# 6823116
* Date: 9/21/2021 */

public class Server {
    public static void main(String[] args) throws IOException {

        int port_number = 4000; //The port number 4000 will be used.
        ServerSocket Socket_S = new ServerSocket(port_number); //The new server socket will be created.

        System.out.println("The server is now waiting for the clients.");

        while (true) { //While loop for the acceptance of multiple clients.
            Socket clientSocket = Socket_S.accept(); //The acceptance to client sockets by a server socket.
            Thread t = new Thread() { //This thread will be used to conduct the connection with multiple clients.
                public void run() {
                    try (
                            //The print writer will enable the server window to write the series of the client's inputs.
                            PrintWriter print_writer = new PrintWriter(clientSocket.getOutputStream(), true);
                            //The input stream will get the series of the client's input strings.
                            InputStream input_stream = clientSocket.getInputStream();
                            //The input stream reader enables to read the client's input which is received by input stream as above.
                            InputStreamReader input_stream_reader = new InputStreamReader(input_stream);
                            //Finally, the buffered reader can read the client's input which was read by input stream reader.
                            BufferedReader buffered_reader = new BufferedReader(input_stream_reader);

                    ) {
                        System.out.println("The client and server are now connected.");
                        String client_mention; //This string variable will be used for reading the series of client's inputs.
                        client_mention = null; //Initialize the client_mention string variable.
                        //While loop: if the client input is not null, then the while loop will continue reading the series of the clients' inputs.
                        while ((client_mention = buffered_reader.readLine())!=null) {
                            //The client input will be printed with upper case strings as well as the port number will be printed.
                            System.out.println("Client said: " + client_mention.toUpperCase() + ", port number: " + clientSocket.getPort());
                            //If the client says "quit," then the connection between the client and the server will be ended.
                            if (client_mention.equals("quit")) {
                                clientSocket.close();
                                System.out.println("The connection was terminated. Bye!");
                                break;
                            }
                        }
                    } catch (IOException ioexception) { //The exception handling will catch any incorrect input values.
                        System.out.println("There is an exception in client.");
                    }
                }
            };
            t.start(); //The multi-thread running will be started.
        }
    }//Main method
}//Server method