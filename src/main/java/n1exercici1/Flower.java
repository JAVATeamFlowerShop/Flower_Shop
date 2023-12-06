package n1exercici1;

public class Flower extends Product{

    private final String colour;
    private final int id;
    private static final Product.Type type = Enum.valueOf(Product.Type.class, "FLOWER");
    public Flower(String name, float price, String colour) {
        super(name, price);
        this.colour = colour;
        this.id = Product.idProd;
    }

    public String getColour() {
        return colour;
    }

    public int getId() {
        return id;
    }
    @Override
    public Product.Type getType(){
        return type;
    }


    @Override
    public String toString(){
        return String.format("\t\t%2d %-15s %-9s %5.2f€", getId(), super.getName(), getColour(), super.getPrice());
    }

    @Override
    public boolean equals(Product p) {
        Flower f = (Flower) p;
        return this.name.equalsIgnoreCase(f.getName()) && this.price == f.getPrice() && this.colour.equalsIgnoreCase(f.getColour());
    }

}
