package Lab1_Q2;

import java.io.*;
import java.net.Socket;

/*This class is Multiple_Client class.
 * The client class will be connected with server class.
 * The client's input value will be read by buffered reader and input stream.
 * If the client says "quit" then the connection between the server and the client will be ended.
 *
 * Name: Hyejin Kim
 * Student# 6823116
 * Date: 9/21/2021 */

public class Multiple_Client {
    public static void main(String[] args) throws IOException{
        //The server will use localhost port number 4000.
        int port_number = 4000;
        //The host name is "Multiple_Client.java" as the current java file name.
        String host_name = "127.0.0.1"; //The host name is hardcoded, but clients can change to the different host names.

        try(
                Socket Socket_C = new Socket(host_name, port_number); //The new client socket will be created by port number 4000 and host name 127.0.0.1.
                //The print writer enables to write the user input strings in the client window.
                PrintWriter print_writer = new PrintWriter(Socket_C.getOutputStream(),true);
                //The input stream enables to get the client's socket input string which will be used for buffered reader followed by below codes.
                InputStream input_stream = Socket_C.getInputStream();
                //The input stream reader enables to read the series of input strings.
                InputStreamReader input_stream_reader = new InputStreamReader(input_stream);
                //The buffered reader will read the user's input string which is used for input stream reader.
                BufferedReader buffered_reader = new BufferedReader(input_stream_reader);
                //Then, the input stream reader will read the user's string from the buffered reader.
                InputStreamReader input_reader = new InputStreamReader(System.in);
                //Finally, the client window can read the user's input string through the buffered reader.
                BufferedReader user_buffered_reader = new BufferedReader(input_reader);
        ){
            System.out.println("The client and server are now connected.");
            String user_string; //The variable user_string will be used for reading the series of client's string input.
            user_string = null; //Initialize the user string variable.
            //While loop: the user input is not null, then it will continue reading the string inputs.
            while((user_string = user_buffered_reader.readLine())!=null){
                //Print the user inputs in the client window.
                print_writer.println(user_string);
                //If the user input says "quit," then the client socket will be closed.
                if(buffered_reader.equals("quit")){
                    print_writer.println("bye!");
                    Socket_C.close();
                    break;}
            }

        }catch(IOException e){ //This exception handling will catch the incorrect host names.
            System.err.println(host_name + " has an exception. Host name should be changed.");
            System.exit(1); //If the exception handling occurs, then the window will be terminated.
        }
    }//Main method

}//Multiple_Client