package n1exercici1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlowerShop {

    private String name;
    private static List<Product> stock;
    private float stockValue;
    private List<Ticket> ticketHistory;

    public FlowerShop(String name) {
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

    public static float calcValue(){
        if (stock.isEmpty()){
            return 0f;
        } else {
            return (float) stock.stream().mapToDouble(prod -> prod.getPrice() * prod.getQuantity()).sum();
        }
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
        LoadInitialData.createStock();
    }
    public static void addProduct() throws IllegalArgumentException
    {
        int type = Readers.readInt("Introduce the product type\n " +
                "1. Decoration\n" +
                "2. Flower\n " +
                "3. Tree");
        int quantity = Readers.readInt("Introduce its quantity");
        String name = Readers.readString("Introduce its name");
        float price = Readers.readFloat("Introduce its price");
        switch(type)
        {
            case 1:
                String materialString = Readers.readString("Introduce its material (Wood or plastic)").toUpperCase();
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);
                Decoration decoration = new Decoration(price, material, name, quantity);
                stock.add(decoration);
                break;

            case 2:
                String colour = Readers.readString("Introduce its colour");
                Flower flower = new Flower(price, colour, name, quantity);
                stock.add(flower);
                break;

            case 3:
                float height = Readers.readFloat("Introduce its height");
                Tree tree = new Tree(price, height, name, quantity);
                stock.add(tree);
                break;

            default:
                System.out.println("This option is not valid");
        }
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

    public Product findProduct(String name)
    {
        Optional<Product> product = stock.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();

        return product.get();

    }


}
