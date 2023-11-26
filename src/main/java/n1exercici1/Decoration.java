package n1exercici1;

public class Decoration extends Product{

   private enum Material {WOOD, PLASTIC}
   private Material material;
    public Decoration(float price, Material material) {
        super(price);
        this.material = material;

    }

    public Material getMaterial() {
        return material;
    }
}
