import java.io.*;
import java.net.*;

public class FileReceiver {
    public static void main(String[] args) {
        //validate command line arguments to make sure there's a listening port number
        if (args.length < 1) {
            System.out.println("Include port number: java FileReceiver <port>");
            return;
        }
        //parse the port number collected from the input
        int port = Integer.parseInt(args[0]);
        //set up TCP listener - Server Socket will bind to the cpecified port and release it when done
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port + "...\n");
            //accepting an incoming connection, waiting for the TCP 3-way handshake with the sender  
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Connection accepted.\n");
                //accessing and reading from raw socket input stream 
                InputStream in = socket.getInputStream();
                //hardcoded output destination to store raw file contents
                String savedFileName = "received_test.txt";
                System.out.println("Receiving file:\n" + savedFileName + "\n");
                //streaming byte payload into destination file
                try (FileOutputStream fileOut = new FileOutputStream(savedFileName)) {
                   //allocating a 4 KB memory buffer for efficient chuncked streaming
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    //pulling available bytes from the TCP receive buffer into memmory
                    //at -1 the sender closes socket - EOF- no bytes to read
                    while ((bytesRead = in.read(buffer)) != -1) {
                        fileOut.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Transfer complete.\n");
                System.out.println("Saved file:\n" + savedFileName);
            }
        //client socket closes here    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//serverSocket terminates here and releases the port
