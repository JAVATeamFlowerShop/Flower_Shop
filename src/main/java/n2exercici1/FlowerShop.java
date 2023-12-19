package n2exercici1;

import n2exercici1.exceptions.*;

import java.util.*;

public class FlowerShop {
    private final String name = "CiberFlower";
    private List<Product> stock;
    private float stockValue;
    private float totalSalesAmount;
    private static FlowerShop instance;
    private FlowerShop() {
        stock = DataBaseManager.loadStock();
        updateStockValue();
        updateTotalSalesAmount();
    }

    public String getName() {
        return name;
    }
    public float getTotalSalesAmount() {
        return totalSalesAmount;
    }
    public void setStockValue(float stockValue) {
        this.stockValue = stockValue;
    }
    public void setTotalSalesAmount(float totalSalesAmount){
        this.totalSalesAmount = totalSalesAmount;
    }
    public static FlowerShop createFlowerShop(){
        if(instance == null) {
            instance = new FlowerShop();
            return instance;
        }
        System.err.println("Uh oh!! A Flower Shop already exists. \nCan create ONLY ONE flower shop.\n");
        return instance;
    }

    public void updateStockValue(){
        setStockValue(DataBaseManager.calcStockValue());
    }
    public void updateTotalSalesAmount() {
        setTotalSalesAmount(DataBaseManager.calcSalesValue());
    }
    public void addProduct() throws IllegalArgumentException{
        int type = Readers.readInt("Introduce the product type\n" +
                "1. Decoration\n" +
                "2. Flower\n" +
                "3. Tree");
        String name = Readers.readString("Introduce its name");
        int quantity = Readers.readInt("Introduce its quantity");
        float price = Readers.readFloat("Introduce its price");

        Product product;

        switch (type) {
            case 1 -> {
                String materialString = Readers.readString("Introduce its material (Wood or plastic)").toUpperCase();
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);
                stock.stream().filter(p -> p instanceof Decoration && p.equals(new Decoration(name, price, material)));
                product = new Decoration(name, price, material);
                DataBaseManager.saveProduct(product, quantity);
                stock.add(product);
            }
            case 2 -> {
                String colour = Readers.readString("Introduce its colour");
                product = new Flower(name, price, colour);
                DataBaseManager.saveProduct(product, quantity);
                stock.add(product);
            }
            case 3 -> {
                float height = Readers.readFloat("Introduce its height");
                product = new Tree(name, price, height);
                DataBaseManager.saveProduct(product, quantity);
                stock.add(product);
            }
            default -> System.out.println("This option is not valid, product not saved");
        }
        updateStockValue();
    }
    public void addProduct(Product product, int quantity){
        DataBaseManager.saveProduct(product, quantity);
        updateStockValue();
    }
    public void removeProduct() throws ItemNotFoundException, NotEnoughStockException{
        showStockQuantities();
        int idProd = Readers.readInt("What product do you want to remove from the stock?\nPlease input product id");
        Product product = findProductById(idProd);
        int quantity = Readers.readInt(product.getName() + ": " + DataBaseManager.findProdQuantity(product) + " units.\nHow many do you want to remove?");

        removeProduct(product, quantity);
    }
    public void removeProduct(Product product, int quantity) throws NotEnoughStockException{
        int currQuantity = DataBaseManager.findProdQuantity(product);
        if (currQuantity >= quantity) {
            DataBaseManager.changeStock(product, -quantity);
        } else {
            throw new NotEnoughStockException();
        }
        updateStockValue();
    }
    public void showAllStock() {
        System.out.println("STOCK:");
        System.out.println("\tTREES");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "HEIGHT", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.stream().filter(product -> product instanceof Tree).forEach(p -> System.out.println("\t\t" + p.toPrettyString()));
        System.out.println();
        System.out.println("\tFLOWERS");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "COLOUR", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.stream().filter(product -> product instanceof Flower).forEach(p -> System.out.println("\t\t" + p.toPrettyString()));
        System.out.println();
        System.out.println("\tDECORATIONS");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "MATERIAL", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.stream().filter(product -> product instanceof Decoration).forEach(p -> System.out.println("\t\t" + p.toPrettyString()));
    }
    public void showStockQuantities(){
        System.out.println("STOCK WITH QUANTITIES");
        System.out.println("\tTREES");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "HEIGHT", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
  //      stock.stream().filter(p -> p instanceof Tree).forEach(e->System.out.printf("\t\t%s %8d\n",e.getKey().toPrettyString(), e.getValue()));
        System.out.println("\tFLOWERS");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "COLOUR", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
  //      stock.stream().filter(p -> p instanceof Flower).forEach(e->System.out.printf("\t\t%s %8d\n",e.getKey().toPrettyString(), e.getValue()));
        System.out.println("\tDECORATIONS");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "MATERIAL", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
  //      stock.stream().filter(p -> p instanceof Decoration).forEach(e->System.out.printf("\t\t%s %8d\n",e.getKey().toPrettyString(), e.getValue()));
    }
    public void showShopValue(){
        System.out.printf("SHOP'S STOCK VALUE: %.2f€\n", this.stockValue);
    }
    public void createPurchaseReceipt(){
        System.out.println("Let's create the purchase ticket");
        Ticket ticket = new Ticket();
        boolean isFinished= false;

        while (!isFinished){
            showStockQuantities();
            int idProd = Readers.readInt("Which products is the client buying?\nPlease input product id");
            try{
                Product product = findProductById(idProd);
                int quantity = Readers.readInt(product.getName() + ": " + DataBaseManager.findProdQuantity(product) + " units.\nHow many are they buying?");
                removeProduct(product, quantity);
                ticket.addProductTicket(product, quantity);
            }
            catch (NotEnoughStockException | ItemNotFoundException ex){
                System.err.println(ex.getMessage());
            }
            finally {
                isFinished = !Readers.readYesNo("Anything else? (y/n)");
            }
        }
        if(!ticket.getProductMap().isEmpty()){
            DataBaseManager.saveTicket(ticket);
            updateTotalSalesAmount();
            System.out.println(ticket.toPrettyString());
        }
        else{
            ticket.decreaseIdTicket();
            System.out.println("No items bought, no ticket created");
        }
    }
    public void showPreviousPurchases(){
        List<Ticket> tickets = DataBaseManager.loadTickets();
        tickets.forEach(t-> System.out.println(t.toPrettyString()));
    }
    public void showTotalSalesIncome(){
        System.out.printf("TOTAL SALES INCOME: %.2f€\n", getTotalSalesAmount());
    }
    private Product findProductById(int id) throws ItemNotFoundException{
        Product myProduct = stock.stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
        if (myProduct == null){
            throw new ItemNotFoundException();
        }
        return myProduct;
    }
}
