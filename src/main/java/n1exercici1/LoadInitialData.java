package n1exercici1;

import java.io.*;
import java.util.*;

public class LoadInitialData {
    private static final String currDir = System.getProperty("user.dir");
    private static final String initialDataPath = currDir + "/src/main/resources/initialStock.csv";
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
    public static void createStock(){
        List<String[]> fileData = readFilePath();
        fileData.forEach(s -> {
            float price = Float.parseFloat(s[2]);
            if (s[0].equalsIgnoreCase("flower")) {
                FlowerShop.stockAdd(new Flower(s[1],price,s[3]));
            }
            else if(s[0].equalsIgnoreCase("tree")){
                float height = Float.parseFloat(s[3]);
                FlowerShop.stockAdd(new Tree(s[1],price,height));
            }
            else{
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, s[3]);
                FlowerShop.stockAdd(new Decoration(s[1],price, material));
            }
        });
    }
}
