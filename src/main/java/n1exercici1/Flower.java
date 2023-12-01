package n1exercici1;

public class Flower extends Product{

    private final String colour;
    private final int id;
    public Flower(String name, float price, String colour, int quantity) {
        super(name, price, quantity);
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
    public String toString(){
        return "Flower {" +
                "\nname: " + super.getName() +
                "\ncolour: " + getColour() +
                "\nprice: " + super.getPrice() +
                "\nquantity: " + super.getQuantity();

    }

    @Override
    public boolean equals(Product p) {
        Flower f = (Flower) p;
        return this.name.equalsIgnoreCase(f.getName()) && this.price == f.getPrice() && this.colour.equalsIgnoreCase(f.getColour());
    }
}
