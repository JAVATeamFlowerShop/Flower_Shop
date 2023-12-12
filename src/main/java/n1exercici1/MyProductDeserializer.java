package n1exercici1;

import com.fasterxml.jackson.databind.*;
import n1exercici1.exceptions.NoSuchTypeProduct;

import java.io.*;

public class MyProductDeserializer extends KeyDeserializer {
    private static final String TYPE_TREE = "TREE";
    private static final String TYPE_DECORATION = "DECORATION";
    private static final String TYPE_FLOWER = "FLOWER";
    @Override
    public Product deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
        String[] splitKey = key.split(",");
        String type = splitKey[0];
        String name = splitKey[2].toLowerCase();
        try {
            int id = Integer.parseInt(splitKey[1]);
            float price = Float.parseFloat(splitKey[3]);
            if (type.equalsIgnoreCase(TYPE_TREE)) {
                float height = Float.parseFloat(splitKey[4]);
                return new Tree(id, name, price, height);
            } else if (type.equalsIgnoreCase(TYPE_DECORATION)) {
                Decoration.Material material = Enum.valueOf(Decoration.Material.class, splitKey[4]);
                return new Decoration(id, name, price, material);
            } else if (type.equalsIgnoreCase(TYPE_FLOWER)) {
                String colour = splitKey[4];
                return new Flower(id, name, price, colour);
            } else {
                throw new NoSuchTypeProduct();
            }
        }
        catch (NoSuchTypeProduct | IllegalArgumentException ex){
            System.err.println("Found the following item: " + key);
            System.err.println("Format error. Skipping item.");
            return null;
        }
    }
}