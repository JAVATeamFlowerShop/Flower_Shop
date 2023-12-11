package n1exercici1;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.*;
import n1exercici1.exceptions.ItemNotFoundException;

import java.util.*;

public class FlowerShop {

    private final String name = "Masama";

    private static Map<Product, Integer> stock;
    private float stockValue;
    private TicketHistory ticketHistory;

    private static FlowerShop instance;
    private FlowerShop() {
        stock = LoadData.loadStock();
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
    public static FlowerShop createFlowerShop(){
        if(instance == null) {
            instance = new FlowerShop();
            return instance;
        }
        System.err.println("Uh oh!! A Flower Shop already exists. \nCan create ONLY ONE flower shop.\n");
        return instance;
    }

    public void updateStockValue(){
        setStockValue(calcValueStore());
    }

    public float calcValueStore(){
        return calcValue(stock);
    }

    private float calcValue(Map<Product, Integer> productQuantityMap){
        if (productQuantityMap == null){
            return 0f;
        } else {
            return (float) productQuantityMap.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
        }
    }

    public void addProduct() throws IllegalArgumentException{
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

    public void removeProduct() throws ItemNotFoundException{
        showStockQuantities();
        int idProd = Readers.readInt("What product do you want to remove from the stock?\nPlease input product id");
        Product product = findProductById(idProd);
        int quantity = Readers.readInt(product.getName() + ": " + stock.get(product) + " units.\nHow many do you want to remove?");

        removeProduct(product, quantity);
    }

    public void removeProduct(Product product, int quantity){
        if (stock.get(product) >= quantity) {
            int newQuantity = stock.get(product) - quantity;
            stock.replace(product, newQuantity);
        } else {
            System.out.println("There is not enough quantity of this product");
        }
        updateStockValue();
    }

    public void showAllStock() {
        System.out.println("STOCK:");
        System.out.println("\tTREES");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "HEIGHT", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.keySet().stream().filter(product -> product instanceof Tree).forEach(p -> System.out.println("\t\t" + p.toPrettyString()));
        System.out.println();
        System.out.println("\tFLOWERS");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "COLOUR", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.keySet().stream().filter(product -> product instanceof Flower).forEach(p -> System.out.println("\t\t" + p.toPrettyString()));
        System.out.println();
        System.out.println("\tDECORATIONS");
        System.out.printf("\t\t%2s %-15s %-9s %6s", "ID", "NAME", "MATERIAL", "PRICE");
        System.out.println();
        System.out.println("\t\t-----------------------------------");
        stock.keySet().stream().filter(product -> product instanceof Decoration).forEach(p -> System.out.println("\t\t" + p.toPrettyString()));
    }

    public void showStockQuantities(){
        System.out.println("STOCK WITH QUANTITIES");
        System.out.println("\tTREES");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "HEIGHT", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Tree).forEach(e->System.out.printf("\t\t%s %8d\n",e.getKey().toPrettyString(), e.getValue()));
        System.out.println("\tFLOWERS");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "COLOUR", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Flower).forEach(e->System.out.printf("\t\t%s %8d\n", e.getKey().toPrettyString(), e.getValue()));
        System.out.println("\tDECORATIONS");
        System.out.printf("\t\t%2s %-15s %-9s %-6s %8s", "ID", "NAME", "MATERIAL", "PRICE", "QUANTITY");
        System.out.println();
        System.out.println("\t\t--------------------------------------------");
        stock.entrySet().stream().filter(e -> e.getKey() instanceof Decoration).forEach(e->System.out.printf("\t\t%s %8d\n", e.getKey().toPrettyString(), e.getValue()));
    }

    public void showShopValue(){
        System.out.printf("SHOP'S STOCK VALUE: %.2f€\n", this.stockValue);
    }

    public void createPurchaseReceipt() throws ItemNotFoundException{
        System.out.println("Let's create the purchase ticket");
        Ticket ticket = new Ticket();
        boolean isFinished= false;

        while (!isFinished){
            showStockQuantities();
            int idProd = Readers.readInt("Which products is the client buying?\nPlease input product id");
            Product product = findProductById(idProd);
            int quantity = Readers.readInt(product.getName() + ": " + stock.get(product) + " units.\nHow many are they buying?");
            ticket.addProductTicket(product, quantity);
            removeProduct(product, quantity);
            isFinished = !Readers.readYesNo("Anything else? (y/n)");
        }
        ticketHistory.addTicket(ticket);
        System.out.println(ticket);
    }

    public void showPreviousPurchases(){
        System.out.println(ticketHistory);
    }
    public void showTotalSalesIncome(){
        System.out.printf("TOTAL SALES INCOME: %.2f€\n", ticketHistory.getTotalSalesAmount());
    }

    private Product findProductById(int id) throws ItemNotFoundException{
        Product myProduct = stock.keySet().stream()
                .filter(product -> product.getId() == id)
                .findAny()
                .orElse(null);
        if (myProduct == null){
            throw new ItemNotFoundException();
        }
        return myProduct;
    }
}
