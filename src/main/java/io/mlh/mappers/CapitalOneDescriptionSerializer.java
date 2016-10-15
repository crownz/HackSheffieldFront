package io.mlh.mappers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class CapitalOneDescriptionSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object tmpObj,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        System.out.println(tmpObj.getClass());
        if (tmpObj.getClass().equals(List.class)) {

        } else if (tmpObj.getClass().equals(String.class)) {

        } else {

        }
    }

}
