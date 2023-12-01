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

    public int getId() {
        return id;
    }

    public String toString(){
        return "Tree {" +
                "\nname: " + super.getName() +
                "\nheight: " + getHeight() +
                "\nprice: " + super.getPrice() +
                "\nquantity: " + super.getQuantity();

    }

    @Override
    public boolean equals(Product p) {
        Tree t = (Tree) p;
        return this.name.equalsIgnoreCase(t.getName()) && this.price == t.getPrice() && this.height == t.getHeight();    }
}
