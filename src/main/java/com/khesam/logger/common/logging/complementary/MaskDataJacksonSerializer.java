package com.khesam.logger.common.logging.complementary;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

//https://stackoverflow.com/questions/18659835/how-to-deserialize-field-from-object-based-on-annotation-using-jackson
public class MaskDataJacksonSerializer extends StdSerializer<Object> {

    protected MaskDataJacksonSerializer() {
        super(Object.class);
    }

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString("*******");
    }
}
