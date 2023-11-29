package n1exercici1;

public class Flower extends Product{

    private String colour;
    public Flower(float price, String colour, int quantity, String name) {
        super(price, quantity, name);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
