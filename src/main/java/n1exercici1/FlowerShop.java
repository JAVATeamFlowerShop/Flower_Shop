package n1exercici1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlowerShop {

    private String name;
    private List<Product> stock;
    private float stockValue;
    private List<Ticket> ticketHistory;
    private static FlowerShop instanceM

    private FlowerShop(String name)
    {
        this.name = name;
        this.stock = new ArrayList<Product>();
        this.stockValue = calcValue();
        this.ticketHistory = new ArrayList<Ticket>();
    }
    public String getName() {
        return name;
    }

    public List<Product> getStock() {
        return stock;
    }


    public float calcValue(){
        if (this.stock.isEmpty()){
            return 0f;
        } else {
            return (float) this.stock.stream().mapToDouble(prod -> prod.getPrice() * prod.getQuantity()).sum();
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
    public void addProduct() throws IllegalArgumentException
    {
        String name = Readers.readString("Introduce its name");
        float price = Readers.readFloat("Introduce its price");
        int quantity = Readers.readInt("Introduce its quantity");

        int type = Readers.readInt("Introduce its type " +
                            "1. Decoration " +
                            "2. Flower " +
                            "3. Tree ");

        switch(type)
        {
            case 1:
                String materialString = Readers.readString("Introduce its material (Wood or plastic)").toUpperCase();
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);
                Decoration decoration = new Decoration(price, material, name);
                stock.add(decoration);
                break;

            case 2:
                String colour = Readers.readString("Introduce its colour");
                Flower flower = new Flower(price, colour, name);
                stock.add(flower);
                break;

            case 3:
                float height = Readers.readFloat("Introduce its height");
                Tree tree = new Tree(price, height, name);
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
