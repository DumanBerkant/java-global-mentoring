package org.design;

public class AccessChecker {
    private static AccessChecker instance;
    private ServerConfig config;
    public AccessChecker() {
        this.config = ServerConfig.getInstance();
    }
    public static AccessChecker getInstance(){
        if (instance == null) {
            instance = new AccessChecker();
        }
        return instance;
    }


    public boolean mayAccess(User user, String path) {
        String userLevel = config.getAccessLevel(user, path);

        if(userLevel.isEmpty())
            return false;

        return true;
    }
}
