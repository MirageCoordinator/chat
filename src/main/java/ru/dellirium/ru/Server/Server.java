package ru.dellirium.ru.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static PrintWriter pw;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ServerSocket serv = null;
        Socket sock = null;
        try {
            serv = new ServerSocket(8189);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            Scanner sc = new Scanner(sock.getInputStream());
            pw = new PrintWriter(sock.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (in.hasNext()) {
                                String messageToClient = in.nextLine();
                                pw.println("Сервер: " + messageToClient);
                                pw.flush();
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            while (true) {String str = sc.nextLine();
                    if (str.equals("end")) break;
                    System.out.println(str);
                    pw.println("Эхо: " + str);
                    pw.flush();
            }
        } catch (
                IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

