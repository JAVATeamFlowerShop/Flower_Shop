package n1exercici1;

import java.util.*;

public class FlowerShop {

    private String name;
    private static Map<Product, Integer> stock;
    private float stockValue;
    private List<Ticket> ticketHistory;

    public FlowerShop(String name) {
        this.name = name;
        stock = new HashMap<Product, Integer>();
        this.ticketHistory = new ArrayList<Ticket>();
    }
    public String getName() {
        return name;
    }

    public Map<Product,Integer> getStock() {
        return stock;
    }

    public void setStockValue(float stockValue) {
        this.stockValue = stockValue;
    }
    private float calcValue(Map<Product, Integer> productQuantityMap){
        if (productQuantityMap.isEmpty()){
            return 0f;
        } else {
            return (float) productQuantityMap.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
        }
    }
    public float calcValueStore(){
        return calcValue(stock);
    }

    public static FlowerShop createFlowerShop(){
        String shopName = Readers.readString("Introduce the flower shop name: ");
        while (true){
            System.out.printf("Name: " + shopName);
            if (Readers.readYesNo("\nIs that correct?(y/n)")){
                return new FlowerShop(shopName);
            } else {
                shopName = Readers.readString("Introduce the name again: ");
            }
        }
    }
    public void addProduct(Product product, int quantity){
        if(stock.entrySet().stream().anyMatch(e-> e.getKey().getType() == product.getType() && e.getKey().equals(product))){
            System.out.println("Product already in stock, quantity will be added");
            stock.entrySet().stream()
                    .filter(e-> e.getKey().getType() == product.getType() && e.getKey().equals(product))
                    .forEach(e-> e.setValue(e.getValue() + quantity));
        }
        else {
            System.out.println("Product added to stock");
            stock.put(product, quantity);
        }
    }
    public void addProductUser() throws IllegalArgumentException
    {
        int type = Readers.readInt("Introduce the product type\n " +
                "1. Decoration\n" +
                "2. Flower\n " +
                "3. Tree");
        int quantity = Readers.readInt("Introduce its quantity");
        String name = Readers.readString("Introduce its name");
        float price = Readers.readFloat("Introduce its price");
        switch (type) {
            case 1 -> {
                String materialString = Readers.readString("Introduce its material (Wood or plastic)").toUpperCase();
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);
                Decoration decoration = new Decoration(name, price, material);
                addProduct(decoration, quantity);
            }
            case 2 -> {
                String colour = Readers.readString("Introduce its colour");
                Flower flower = new Flower(name, price, colour);
                addProduct(flower, quantity);
            }
            case 3 -> {
                float height = Readers.readFloat("Introduce its height");
                Tree tree = new Tree(name, price, height);
                addProduct(tree, quantity);
            }
            default -> System.out.println("This option is not valid");
        }
    }

    public void removeProduct()
    {
        String name = Readers.readString("What product do you want to remove from the stock");
        Product product = findProduct(name);

        if(product != null) {
             int quantity = Readers.readInt("What quantity?");

             if (stock.get(product) >= quantity) {
                int newQuantity = stock.get(product) - quantity;
                stock.replace(product, newQuantity);
             } else {
                 System.out.println("There is not enough quantity of this product");
             }
        }
        else {
            System.out.println("This product is not found in the stock");
        }
    }

    private Product findProduct(String name){
        Optional<Product> product = stock.keySet().stream()
                .filter(prod -> prod.getName().equals(name))
                .findFirst();

        return product.get();
    }

    public void showAllStock() {
        System.out.println("STOCK:\nTrees: ");
        stock.keySet().stream().filter(product -> product instanceof Tree).forEach((product -> System.out.println(product.showStock())));
        System.out.println("\nFlowers:");
        stock.keySet().stream().filter(product -> product instanceof Flower).forEach((product -> System.out.println(product.showStock())));
        System.out.println("\nDecoration:");
        stock.keySet().stream().filter(product -> product instanceof Decoration).forEach((product -> System.out.println(product.showStock())));
    }

    public void showStockQuantities(){
        System.out.println("STOCK WITH QUANTITIES:\nTrees: ");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Tree).forEach(e->System.out.println(e.getKey() + "\tQUANTITY = " + e.getValue()));
        System.out.println("\nFlowers:");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Flower).forEach(e->System.out.println(e.getKey() + "\tQUANTITY = " + e.getValue()));
        System.out.println("\nDecoration:");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Decoration).forEach(e->System.out.println(e.getKey() + "\tQUANTITY = " + e.getValue()));
    }
    public void showShopValue(){
        String stockValue = String.format("%.2f", this.calcValueStore());
        System.out.printf("Shop's stock value is: %s eur\n", stockValue);
    }
    private Product findProductById(int id){
        Product myProduct = stock.keySet().stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
        if (myProduct == null){
            //TODO
            //throw exception para evitar nullpointer exception
            System.out.println("Item not found");
        }
        return myProduct;
    }
    public void createPurchaseReceipt(){
        System.out.println("Let's create the purchase ticket");
        Ticket ticket = new Ticket();
        System.out.println("Which products is the client buying?");
        showAllStock();
        boolean isFinished= false;
        while (!isFinished){
            int idProd = Readers.readInt("Please input product id");
            int quantity = Readers.readInt("How many?");
            ticket.addProductTicket(findProductById(idProd), quantity);
            isFinished = !Readers.readYesNo("Anything else?");
        }
        System.out.println(ticket);
    }
}
