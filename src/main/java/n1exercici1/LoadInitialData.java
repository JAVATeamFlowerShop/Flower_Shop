package n1exercici1;

import java.io.*;
import java.util.*;

public class LoadInitialData {
    private static final String currDir = System.getProperty("user.dir");
    private static final String initialDataPath = currDir + "/src/main/resources/initialStock.csv";
    private static final String TYPE_FLOWER = "flower";
    private static final String TYPE_TREE = "tree";
    private static final String TYPE_DECORATION = "decoration";
    private static List<String[]> readFilePath(){
        System.out.println("Reading data file...");
        File file = new File(initialDataPath);
        List<String[]> fileData = new ArrayList<>();
        try {
            Scanner myScanner = new Scanner(file);
            myScanner.nextLine();
            while(myScanner.hasNextLine()){
                String[] dataLine = myScanner.nextLine().trim().split(",");
                fileData.add(dataLine);
            }
            System.out.println("File read successfully");
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't find file");
        }
        return fileData;
    }
    public static void createStock(FlowerShop myShop){
        List<String[]> fileData = readFilePath();
        fileData.forEach(s -> {
            String type = s[0];
            String name = s[1];
            float price = Float.parseFloat(s[2]);
            int quantity = Integer.parseInt(s[4]);
            if (type.equalsIgnoreCase(TYPE_FLOWER)) {
                String colour = s[3];
                myShop.addProduct(new Flower(name,price,colour), quantity);
            }
            else if(type.equalsIgnoreCase(TYPE_TREE)){
                float height = Float.parseFloat(s[3]);
                myShop.addProduct(new Tree(name,price,height), quantity);
            }
            else{
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, s[3]);
                myShop.addProduct(new Decoration(name,price,material), quantity);
            }
        });
        System.out.println("Shop initial stock added!\n");
        myShop.setStockValue(myShop.calcValueStore());
    }
}
