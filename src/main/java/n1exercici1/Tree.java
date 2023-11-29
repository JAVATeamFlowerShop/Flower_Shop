package n1exercici1;

public class Tree extends Product{

    private float height;
    public Tree(float price, float height, int quantity, String name) {
        super(price, quantity, name);
        this.height = height;
    }

    public float getHeight() {
        return height;
    }
}
