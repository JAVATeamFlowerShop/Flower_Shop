package n1exercici1;

public class Tree extends Product{

    private final float height;
    private final int id;
    public Tree(float price, float height) {
        super(price);
        this.id = Product.idProd;
        this.height = height;
    }

    public float getHeight() {
        return height;
    }
}
