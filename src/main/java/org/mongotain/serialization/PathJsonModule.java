package org.mongotain.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathJsonModule extends SimpleModule {

    public PathJsonModule() {

        super("PathJsonModule");

        addSerializer(Path.class, new JsonSerializer<Path>() {
            @Override
            public void serialize(Path path, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                    throws IOException {
                if (path == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonGenerator.writeString(path.toString());
                }
            }
        });

        addDeserializer(Path.class, new JsonDeserializer<Path>() {
            @Override
            public Path deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                String path = (String) jp.getEmbeddedObject();
                return Paths.get(path);
            }
        });
    }
}