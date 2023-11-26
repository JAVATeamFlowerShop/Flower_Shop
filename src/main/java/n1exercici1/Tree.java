package n1exercici1;

public class Tree extends Product{

    private float height;
    public Tree(float price, float height) {
        super(price);
        this.height = height;
    }

    public float getHeight() {
        return height;
    }
}
