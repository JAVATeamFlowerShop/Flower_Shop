package n1exercici1;

public class Decoration extends Product{

    public enum Material {WOOD, PLASTIC}
    private Material material;
    private final int id;

    public Decoration(float price, Material material, String name) {
        super(price, name);
        this.material = material;
        this.id = Product.idProd;
    }

    public Material getMaterial() {
        return material;
    }
}
