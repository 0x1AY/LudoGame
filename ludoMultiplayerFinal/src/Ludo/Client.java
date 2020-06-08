package Ludo;

import java.net.*;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Client {

    private final static int PORT = 4444;
    private static Socket sock = null;
    private static String error = "";
    private static int id = 0;

    private static String send(String request) {
        String response;
        try {
            System.out.println("[*] Connection to server");
            sock = new Socket("localhost", PORT);
            System.out.println("[*] Connected to server successfully!");

            DataInputStream input = new DataInputStream(sock.getInputStream());
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());

            System.out.println("[*] Sending data...");
            output.writeUTF(request);
            output.flush();
            System.out.println("[*] Done sending data...");


            System.out.println("[*] Reading data...");
            response = input.readUTF();
            System.out.println("[*] Done reading data...");
            System.out.println(response);

        } catch (IOException ex) {
            System.out.println("Server connection failed!");
            return "Error";
        }finally {
            try {
                System.out.println("[*] Closing socket...");
                if (sock != null)
                    sock.close();
            } catch (IOException ex) {  return "Error"; }
        }
        return response;
    }

    public int getId() {
        String reponse = send("GET ID");
        id = parseInt(reponse);
        if (reponse.equals("Error")) return -1;
        return parseInt(reponse);
    }

    public String getMove() {
        return send("GET MOVE,"+id);
    }

    public boolean setMove(int move) {
        String reponse = send("SET MOVE,"+ id + "," + move);
        if (reponse.equals("Error")) return false;
        return true;
    }

    public int getSize() {
        return parseInt(send("GET SIZE"));
    }

    public String getLastError() {
        return error;
    }
}