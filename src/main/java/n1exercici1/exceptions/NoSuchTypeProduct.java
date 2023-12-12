package n1exercici1.exceptions;

public class NoSuchTypeProduct extends Exception{
    public NoSuchTypeProduct() {
        super("Product type not sold in our store. Skipping product.");
    }
    public NoSuchTypeProduct(String message) {
        super(message);
    }
}
