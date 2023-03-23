package test.task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * класс будет подключаться к серверу,
 * используя IP-адрес и номер порта, которые прослушивает сервер
 * */
public class CalculatorClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 1234;

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            System.out.println("Подключился к серверу");
            System.out.println("Веедите через прбел 3 значения\n" +
                    "Команда (add, subs, mult, divide) цифра цифра");
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Результат: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Неизвестный host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Не удалось получить значения для подключения к " + hostName);
            System.exit(1);
        }
    }
}
