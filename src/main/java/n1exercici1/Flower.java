package n1exercici1;

public class Flower extends Product{

    private final String colour;
    private final int id;
    public Flower(float price, String colour) {
        super(price);
        this.id = Product.idProd;
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
