package n1exercici1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        loop();
    }

    private static void loop(){
        boolean exit = false;
        do{
            switch (menu()){
                case 1 -> createFlowerShop();
                case 2 -> addProductStock;
                case 3 -> removeProductStock();
                case 4 -> showAllStock();
                case 5 -> showStockQuantities();
                case 6 -> showShopValue();
                case 7 -> createPurchaseReceipt();
                case 8 -> showPreviousPurchases();
                case 9 -> showTotalSalesIncome();
                case 0 -> {
                    exit = true;
                    System.out.println("Bye! You exited flower shop management.");
                }
                default -> System.err.println("Please introduce a valid option.\n");
            }
        } while (!exit);
    }
    private static int menu(){
        System.out.println("FLOWER SHOP MANAGEMENT\n" +
                "1- Create flower shop\n" +
                "2- Add product to stock\n" +
                "3- Remove product from stock\n" +
                "4- Show all products in stock\n" +
                "5- Show stock with quantities\n" +
                "6- Show total flower shop value\n" +
                "7- Create purchase receipts with multiple products\n" +
                "8- Show list of previous purchases\n" +
                "9- Show total sales income\n" +
                "0- Exit\n" +
                "Choose an option: ");
        return readInt();
    }
    private static Scanner in = new Scanner(System.in);

    private static int readInt(String message){
        while (true){
            System.out.println(message);
            int i;
            try {
                i = in.nextInt();
                in.nextLine();
                return i;
            }
            catch(InputMismatchException ex) {
                System.out.println("Error de format, introdueix un enter");
                in.nextLine();
            }
        }
    }
    private static double readFloat(String message){
        while (true){
            System.out.println(message);
            float d;
            try {
                d = in.nextFloat();
                in.nextLine();
                return d;
            }
            catch(InputMismatchException ex) {
                System.out.println("Error de format, introdueix un número decimal");
                in.nextLine();
            }
        }
    }
    private static String readString(String message){
        while (true){
            System.out.println(message);
            String s;
            try {
                s = in.nextLine();
                boolean allDigits = true;
                for(int i = 0; i < s.length(); ++i) {
                    char c = s.charAt(i);
                    if(Character.isAlphabetic(c)){
                        allDigits = false;
                    }
                }
                if(allDigits){
                    throw new Exception();
                }
                return s;
            }
            catch(Exception ex) {
                System.out.println("Error de format, introdueix només caràcters alfabètics");
            }
        }
    }
}
