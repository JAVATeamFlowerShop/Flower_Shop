package n1exercici1;

public abstract class Product {

    private final float price;

    private int quantity;

    private final String name;

    protected static int idProd;

    public Product(float price, int quantity, String name)
    {
        this.price = price;
        idProd++;
        this.quantity = quantity;
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int quantity)
    {
        this.quantity -= quantity;
    }

    public void increaseQuantity(int quantity)
    {
        this.quantity += quantity;
    }
}
