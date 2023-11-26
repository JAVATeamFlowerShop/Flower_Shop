package n1exercici1;

public class Flower extends Product{

    private String colour;
    public Flower(float price, String colour) {
        super(price);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
