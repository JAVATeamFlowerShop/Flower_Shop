package n1exercici1;

public class Main {
    public static void main(String[] args) {
        loop();
    }

    private static void loop(){
        boolean exit = false;
        do{
            switch (menu()){
                case 1 -> System.out.println("Create flower shop\n");
                case 2 -> System.out.println("Add product to stock\n");
                case 3 -> System.out.println("Remove product from stock\n");
                case 4 -> System.out.println("Show all products in stock\n");
                case 5 -> System.out.println("Show total stock with quantities\n");
                case 6 -> System.out.println("Show total flower shop value\n");
                case 7 -> System.out.println("Create purchase receipts (multiple products)\n");
                case 8 -> System.out.println("Show list previous purchases\n");
                case 9 -> System.out.println("Show total sales income\n");
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
