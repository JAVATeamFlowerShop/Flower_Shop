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
        return "TICKET NUMBER " + getId() +"\n---------------------------------\n" + "\t TOTAL: " + getAmount();
    }
}
