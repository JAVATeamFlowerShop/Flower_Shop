package n1exercici1;

import java.io.*;
import java.util.*;

public class LoadInitialData {
    private static final String currDir = System.getProperty("user.dir");
    private static final String initialDataPath = currDir + "/src/main/resources/initialStock.csv";
    private static List<String[]> readFilePath(String path){
        System.out.println("Reading data file...");
        File file = new File(path);
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
    private static Flower createFlowers(String[] flowerList){
        float price = Float.parseFloat(flowerList[1]);
        String colour = flowerList[2];
        return new Flower(price, colour);
    }
    public static void createStock(List<String[]> fileData){
        fileData.forEach(s -> {
            float price = Float.parseFloat(s[1]);
            if (s[0].equalsIgnoreCase("flower")) {
                FlowerShop.stockAdd(new Flower(price,s[2]));
            }
            else if(s[0].equalsIgnoreCase("tree")){
                float height = Float.parseFloat(s[2]);
                FlowerShop.stockAdd(new Tree(price, height));
            }
            else{
                FlowerShop.stockAdd(new Decoration(price, s[2]));
            }
        });
    }
}
