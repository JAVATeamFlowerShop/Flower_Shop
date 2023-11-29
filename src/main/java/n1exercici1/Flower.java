package n1exercici1;

public class Flower extends Product{

    private final String colour;
    private final int id;
    public Flower(float price, String colour, String name) {
        super(price, name);
        this.colour = colour;
        this.id = Product.idProd;
    }

    public String getColour() {
        return colour;
    }
}
