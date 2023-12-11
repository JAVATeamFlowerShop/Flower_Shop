package n1exercici1;

public abstract class Product {
    protected static int idProd;
    public enum Type {FLOWER,TREE,DECORATION};
    protected final String name;
    protected final float price;

    public Product(String name, float price)
    {
        idProd++;
        this.name = name;
        this.price = price;
    }

    public static int getIdProd() {
        return idProd;
    }
    public abstract int getId();
    public String getName() {
        return name;
    }
    public float getPrice() {
        return price;
    }
    public abstract Product.Type getType();
    public abstract boolean equals(Product p);
    public abstract String toPrettyString();
}
