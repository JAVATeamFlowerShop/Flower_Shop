package n1exercici1;

public class Product {

    private float price;

    private int quantity;

    private String name;

    private static int id;

    public Product(float price, int quantity, String name)
    {
        this.price = price;
        id++;
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
