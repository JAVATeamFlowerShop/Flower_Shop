package n1exercici1;

public class Tree extends Product{

    private final float height;
    private final int id;

    public Tree(float price, float height, String name, int quantity) {
        super(price, name, quantity);
        this.height = height;
        this.id = Product.idProd;
    }

    public float getHeight() {
        return height;
    }

    public String toString(){
        return "Tree {" +
                "\nname: " + super.getName() +
                "\nheight: " + getHeight() +
                "\nprice: " + super.getPrice() +
                "\nquantity: " + super.getQuantity();

    }
}
