package n1exercici1;

public class Flower extends Product{

    private final String colour;
    private final int id;
    public Flower(float price, String colour int quantity, String name) {
        super(price, quantity, name);
        this.colour = colour;
        this.id = Product.idProd;
    }

    public String getColour() {
        return colour;
    }
}
