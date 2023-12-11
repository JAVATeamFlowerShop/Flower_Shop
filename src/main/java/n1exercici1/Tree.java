package n1exercici1;

import com.fasterxml.jackson.annotation.JsonValue;

public class Tree extends Product{

    private final float height;
    private final int id;
    private static final Product.Type type = Enum.valueOf(Product.Type.class, "TREE");

    public Tree(String name, float price, float height) {
        super(name, price);
        this.height = height;
        this.id = Product.idProd;
    }

    public float getHeight() {
        return height;
    }
    @Override
    public Product.Type getType(){
        return type;
    }
    @Override
    public int getId() {
        return id;
    }

    @Override
    @JsonValue
    public String toString(){
        return String.format("%2d %-15s %.2f%-5s %5.2f€", getId(), super.getName(), getHeight(), "m", super.getPrice());
    }

    @Override
    public boolean equals(Product p) {
        Tree t = (Tree) p;
        return this.name.equalsIgnoreCase(t.getName()) && this.price == t.getPrice() && this.height == t.getHeight();
    }


}
