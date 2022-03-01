package hcl.com.util;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("TThe item requested cannot be found!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
