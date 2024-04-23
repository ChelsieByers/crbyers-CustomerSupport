package com.example.crbyerscustomersupport.site;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionListUtil {
    //Mapping my sessions
    private static final Map<String, HttpSession> SESSIONS = new HashMap<>();

    //method to add a session to the list
    public static void addSession(HttpSession session) {
        SESSIONS.put(session.getId(), session);
    }
    public static void updateSessionId (HttpSession session, String oldSessionId) {
        synchronized (SESSIONS){
            SESSIONS.remove(oldSessionId);
            addSession(session);
        }
    }

    //method to remove the session from teh list
    public static void removeSession(HttpSession session) {
        SESSIONS.remove(session.getId());
    }

    //adding my session to the list and returning the list
    public static List<HttpSession> getAllSessions(){
        return new ArrayList<>(SESSIONS.values());
    }

    //returning the number of sessions
    public static int getNumberOfSessions() {
        return SESSIONS.size();
    }
}