package n1exercici1;

import com.fasterxml.jackson.databind.*;

import java.io.*;

public class MyProductDeserializer extends KeyDeserializer {
    private static final String TYPE_TREE = "TREE";
    private static final String TYPE_DECORATION = "DECORATION";
    private static final String TYPE_FLOWER = "FLOWER";
    @Override
    public Product deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
        String[] splitKey = key.split(",");
        String type = splitKey[0];
        if(type.equalsIgnoreCase(TYPE_TREE)){
            return new Tree(splitKey);
        }
        else if(type.equalsIgnoreCase(TYPE_DECORATION)){
            return new Decoration(splitKey);

        }
        else{
            return new Flower(splitKey);
        }
    }
}