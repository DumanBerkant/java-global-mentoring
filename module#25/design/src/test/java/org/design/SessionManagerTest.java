package org.design;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionManagerTest {

    @Test
    public void testCreateSession_WHEN_UserHasAccess_SHOULD_createSession() {
        User user = new User(1, "Berkant", "hasAccess");
        SessionManager sessionManager = new SessionManager();
        Session session = sessionManager.createSession(user, "valid");
        Assert.assertNotNull(session);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCreateSession_WHEN_UserHasNoAccess_SHOULD_throwException() {
        User user = new User(1, "Berkant", "");
        SessionManager sessionManager = new SessionManager();
        Session session = sessionManager.createSession(user, "valid");
        Assert.assertNotNull(session);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateSession_WHEN_UserNotDefined_SHOULD_throwException() {
        SessionManager sessionManager = new SessionManager();
        Session session = sessionManager.createSession(null, "valid");
        Assert.assertNotNull(session);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSession_WHEN_UserHasAccessAndPathNotValid_SHOULD_throwException() {
        User user = new User(1, "Berkant", "");
        SessionManager sessionManager = new SessionManager();
        Session session = sessionManager.createSession(user, "");
        Assert.assertNotNull(session);
    }
}