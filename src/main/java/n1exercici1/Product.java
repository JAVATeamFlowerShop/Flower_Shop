package n1exercici1;

public abstract class Product {
    protected final float price;
    protected int quantity;
    protected final String name;
    protected static int idProd;
    protected enum Type {FLOWER,TREE,DECORATION};

    public Product(String name, float price, int quantity)
    {
        idProd++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public static int getIdProd() {
        return idProd;
    }
    public abstract int getId();

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

    public abstract String showStock();
    public abstract boolean equals(Product p);
    public abstract Product.Type getType();
}
