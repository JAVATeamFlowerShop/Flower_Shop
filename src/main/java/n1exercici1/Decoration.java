package n1exercici1;

public class Decoration extends Product{

   private enum Material {WOOD, PLASTIC}
   private Material material;
   private final int id;
    public Decoration(float price, Material material) {
        super(price);
        this.id = Product.idProd;
        this.material = material;

    }

    public Material getMaterial() {
        return material;
    }
}
