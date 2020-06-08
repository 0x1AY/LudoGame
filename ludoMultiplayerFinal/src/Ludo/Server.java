package Ludo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.net.Socket;
import java.util.Vector;

import static java.lang.Integer.parseInt;

public class Server {

    public final static int PORT = 4444;
    private static ServerSocket server = null;

    private static Vector<Vector<String>> moveQueue = new Vector<>();

    public static void main(String[] args) {
        int port = PORT;
        try {
            server = new ServerSocket(port);
            System.out.println("Server started on port ::: " + port);

        } catch (IOException ex) {
            System.out.println("Couldn't start the server");
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (true) {
            try {
                java.net.Socket connection = server.accept();
                connection.setSoTimeout(60000);
                System.out.println("New Client ::: " + connection);
                Thread task = new ServerTask(connection);
                task.start();
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    private static class ServerTask extends Thread {

        private static int action = 0;
        private static int move = 0;
        private static int id = 0;
        private java.net.Socket connection;

        ServerTask(Socket connection) {
            this.connection = connection;
        }


        private static String clientGetId() {
            moveQueue.add(new Vector<String>());
            id = moveQueue.size() - 1;
            return String.valueOf(id);
        }

        private static String getMove() {
            try {
                if (moveQueue.elementAt(id).size() == 0) return "No move";
                String res = moveQueue.elementAt(id).elementAt(0);
                moveQueue.elementAt(id).remove(0);
                return res;
            } catch (Exception ex) {
                ex.printStackTrace();
                return "Error";
            }
        }

        private static String setMove() {
            for (int i = 0; i < moveQueue.size(); i++)
                if (id != i) {
                    moveQueue.elementAt(i).add(id + "," + move);
                }
            return "done";
        }

        private static String getSize() {
            return moveQueue.size() + "";
        }


        /**
         * Parse String the extract the clients instructions to the server
         *
         * @param request The client request string
         * @return Boolean value
         */
        Boolean parse(String request) {
            String[] tokens = request.split(",");
            if (tokens.length == 1 && tokens[0].equals("GET ID")) {
                action = 1;
                return true;
            } else if (tokens.length == 2 && tokens[0].equals("GET MOVE")) {
                action = 2;
                id = parseInt(tokens[1]);
                return true;
            } else if (tokens.length == 3 && tokens[0].equals("SET MOVE")) {
                action = 3;
                id = parseInt(tokens[1]);
                move = parseInt(tokens[2]);
                return true;
            } else if (tokens.length == 1 && tokens[0].equals("GET SIZE")) {
                action = 4;
                return true;
            }
            return false;
        }

        @Override
        public void run() {
            try {
                DataInputStream input = new DataInputStream(connection.getInputStream());
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());

                System.out.println("[*] Reading data...");
                String request = input.readUTF();
                System.out.println("[*] Done reading data...");
                System.out.println(request);

                String response;
                if (parse(request)) {
                    if (action == 1) response = clientGetId();
                    else if (action == 2) response = getMove();
                    else if (action == 3) response = setMove();
                    else if (action == 4) response = getSize();
                    else response = "Error";
                } else response = "Error";

                System.out.println("[*] Sending data...");
                output.writeUTF(response);
                output.flush();
                System.out.println("[*] Done sending data...");

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}