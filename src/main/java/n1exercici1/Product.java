package n1exercici1;

public abstract class Product {
    private final float price;
    private int quantity;
    private final String name;
    protected static int idProd;

    public Product(float price, String name)
    {
        this.price = price;
        idProd++;
        this.name = name;
        this.quantity = 1;
    }

    public float getPrice() {
        return price;
    }

    public static int getId() {
        return idProd;
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
