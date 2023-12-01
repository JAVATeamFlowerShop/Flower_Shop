package n1exercici1;

public abstract class Product {
    protected final float price;
    protected final String name;
    protected static int idProd;
    protected enum Type {FLOWER,TREE,DECORATION};

    public Product(String name, float price)
    {
        idProd++;
        this.name = name;
        this.price = price;
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

    public abstract String showStock();
    public abstract boolean equals(Product p);
    public abstract Product.Type getType();
}
