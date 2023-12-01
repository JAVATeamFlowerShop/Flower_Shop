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


    @Override
    public int getId() {
        return id;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Product p) {
        Decoration d = (Decoration) p;
        return this.name.equalsIgnoreCase(d.getName()) && this.price == d.getPrice() && this.material == d.getMaterial();
    }

    @Override
    public String toString(){
        return "Decoration {" +
                "\nname: " + super.getName() +
                "\nmaterial: " + getMaterial() +
                "\nprice: " + super.getPrice() +
                "\nquantity: " + super.getQuantity();

    }
}
