package n2exercici1;

public abstract class Product {
    protected static int idProd = 0;
    public enum Type {FLOWER,TREE,DECORATION};
    protected String name;
    protected float price;

    public Product(String name, float price)
    {
        idProd++;
        this.name = name;
        this.price = price;
    }
    public Product(int id){
        if(id > idProd){
            idProd = id;
        }
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
    public abstract Type getType();
    public static void decreaseId(){
        idProd--;
    }
    public abstract boolean equals(Product p);
    public abstract String toPrettyString();
}
