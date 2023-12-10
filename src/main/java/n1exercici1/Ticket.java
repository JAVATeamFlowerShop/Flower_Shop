package n1exercici1;

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

    public void addProductTicket(Product product, int quantity){
        this.productMap.put(product, quantity);
        updateAmount();

    }

    private void updateAmount(){
        this.amount = this.calcAmount();
    }

    private float calcAmount(){
        if (productMap.isEmpty()){
            return 0f;
        } else {
            return (float) productMap.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
        }
    }

    public int getId() {
        return id;
    }

    public Map<Product, Integer> getProductMap() {
        return productMap;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString(){
        String header = "TICKET NUMBER " + getId() + "\n---------------------------------------------\n" + String.format("%2s %-15s %-9s %-6s %8s\n", "ID", "NAME", "DETAIL", "PRICE", "QUANTITY");
        StringBuilder products = new StringBuilder();
        productMap.forEach((key, value) -> products.append(String.format("%s %8d\n", key, value)));
        String total = "---------------------------------------------\n" + String.format("%28s %.2f€","TOTAL:", getAmount());
        return String.format("%s%s%s\n", header, products, total);
    }
}
