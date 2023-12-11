package n1exercici1;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;

public class MyProductSerializer  extends JsonSerializer <Product> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void serialize(Product product, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, product);
        jsonGenerator.writeFieldName(writer.toString());
    }
}

