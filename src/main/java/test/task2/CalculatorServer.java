package test.task2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 класс будет обрабатывать соединения сокетов от клиентов и обрабатывать их запросы
*/

public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port = 1234;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен на порту " + port);
        } catch (IOException e) {
            System.err.println("Не удалось прослушать порт: " + port);
            System.exit(-1);
        }

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new CalculatorThread(clientSocket).start();
            }
        } catch (IOException e) {

        }

        serverSocket.close();
    }
}

class CalculatorThread extends Thread {
    private Socket clientSocket;

    public CalculatorThread(Socket socket) {
        super("CalculatorThread");
        clientSocket = socket;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] input = inputLine.split(" ");
                if (input.length != 3) {
                    out.println("Ввели некорректное значение. Повторите ввод");
                    continue;
                }
                String operation = input[0];
                int arg1 = Integer.parseInt(input[1]);
                int arg2 = Integer.parseInt(input[2]);

                switch (operation) {
                    case "add":
                        out.println(ArithmeticOperations.add(arg1, arg2));
                        break;
                    case "subs":
                        out.println(ArithmeticOperations.subs(arg1, arg2));
                        break;
                    case "mult":
                        out.println(ArithmeticOperations.mult(arg1, arg2));
                        break;
                    case "divide":
                        out.println(ArithmeticOperations.divide(arg1, arg2));
                        break;
                    default:
                        out.println("Неверная операция");
                }
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
