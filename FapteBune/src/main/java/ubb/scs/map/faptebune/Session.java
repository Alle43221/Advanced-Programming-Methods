package ubb.scs.map.faptebune;

public class Session {
    private Long sessionID;
    private static Session instance;

    // Private constructor to prevent direct instantiation
    private Session(Long sessionID) {
        this.sessionID = sessionID;
    }

    // Public method to provide access to the singleton instance
    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session(0L);
        }
        return instance;
    }

    public Long getSessionID() {
        return sessionID;
    }

    public void setSessionID(Long sessionID) {
        this.sessionID = sessionID;
    }
}

