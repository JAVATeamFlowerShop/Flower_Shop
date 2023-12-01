package n1exercici1;

public class Decoration extends Product{
    public enum Material {WOOD, PLASTIC}
    private Material material;
    private final int id;

    public Decoration(String name, float price, Material material, int quantity) {
        super(name, price, quantity);
        this.material = material;
        this.id = Product.idProd;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public String showStock() {
        return "\t{name: " + super.getName() +
                "\n\tmaterial: " + getMaterial() +
                "\n\tprice: " + super.getPrice() + "}\n";
    }

    @Override
    public String toString(){
        return "\t{name: " + super.getName() +
                "\n\tmaterial: " + getMaterial() +
                "\n\tprice: " + super.getPrice() +
                "\n\tquantity: " + super.getQuantity() + "}\n";

    }
}
