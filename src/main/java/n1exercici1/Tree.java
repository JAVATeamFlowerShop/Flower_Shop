package n1exercici1;

public class Tree extends Product{

    private final float height;
    private final int id;
  
    public Tree(float price, float height, int quantity, String name) {
        super(price, quantity, name);
        this.height = height;
        this.id = Product.idProd;
    }

    public float getHeight() {
        return height;
    }
}
