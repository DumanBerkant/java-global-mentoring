package org.design;

public class Main {
    public static void main(String[] args) {

        User user = new User(1, "name", "lastname");
        SessionManager sessionManager = new SessionManager();

         sessionManager.createSession(user, "accesspath");
    }
}