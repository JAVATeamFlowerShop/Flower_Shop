package n1exercici1;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = MyProductSerializer.class)
@JsonDeserialize(keyUsing = MyProductDeserializer.class)
public abstract class Product {
    protected static int idProd;
    public enum Type {FLOWER,TREE,DECORATION};
    protected String name;
    protected float price;

    public Product(String name, float price)
    {
        idProd++;
        this.name = name;
        this.price = price;
    }
    public Product(){}
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
