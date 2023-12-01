package n1exercici1;

public class Tree extends Product{

    private final float height;
    private final int id;

    public Tree(String name, float price, float height, int quantity) {
        super(name, price, quantity);
        this.height = height;
        this.id = Product.idProd;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public String showStock() {
        return "\t{name: " + super.getName() +
                "\n\theight: " + getHeight() +
                "\n\tprice: " + super.getPrice() + "}\n";
    }

    @Override
    public String toString(){
        return "\t{name: " + super.getName() +
                "\n\theight: " + getHeight() +
                "\n\tprice: " + super.getPrice() +
                "\n\tquantity: " + super.getQuantity() + "}\n";

    }
}
