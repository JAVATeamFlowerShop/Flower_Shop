package n2exercici1;

public class Flower extends Product{
    private static final Type type = Enum.valueOf(Type.class, "FLOWER");
    private final int id;
    private final String colour;

    public Flower(String name, float price, String colour) {
        super(name, price);
        this.colour = colour;
        this.id = idProd;
    }
    public Flower(int id, String name, float price, String colour){
        super(id);
        this.id = id;
        this.name = name;
        this.price = price;
        this.colour = colour;
    }

    public int getId() {
        return id;
    }
    @Override
    public Type getType(){
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
    public String toString(){
        return String.format("%s,%d,%s,%.2f,%s", getType(), getId(), super.getName(), super.getPrice(), getColour());
    }
}
