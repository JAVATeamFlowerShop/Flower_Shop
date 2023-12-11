package n1exercici1;

import com.fasterxml.jackson.annotation.JsonValue;
public class Tree extends Product{
    private static final Product.Type type = Enum.valueOf(Product.Type.class, "TREE");
    private final int id;
    private final float height;

    public Tree(String name, float price, float height) {
        super(name, price);
        this.height = height;
        this.id = Product.idProd;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public Product.Type getType(){
        return type;
    }
    public float getHeight() {return height;}
    @Override
    public boolean equals(Product p) {
        Tree t = (Tree) p;
        return this.name.equalsIgnoreCase(t.getName()) && this.price == t.getPrice() && this.height == t.getHeight();
    }
    @Override
    public String toPrettyString(){
        return String.format("%2d %-15s %.2f%-5s %5.2f€", getId(), super.getName(), getHeight(), "m", super.getPrice());
    }
    @Override
    @JsonValue
    public String toString(){
        return String.format("%s,%d,%s,%.2f,%.2f",getType(), getId(), super.getName(), super.getPrice(), getHeight());
    }
}
