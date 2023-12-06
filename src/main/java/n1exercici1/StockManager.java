package n1exercici1;

public class StockManager {
    private FlowerShop flowerShop;
    private int amount;
    private Product product;
    public StockManager(FlowerShop myShop){
        this.flowerShop = myShop;
    }
    public void update(Product product, int amount){
        updateStock(product, amount);
    }
    private void updateStock(Product product, int amount){
        flowerShop.removeProduct(product, amount);
    }
}
