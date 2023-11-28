package n1exercici1;

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
}
