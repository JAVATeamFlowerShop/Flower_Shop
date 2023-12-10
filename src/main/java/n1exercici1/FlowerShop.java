package n1exercici1;

import java.util.*;

public class FlowerShop {

    private String name;
    private static Map<Product, Integer> stock;
    private float stockValue;
    private TicketHistory ticketHistory;

    private static FlowerShop instance;

    private FlowerShop(String name) {
        this.name = name;
        stock = new HashMap<Product, Integer>();
        this.ticketHistory = new TicketHistory();
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

    public static FlowerShop createFlowerShop(String shopName){
        if(instance == null) {
            instance = new FlowerShop(shopName);
            return instance;
        }
        System.err.println("Uh oh!! A Flower Shop already exists. \nCan create ONLY ONE flower shop.\n");
        return instance;
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
        updateStockValue();
    }
    public void addProductUser() throws IllegalArgumentException
    {
        int type = Readers.readInt("Introduce the product type\n" +
                "1. Decoration\n" +
                "2. Flower\n" +
                "3. Tree");
        String name = Readers.readString("Introduce its name");
        int quantity = Readers.readInt("Introduce its quantity");
        float price = Readers.readFloat("Introduce its price");

        Product product = null;

        switch(type)
        {
            case 1:
                String materialString = Readers.readString("Introduce its material (Wood or plastic)").toUpperCase();
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);
                product = new Decoration(name,price, material);
            break;

            case 2:
                String colour = Readers.readString("Introduce its colour");
                product = new Flower(name,price, colour);
                break;

            case 3 :
                float height = Readers.readFloat("Introduce its height");
                product = new Tree(name, price, height);
                break;

            default:
                System.out.println("This option is not valid");
        }

        addProduct(product, quantity);
    }
    public void removeProduct(Product product, int quantity){
        if(product != null) {
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
        updateStockValue();
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
        System.out.println("STOCK:");
        System.out.println("\tTREES");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "HEIGHT", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.keySet().stream().filter(product -> product instanceof Tree).forEach(p -> System.out.println("\t\t" + p));
        System.out.println();
        System.out.println("\tFLOWERS");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "COLOUR", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.keySet().stream().filter(product -> product instanceof Flower).forEach(p -> System.out.println("\t\t" + p));
        System.out.println();
        System.out.println("\tDECORATION");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "MATERIAL", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.keySet().stream().filter(product -> product instanceof Decoration).forEach(p -> System.out.println("\t\t" + p));
    }

    public void showStockQuantities(){
        System.out.println("STOCK AND QUANTITIES");
        System.out.println("\tTREES");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "HEIGHT", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Tree).forEach(e->System.out.printf("\t\t%s %8d\n",e.getKey(), e.getValue()));
        System.out.println("\tFLOWERS");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "COLOUR", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Flower).forEach(e->System.out.printf("\t\t%s %8d\n", e.getKey(), e.getValue()));
        System.out.println("\tDECORATION");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "MATERIAL", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Decoration).forEach(e->System.out.printf("\t\t%s %8d\n", e.getKey(), e.getValue()));
    }
    public void showShopValue(){
        System.out.printf("Shop's stock value is: %.2f eur\n", this.stockValue);
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
            Product product = findProductById(idProd);
            ticket.addProductTicket(product, quantity);
            removeProduct(product, quantity);
            isFinished = !Readers.readYesNo("Anything else? (y/n)");
        }
        ticketHistory.addTicket(ticket);
        System.out.println(ticket);
    }

    public void updateStockValue(){
        float value = calcValueStore();
        setStockValue(stockValue);
    }
    public void showPreviousPurchases(){
        System.out.println(ticketHistory);
    }
    public void showTotalSalesIncome(){
        System.out.printf("%.2f eur\n", ticketHistory.getTotalSalesAmount());
    }
}
