import java.io.*;
import java.net.*;

public class FileReceiver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Include port number: java FileReceiver <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port + "...\n");
          
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Connection accepted.\n");

                InputStream in = socket.getInputStream();

                String savedFileName = "received_test.txt";

                System.out.println("Receiving file:\n" + savedFileName + "\n");

                try (FileOutputStream fileOut = new FileOutputStream(savedFileName)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        fileOut.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Transfer complete.\n");
                System.out.println("Saved file:\n" + savedFileName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
