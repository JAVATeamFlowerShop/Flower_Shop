package n1exercici1;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.*;

public class MyProductSerializer  extends JsonSerializer <Product> {
    private static ObjectMapper mapper = new ObjectMapper();
    public static <T> void serialize(List<T> obj, String filePath) throws IOException {
        File file = new File(filePath);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, obj);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, product);
        jsonGenerator.writeFieldName(writer.toString());
    }
}

