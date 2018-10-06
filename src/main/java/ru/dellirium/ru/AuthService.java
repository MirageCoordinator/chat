package ru.dellirium.ru;


import java.util.ArrayList;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}
