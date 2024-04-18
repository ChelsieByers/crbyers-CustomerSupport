package com.example.crbyerscustomersupport;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {

    //set up my methods to change a session ID
    @Override
    public void sessionIdChanged(HttpSessionEvent se, String oldId) {
        System.out.println(oldId + " changed to " + se.getSession().getId());
        SessionListUtil.updateSessionId(se.getSession(), oldId);
    }

    //set up my methods to create a session
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se.getSession().getId() + "created");
        SessionListUtil.addSession(se.getSession());
    }

    //set up my methods to destroy a session
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se.getSession().getId() + "destroyed");
        SessionListUtil.removeSession(se.getSession());
    }
}