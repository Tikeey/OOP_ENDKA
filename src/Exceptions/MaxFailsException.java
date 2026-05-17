package exceptions;

public class MaxFailsException extends Exception {
    public MaxFailsException(String message) {
        super(message);
    }
}