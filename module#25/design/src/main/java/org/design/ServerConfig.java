package org.design;

public class ServerConfig {
    private static ServerConfig instance;
    private static String configFilePath = "...";

    public static ServerConfig getInstance() {
        if (instance == null) {
            return new ServerConfig();
        }
        return instance;

    }

    public ServerConfig() {
        validatePath(configFilePath);
    }

    private static void validatePath(String path) {
        if(path.isEmpty())
            throw new IllegalArgumentException("File not found");
    }

    public String getAccessLevel(User u, String path) {
        validatePath(path);
        return u.getLevel();
    }

}