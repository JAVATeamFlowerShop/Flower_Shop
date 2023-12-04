package n1exercici1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlowerShop {

    private String name;
    private static List<Product> stock;
    private float stockValue;
    private List<Ticket> ticketHistory;

    private static FlowerShop instance;

    private FlowerShop(String name) {
        this.name = name;
        stock = new ArrayList<Product>();
        this.stockValue = calcValue();
        this.ticketHistory = new ArrayList<Ticket>();
    }
    public String getName() {
        return name;
    }

    public List<Product> getStock() {
        return stock;
    }

    private float calcValue(){
        if (stock.isEmpty()){
            return 0f;
        } else {
            return (float) stock.stream().mapToDouble(prod -> prod.getPrice() * prod.getQuantity()).sum();
        }
    }


    public static FlowerShop createFlowerShop(){

        if(instance == null) {

            String shopName = Readers.readString("Introduce the flower shop name: ");
            while (true) {
                System.out.printf("Name: " + shopName);
                if (Readers.readYesNo("\nIs that correct?(y/n)")) {
                    instance = new FlowerShop(shopName);
                    return instance;
                } else {
                    shopName = Readers.readString("Introduce the name again: ");
                }
            }
        }

        return instance;
    }
    public void addProduct(Product product){
        if(stock.stream().anyMatch(p-> p.equals(product))){
            System.out.println("Product already in stock, quantity will be added");
            stock.stream()
                    .filter(p-> p.equals(product))
                    .forEach(p-> p.increaseQuantity(product.getQuantity()));
        }
        else {
            System.out.println("Product added to stock");
            stock.add(product);
        }

    }
    public void addProductUser() throws IllegalArgumentException
    {
        String name = Readers.readString("Introduce its name");
        int quantity = Readers.readInt("Introduce its quantity");
        float price = Readers.readFloat("Introduce its price");

        int type = Readers.readInt("Introduce the product type\n" +
                "1. Decoration\n" +
                "2. Flower\n" +
                "3. Tree");


        Product product = null;

        switch(type)
        {
            case 1:
                String materialString = Readers.readString("Introduce its material (Wood or plastic)").toUpperCase();
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);
                product = new Decoration(name,price, material, quantity);
                break;

            case 2:
                String colour = Readers.readString("Introduce its colour");
                product = new Flower(name,price, colour, quantity);

                break;

            case 3:
                float height = Readers.readFloat("Introduce its height");
                product = new Tree(name, price, height, quantity);

                break;

            default:
                System.out.println("This option is not valid");
        }

        addProduct(product);
    }

    public void removeProduct()
    {
        String name = Readers.readString("What product do you want to remove from the stock");
        Product product = findProduct(name);

        if(product != null) {
             int quantity = Readers.readInt("What quantity?");

             if (product.getQuantity() > quantity) {
                int productIndex = stock.indexOf(product);
                stock.get(productIndex).decreaseQuantity(quantity);
             } else {
                if (product.getQuantity() == quantity) {
                    stock.remove(product);
                } else {
                    System.out.println("There is not enough quantity of this product");
                }
             }
        }
        else {
            System.out.println("This product is not found in the stock");
        }
    }

    private Product findProduct(String name)
    {
        Optional<Product> product = stock.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();

        return product.get();

    }
    public void showAllStock(){
        this.getStock().forEach(product -> System.out.println(product + "\n"));
    }
    public void showShopValue(){
        String stockValue = String.format("%.2f", this.calcValue());
        System.out.printf("Shop's stock value is: %s eur\n", stockValue);
    }


}
