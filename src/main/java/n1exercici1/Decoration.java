package n1exercici1;

public class Decoration extends Product{

   public enum Material {WOOD, PLASTIC}
   private Material material;
    public Decoration(float price, Material material, int quantity, String name) {
        super(price, quantity, name);
        this.material = material;

    }

    public Material getMaterial() {
        return material;
    }
}
