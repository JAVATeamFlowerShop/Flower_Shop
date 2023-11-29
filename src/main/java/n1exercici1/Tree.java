package n1exercici1;

public class Tree extends Product{

    private final float height;
    private final int id;

    public Tree(String name, float price, float height) {
        super(name, price);
        this.height = height;
        this.id = Product.idProd;
    }

    public float getHeight() {
        return height;
    }
}
