package org.design;

public class SessionManager {
    private AccessChecker access = AccessChecker.getInstance();

    public SessionManager(){

    }

    public Session createSession(User user, String accessedPath) {
        if (access.mayAccess(user, accessedPath)) {
            return new Session(user);
        } else {
            throw new IllegalArgumentException("Illegal user");
        }
    }
}