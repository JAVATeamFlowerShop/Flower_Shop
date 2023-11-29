package n1exercici1;

public class Decoration extends Product{

    public enum Material {WOOD, PLASTIC}
    private Material material;
    private final int id;

    public Decoration(String name, float price, Material material) {
        super(name, price);
        this.material = material;
        this.id = Product.idProd;
    }

    public Material getMaterial() {
        return material;
    }
}
