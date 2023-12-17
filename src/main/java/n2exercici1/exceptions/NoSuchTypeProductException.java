package n2exercici1.exceptions;

public class NoSuchTypeProductException extends Exception{
    public NoSuchTypeProductException() {
        super("Product type not sold in our store. Skipping product.");
    }
    public NoSuchTypeProductException(String message) {
        super(message);
    }
}
