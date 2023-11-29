package n1exercici1;

public class Main {
    static FlowerShop myShop = createFlowerShop();

    public static void main(String[] args) {
        loop();
    }

    private static void loop(){
        boolean exit = false;
        do{
            switch (menu()){
                case 1 -> FlowerShop.addProduct();
//                case 2 -> removeProductStock();
                case 3 -> showAllStock();
//                case 4 -> showStockQuantities();
                case 5 -> showShopValue();
//                case 6 -> createPurchaseReceipt();
//                case 7 -> showPreviousPurchases();
//                case 8 -> showTotalSalesIncome();
                case 0 -> {
                    exit = true;
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
                "6- Create purchase receipts with multiple products\n" +
                "7- Show list of previous purchases\n" +
                "8- Show total sales income\n" +
                "0- Exit\n" +
                "Choose an option: ");
    }

    private static FlowerShop createFlowerShop(){
        System.out.println("Welcome to the management app for your flower shop!\n");
        return FlowerShop.createFlowerShop();
    }

    private static void showShopValue(){
        String stockValue = String.format("%.2f", FlowerShop.calcValue());
        System.out.printf("Shop's stock value is: %s eur\n", stockValue);
    }

    private static void showAllStock(){
        myShop.getStock().forEach(product -> System.out.println(product + "\n"));
    }


}
