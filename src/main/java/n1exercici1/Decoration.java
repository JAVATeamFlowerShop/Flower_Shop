package n1exercici1;


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
    public String showStock() {
        return "\t{name: " + super.getName() +
                "\n\tmaterial: " + getMaterial() +
                "\n\tprice: " + super.getPrice() + "}\n";

    @Override
    public boolean equals(Product p) {
        Decoration d = (Decoration) p;
        return this.name.equalsIgnoreCase(d.getName()) && this.price == d.getPrice() && this.material == d.getMaterial();
    }

    @Override
    public String toString(){
        return "\t{name: " + super.getName() +
                "\n\tmaterial: " + getMaterial() +
                "\n\tprice: " + super.getPrice() +
                "\n\tquantity: " + super.getQuantity() + "}\n";

    }
}
