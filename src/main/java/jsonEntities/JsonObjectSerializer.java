package jsonEntities;

import com.google.gson.*;
import java.lang.reflect.Type;

public class JsonObjectSerializer implements JsonSerializer<JsonDataObjects> {

    @Override
    public JsonElement serialize(JsonDataObjects product, Type type, JsonSerializationContext jsc) {
        JsonObject jObj = (JsonObject)new GsonBuilder().create().toJsonTree(product);
            jObj.remove("unplug_hourTime");
        return jObj;
    }
}
