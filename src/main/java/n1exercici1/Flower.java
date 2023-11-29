package n1exercici1;

public class Flower extends Product{

    private final String colour;
    private final int id;
    public Flower(float price, String colour, String name, int quantity) {
        super(price, name, quantity);
        this.colour = colour;
        this.id = Product.idProd;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString(){
        return "Flower {" +
                "\nname: " + super.getName() +
                "\ncolour: " + getColour() +
                "\nprice: " + super.getPrice() +
                "\nquantity: " + super.getQuantity();

    }
}
