package n2exercici1;

public class Decoration extends Product{
    private static final Type type = Enum.valueOf(Type.class, "DECORATION");
    private final int id;
    public enum Material {WOOD, PLASTIC}
    private Material material;

    public Decoration(String name, float price, Material material) {
        super(name, price);
        this.material = material;
        this.id = idProd;
    }
    public Decoration(int id, String name, float price, Material material){
        super(id);
        this.id = id;
        this.name = name;
        this.price = price;
        this.material = material;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public Type getType(){
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
        return String.format("%2d %-15s %-9s %5.2f€", getId(), super.getName(), getMaterial().toString().toLowerCase(), super.getPrice());
    }
    @Override
    public String toString(){
        return String.format("%s,%d,%s,%.2f,%s", getType(), getId(), super.getName(), super.getPrice(), getMaterial());
    }
}
