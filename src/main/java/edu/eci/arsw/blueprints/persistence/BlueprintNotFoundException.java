package edu.eci.arsw.blueprints.persistence;

public class BlueprintNotFoundException extends Exception {

    public BlueprintNotFoundException(String message) {
        super(message);
    }

    public BlueprintNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlueprintNotFoundException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
