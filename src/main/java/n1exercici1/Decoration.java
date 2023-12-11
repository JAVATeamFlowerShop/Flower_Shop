package n1exercici1;

import com.fasterxml.jackson.annotation.JsonValue;


public class Decoration extends Product{
    private static final Product.Type type = Enum.valueOf(Product.Type.class, "DECORATION");
    private final int id;
    public enum Material {WOOD, PLASTIC}
    private Material material;

    public Decoration(String name, float price, Material material) {
        super(name, price);
        this.material = material;
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
    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Product p) {
        Decoration d = (Decoration) p;
        return this.name.equalsIgnoreCase(d.getName()) && this.price == d.getPrice() && this.material == d.getMaterial();
    }
    @Override
    public String toPrettyString(){
        return String.format("%2d %-15s %-9s %5.2f€", getId(), super.getName(), getMaterial(), super.getPrice());
    }
    @Override
    @JsonValue
    public String toString(){
        return String.format("%s,%d,%s,%.2f,%s", getType(), getId(), super.getName(), super.getPrice(), getMaterial());
    }
}
