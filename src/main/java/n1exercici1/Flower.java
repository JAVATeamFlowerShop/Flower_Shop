package n1exercici1;

import com.fasterxml.jackson.annotation.JsonValue;

public class Flower extends Product{
    private static final Product.Type type = Enum.valueOf(Product.Type.class, "FLOWER");
    private final int id;
    private final String colour;

    public Flower(String name, float price, String colour) {
        super(name, price);
        this.colour = colour;
        this.id = Product.idProd;
    }
    public Flower(String[] splitKey){
        super();
        this.id = Integer.parseInt(splitKey[1]);
        this.name = splitKey[2].toLowerCase();
        this.price = Float.parseFloat(splitKey[3]);
        this.colour = splitKey[4].toLowerCase();
    }

    public int getId() {
        return id;
    }
    @Override
    public Product.Type getType(){
        return type;
    }
    public String getColour() {
        return colour;
    }
    @Override
    public boolean equals(Product p) {
        Flower f = (Flower) p;
        return this.name.equalsIgnoreCase(f.getName()) && this.price == f.getPrice() && this.colour.equalsIgnoreCase(f.getColour());
    }
    @Override
    public String toPrettyString(){
        return String.format("%2d %-15s %-9s %5.2f€", getId(), super.getName(), getColour(), super.getPrice());
    }
    @Override
    @JsonValue
    public String toString(){
        return String.format("%s,%d,%s,%.2f,%s", getType(), getId(), super.getName(), super.getPrice(), getColour());
    }
}
