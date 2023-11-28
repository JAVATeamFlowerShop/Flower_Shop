package n1exercici1;

public abstract class Product {

    private final float price;
    protected static int idProd;
    protected int quantity;

    public Product(float price) {
        idProd++;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
