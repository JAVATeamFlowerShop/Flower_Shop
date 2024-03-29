package n1exercici1;

import n1exercici1.exceptions.ItemNotFoundException;
import n1exercici1.exceptions.NotEnoughStockException;

import java.util.Locale;

public class Main {
    private static FlowerShop myShop;
    static {
        Locale.setDefault(Locale.ENGLISH);
        createFlowerShop();
    }

    public static void main(String[] args) {
        System.out.println("Welcome!");
        loop();
    }

    private static void loop(){
        boolean exit = false;
        do{
            switch (menu()){
                case 1 -> myShop.addProduct();
                case 2 -> {try {myShop.removeProduct();}
                    catch (NotEnoughStockException | ItemNotFoundException e) {System.out.println(e.getMessage());}}
                case 3 -> myShop.showAllStock();
                case 4 -> myShop.showStockQuantities();
                case 5 -> myShop.showShopValue();
                case 6 -> myShop.createPurchaseReceipt();
                case 7 -> myShop.showPreviousPurchases();
                case 8 -> myShop.showTotalSalesIncome();
                case 0 -> {
                    exit = true;
                    System.out.println("Saving stock...");
                    LoadData.saveStock(myShop.getStock());
                    System.out.println("Saving tickets...");
                    LoadData.saveTickets(myShop.getTicketHistory());
                    System.out.println("Bye! You exited flower shop management.");
                }
                default -> System.err.println("Please introduce a valid option.\n");
            }
        } while (!exit);
    }
    private static int menu(){
        System.out.printf("\n'%s' FLOWER SHOP MANAGEMENT%n", myShop.getName().toUpperCase());
        return Readers.readInt("1- Add product to stock\n" +
                "2- Remove product from stock\n" +
                "3- Show all products in stock\n" +
                "4- Show stock with quantities\n" +
                "5- Show total flower shop value\n" +
                "6- Generate sales ticket\n" +
                "7- Show list of previous purchases\n" +
                "8- Show total sales income\n" +
                "0- Exit\n" +
                "Choose an option: ");
    }
    private static void createFlowerShop(){
        myShop = FlowerShop.createFlowerShop();
    }
}
