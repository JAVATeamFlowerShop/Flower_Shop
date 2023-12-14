package n2exercici1;

import java.util.*;

public class Ticket {
    private static int idTicket = 0;
    private int id;
    private Map<Product, Integer> productMap;
    private float amount;

    public Ticket(){
        idTicket++;
        this.id = idTicket;
        this.productMap = new HashMap<>();
    }
    public Ticket(int id, Map<Product, Integer> productMap, float amount){
        if(id > idTicket){
            idTicket = id;
        }
        this.id = id;
        this.productMap = productMap;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }
    public Map<Product, Integer> getProductMap(){
        return productMap;
    }
    public float getAmount() {
        return amount;
    }
    public void decreaseIdTicket(){
        idTicket--;
    }
    public void addProductTicket(Product product, int quantity){
        this.productMap.put(product, quantity);
        updateAmount();
    }
    private float calcAmount(){
        if (productMap.isEmpty()){
            return 0f;
        } else {
            return (float) productMap.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
        }
    }
    private void updateAmount(){
        this.amount = this.calcAmount();
    }

    public String toPrettyString(){
        String header = "TICKET NUMBER " + getId() + "\n---------------------------------------------\n" + String.format("%2s %-15s %-9s %-6s %8s\n", "ID", "NAME", "DETAIL", "PRICE", "QUANTITY");
        StringBuilder products = new StringBuilder();
        productMap.forEach((key, value) -> products.append(String.format("%s %8d\n", key.toPrettyString(), value)));
        String total = "---------------------------------------------\n" + String.format("%28s %.2f€","TOTAL:", getAmount());
        return String.format("%s%s%s\n", header, products, total);
    }
    @Override
    public String toString() {
        return String.format("%d,%s,%.2f", getId(), productMap, getAmount());
    }
}
