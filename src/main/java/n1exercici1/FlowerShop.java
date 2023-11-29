package n1exercici1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FlowerShop {
    
    private String name;
    private List<Product> stock;
    private float stockValue;

    public FlowerShop()
    {
        stock = new ArrayList<Product>();
    }

    public void addProduct() throws IllegalArgumentException
    {
        String name = askString("Introduce its name");
        float price = askFloat("Introduce its price");
        int quantity = askInt("Introduce its quantity");

        int type = askInt("Introduce its type " +
                            "1. Decoration " +
                            "2. Flower " +
                            "3. Tree ");



        switch(type)
        {
            case 1:


                String materialString = askString("Introduce its material (Wood or plastic)").toUpperCase();

                Decoration.Material material = Enum.valueOf(Decoration.Material.class, materialString);

                Decoration decoration = new Decoration(price, material, quantity, name);

                stock.add(decoration);

                break;

            case 2:


                String colour = askString("Introduce its colour");

                Flower flower = new Flower(price, colour, quantity, name);

                stock.add(flower);

                break;

            case 3:


                float height = askFloat("Introduce its height");

                Tree tree = new Tree(price, height, quantity, name);

                stock.add(tree);

                break;

            default:

                System.out.println("This option is not valid");
        }


    }

    public void removeProduct()
    {
        String name = askString("What product do you want to remove from the stock");

        Product product = findProduct(name);

        if(product != null) {

             int quantity = askInt("What quantity?");

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

    public int askInt(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        int input = scanner.nextInt();

        return input;
    }

    public String askString(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        String input = scanner.nextLine();

        return input;
    }

    public float askFloat(String message)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(message);

        float input = scanner.nextFloat();

        return input;
    }
>>>

}
