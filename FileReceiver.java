import java.io.*;
import java.net.*;

public class FileReceiver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java FileReceiver <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port + "...\n");
          
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Connection accepted.\n");

                DataInputStream in = new DataInputStream(socket.getInputStream());

                String fileName = in.readUTF();

                System.out.println("Receiving file:\n" + fileName + "\n");

                String savedName = "received_" + fileName;

                try (FileOutputStream fileOut = new FileOutputStream(savedName)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {

                        fileOut.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Transfer complete.\n");
                System.out.println("Saved file:\n" + savedName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
